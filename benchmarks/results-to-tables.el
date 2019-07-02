(defun string-mean-sdev (line)
  (let* ((l (split-string line ": "))
         (md (split-string
              (calc-eval '("vmean($), vsdev($)" calc-float-format (fix 0)) nil (cadr l))
              ", ")))
    (list (cons 'task (car l))
          (cons 'mean (car md))
          (cons 'sdev (cadr md)))))

(string-mean-sdev "Load view: [172,179,170,166,172,168,166,175,166,168]")
((task . "Load view") (mean . "170.") (sdev . "4."))

(defun task-mean-sdev (bench task)
  (with-current-buffer "all-xeon"
    (goto-char (point-min))
    (re-search-forward (format "^# Results for: %s$" bench))
    (re-search-forward (format "^%s" task))
    (string-mean-sdev
     (buffer-substring (line-beginning-position) (line-end-position)))))

(task-mean-sdev "Load view with XMI trace of size 10" "Load view")
((task . "Load view") (mean . "170.") (sdev . "4."))

(task-mean-sdev "Load view with XMI trace of size 10" "Get all")
((task . "Get all elements") (mean . "577.") (sdev . "10."))

(defun bench-task-mean-sdev (bench task sizes &optional suffix)
  (mapcar
   (lambda (s)
     (cons (cons 'size s)
           (task-mean-sdev (format "%s of size %d%s" bench s (or suffix "")) task)))
   sizes))

(bench-task-mean-sdev "Load view with XMI trace" "Load view" '(10 100))
(((size . 10) (task . "Load view") (mean . "170.") (sdev . "4."))
 ((size . 100) (task . "Load view") (mean . "167.") (sdev . "4.")))

(defun print-table (results)
  (insert (format "| Size | %s |\n" (alist-get 'task (car results))))
  (insert "|-\n")
  (dolist (r results)
    (insert (format "| %d | %s ± %s |\n" (log10 (alist-get 'size r))
                    (alist-get 'mean r) (alist-get 'sdev r)))))

(print-table (bench-task-mean-sdev "Load view with NeoEMF trace" "Load view"
                                   '(10 100 1000 10000 100000 1000000)))
| Size | Load view |
|-
| 1 | 7982. ± 840. |
| 2 | 6060. ± 101. |
| 3 | 6013. ± 146. |
| 4 | 7874. ± 159. |
| 5 | 23138. ± 1323. |
| 6 | 181645. ± 2779. |

(defun pp0 (bench task &optional suffix)
  (insert "\n\n" bench (or suffix "") "\n")
  (print-table (bench-task-mean-sdev bench task '(10 100 1000 10000 100000 1000000) suffix)))

(pp0 "Load view with NeoEMF trace" "Load view")

Load view with NeoEMF trace
| Size | Load view |
|-
| 1 | 7982. ± 840. |
| 2 | 6060. ± 101. |
| 3 | 6013. ± 146. |
| 4 | 7874. ± 159. |
| 5 | 23138. ± 1323. |
| 6 | 181645. ± 2779. |

(pp0 "EOL query traceToReqs.eol for NeoEMF view" "Load view" " with EMF EMC")

EOL query traceToReqs.eol for NeoEMF view with EMF EMC
| Size | Load view |
|-
| 1 | 6126. ± 211. |
| 2 | 6126 ± 102. |
| 3 | 6197. ± 196. |
| 4 | 7816. ± 158. |
| 5 | 23494 ± 1086. |
| 6 | 179842. ± 3111. |
