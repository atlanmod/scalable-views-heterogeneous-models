/**
 */
package traceneoemf;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Log Level</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see traceneoemf.TraceneoemfPackage#getLogLevel()
 * @model
 * @generated
 */
public enum LogLevel implements Enumerator {
  /**
   * The '<em><b>SEVERE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SEVERE_VALUE
   * @generated
   * @ordered
   */
  SEVERE(1000, "SEVERE", "SEVERE"),

  /**
   * The '<em><b>WARNING</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #WARNING_VALUE
   * @generated
   * @ordered
   */
  WARNING(900, "WARNING", "WARNING"),

  /**
   * The '<em><b>INFO</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INFO_VALUE
   * @generated
   * @ordered
   */
  INFO(800, "INFO", "INFO"),

  /**
   * The '<em><b>CONFIG</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CONFIG_VALUE
   * @generated
   * @ordered
   */
  CONFIG(700, "CONFIG", "CONFIG"),

  /**
   * The '<em><b>FINE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FINE_VALUE
   * @generated
   * @ordered
   */
  FINE(500, "FINE", "FINE"),

  /**
   * The '<em><b>FINER</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FINER_VALUE
   * @generated
   * @ordered
   */
  FINER(400, "FINER", "FINER"),

  /**
   * The '<em><b>FINEST</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FINEST_VALUE
   * @generated
   * @ordered
   */
  FINEST(300, "FINEST", "FINEST");

  /**
   * The '<em><b>SEVERE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SEVERE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SEVERE
   * @model
   * @generated
   * @ordered
   */
  public static final int SEVERE_VALUE = 1000;

  /**
   * The '<em><b>WARNING</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>WARNING</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #WARNING
   * @model
   * @generated
   * @ordered
   */
  public static final int WARNING_VALUE = 900;

  /**
   * The '<em><b>INFO</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>INFO</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INFO
   * @model
   * @generated
   * @ordered
   */
  public static final int INFO_VALUE = 800;

  /**
   * The '<em><b>CONFIG</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>CONFIG</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #CONFIG
   * @model
   * @generated
   * @ordered
   */
  public static final int CONFIG_VALUE = 700;

  /**
   * The '<em><b>FINE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FINE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FINE
   * @model
   * @generated
   * @ordered
   */
  public static final int FINE_VALUE = 500;

  /**
   * The '<em><b>FINER</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FINER</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FINER
   * @model
   * @generated
   * @ordered
   */
  public static final int FINER_VALUE = 400;

  /**
   * The '<em><b>FINEST</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FINEST</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FINEST
   * @model
   * @generated
   * @ordered
   */
  public static final int FINEST_VALUE = 300;

  /**
   * An array of all the '<em><b>Log Level</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final LogLevel[] VALUES_ARRAY =
    new LogLevel[] {
      SEVERE,
      WARNING,
      INFO,
      CONFIG,
      FINE,
      FINER,
      FINEST,
    };

  /**
   * A public read-only list of all the '<em><b>Log Level</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<LogLevel> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Log Level</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static LogLevel get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      LogLevel result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Log Level</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static LogLevel getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      LogLevel result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Log Level</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static LogLevel get(int value) {
    switch (value) {
      case SEVERE_VALUE: return SEVERE;
      case WARNING_VALUE: return WARNING;
      case INFO_VALUE: return INFO;
      case CONFIG_VALUE: return CONFIG;
      case FINE_VALUE: return FINE;
      case FINER_VALUE: return FINER;
      case FINEST_VALUE: return FINEST;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private LogLevel(int value, String name, String literal) {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue() {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName() {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral() {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    return literal;
  }
  
} //LogLevel
