package cdobackend;

/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.Closeable;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jSessionConfiguration;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.server.CDOServerUtil;
import org.eclipse.emf.cdo.server.IRepository;
import org.eclipse.emf.cdo.server.ISession;
import org.eclipse.emf.cdo.server.IStore;
import org.eclipse.emf.cdo.server.db.CDODBUtil;
import org.eclipse.emf.cdo.server.db.mapping.IMappingStrategy;
import org.eclipse.emf.cdo.server.db.mapping.IMappingStrategy.Props;
import org.eclipse.emf.cdo.server.net4j.CDONet4jServerUtil;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.spi.server.ISessionProtocol;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.acceptor.IAcceptor;
import org.eclipse.net4j.db.DBUtil;
import org.eclipse.net4j.db.IDBAdapter;
import org.eclipse.net4j.db.IDBConnectionProvider;
import org.eclipse.net4j.db.h2.H2Adapter;
import org.eclipse.net4j.jvm.IJVMConnector;
import org.eclipse.net4j.jvm.JVMUtil;
import org.eclipse.net4j.signal.ISignalProtocol;
import org.eclipse.net4j.util.container.ContainerEventAdapter;
import org.eclipse.net4j.util.container.ContainerUtil;
import org.eclipse.net4j.util.container.IContainer;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.h2.jdbcx.JdbcDataSource;

public class CDOBackend {

	public static final String NAME = "cdo";

	private EmbeddedCdoServer server;
	private CDOSession session;
	private CDOTransaction transaction;

	public CDOBackend() {
	}

	/**
	 * Creates a new {@link Resource} in the server at {@code serverFile} with the
	 * provided {@code resourceName}.
	 * <p>
	 * <b>Note:</b> this method should not be used to retrieve existing
	 * {@link Resource}s, see {@link #getResource(File, String)} to retrieve an
	 * existing {@link Resource} with a given name.
	 * 
	 * @param serverFile
	 *            the {@link File} containing the CDO server database
	 * @param resourceName
	 *            the name of the {@link Resource} to create
	 * @return the created {@link Resource}
	 * @throws Exception
	 *             if the server cannot be found or if the CDO transaction cannot
	 *             create a new {@link Resource}
	 * @see #getResource(File, String)
	 */
	public Resource createResource(File serverFile, String resourceName) throws Exception {
		server = new EmbeddedCdoServer(serverFile.getAbsolutePath());
		server.run();
		session = server.openSession();
		transaction = session.openTransaction();
		Resource r = transaction.createResource(resourceName);
		r.load(getOptions());
		return r;
	}

	/**
	 * Retrieves an existing {@link Resource} in the server at {@code serverFile}
	 * with the provided {@code resourceName}.
	 * <p>
	 * <b>Note:</b> this method should not be used to create new {@link Resource}s,
	 * see {@link #createResource(File, String)} to create a new {@link Resource}
	 * with a given name.
	 * 
	 * @param serverFile
	 *            the {@link File} containing the CDO server database
	 * @param resourceName
	 *            the name of the {@link Resource} to retrieve
	 * @return the retrieved {@link Resource}
	 * @throws Exception
	 *             if the server cannot be found or if the CDO transaction cannot
	 *             get the {@link Resource}
	 * @see #createResource(File, String)
	 */
	public Resource getResource(File serverFile, String resourceName) throws Exception {
		server = new EmbeddedCdoServer(serverFile.getAbsolutePath());
		server.run();
		session = server.openSession();
		transaction = session.openTransaction();
		Resource r = transaction.getResource(resourceName);
		r.load(getOptions());
		return r;
	}

	/**
	 * Closes the backend and its underlying {@link EmbeddedCdoServer}.
	 * <p>
	 * This method should be called at the end of the main application to force the
	 * {@link EmbeddedCdoServer} to shutdown and terminate associated threads.
	 */
	public void close() {
		server.close();
	}

	/**
	 * Returns the loading/saving options of the created and retrieved
	 * {@link Resource}s.
	 * 
	 * @return the loading/saving options of the created and retrieved
	 *         {@link Resource}s
	 * @see #createResource(File, String)
	 * @see #getResource(File, String)
	 */
	public Map<String, Object> getOptions() {
		Map<String, Object> saveOpts = new HashMap<>();
		saveOpts.put(CDOResource.OPTION_SAVE_OVERRIDE_TRANSACTION, transaction);
		return saveOpts;
	}

	/**
	 * Embedded implementation of CDO server.
	 */
	public static class EmbeddedCdoServer implements Closeable {

		private static final String DEFAULT_REPOSITORY_NAME = "repo";

		private final String path;

		private final String repositoryName;

		private IJVMConnector connector;

		private IManagedContainer container;

		public EmbeddedCdoServer(String path) {
			this.path = path;
			this.repositoryName = DEFAULT_REPOSITORY_NAME;
		}

		public void run() {
			try {
				JdbcDataSource dataSource = createDataSource("jdbc:h2:" + path + "/" + repositoryName);
				IStore cdoStore = createStore(dataSource);
				IRepository cdoRepository = createRepository(cdoStore);

				container = createContainer();
				CDOServerUtil.addRepository(container, cdoRepository);

				@SuppressWarnings("unused")
				IAcceptor acceptor = JVMUtil.getAcceptor(container, "default");
				connector = JVMUtil.getConnector(container, "default");

				cdoRepository.getSessionManager().addListener(new ContainerEventAdapter<ISession>() {
					@Override
					protected void onAdded(IContainer<ISession> container, ISession session) {
						ISessionProtocol protocol = session.getProtocol();
						if (protocol instanceof ISignalProtocol) {
							ISignalProtocol<?> signalProtocol = (ISignalProtocol<?>) protocol;
							signalProtocol.setTimeout(30L * 1000L);
						}
					}
				});
			} finally {
				Runtime.getRuntime().addShutdownHook(new Thread(EmbeddedCdoServer.this::close));
			}
		}

		@Override
		public void close() {
			if (nonNull(connector) && !connector.isClosed()) {
				connector.close();
			}
			if (nonNull(container) && container.isActive()) {
				Exception e = container.deactivate();

				if (nonNull(e)) {
					e.printStackTrace();
				}
			}
		}

		public boolean isClosed() {
			return isNull(connector) || connector.isClosed();
		}

		public CDOSession openSession() {
			CDONet4jSessionConfiguration config = CDONet4jUtil.createNet4jSessionConfiguration();
			config.setConnector(connector);
			config.setRepositoryName(repositoryName);
			return config.openNet4jSession();
		}

		private JdbcDataSource createDataSource(String url) {
			JdbcDataSource dataSource = new JdbcDataSource();
			dataSource.setURL(url);
			return dataSource;
		}

		private IStore createStore(JdbcDataSource dataSource) {
			IMappingStrategy mappingStrategy = CDODBUtil.createHorizontalMappingStrategy(true);
			mappingStrategy.getProperties().put(Props.QUALIFIED_NAMES, "true");
			IDBAdapter dbAdapter = new H2Adapter();
			IDBConnectionProvider dbConnectionProvider = DBUtil.createConnectionProvider(dataSource);
			return CDODBUtil.createStore(mappingStrategy, dbAdapter, dbConnectionProvider);
		}

		private IRepository createRepository(IStore store) {
			Map<String, String> props = new HashMap<>();
			props.put(IRepository.Props.OVERRIDE_UUID, repositoryName);
			props.put(IRepository.Props.SUPPORTING_AUDITS, "false");
			props.put(IRepository.Props.SUPPORTING_BRANCHES, "false");
			return CDOServerUtil.createRepository(repositoryName, store, props);
		}

		private IManagedContainer createContainer() {
			IManagedContainer container = ContainerUtil.createContainer();
			Net4jUtil.prepareContainer(container);
			JVMUtil.prepareContainer(container);
			CDONet4jUtil.prepareContainer(container);
			CDONet4jServerUtil.prepareContainer(container);
			container.activate();
			return container;
		}
	}
}
