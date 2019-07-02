(defun task-line (buffer bench task)
  (with-current-buffer buffer
    (goto-char (point-min))
    (re-search-forward (format "^# Results for: %s$" bench))
    (re-search-forward (format "^%s" task))
    (buffer-substring (line-beginning-position) (line-end-position))))

(task-line "all-xeon" "Load view with XMI trace of size 10" "Load view")
"Load view: [169,168,155,163,165,153,164,163,151,163]"

(defun parse-task-line (line)
  (split-string line ": "))

(parse-task-line "Load view: [169,168,155,163,165,153,164,163,151,163]")
("Load view" "[169,168,155,163,165,153,164,163,151,163]")

(defun task-mean-sdev (task)
  (let ((md (split-string
             (calc-eval '("vmean($), vsdev($)" calc-float-format (fix 0)) nil (cadr task))
             ", ")))
    (list (cons 'task (car task))
          (cons 'mean (car md))
          (cons 'sdev (cadr md)))))

(task-mean-sdev '("Load view" "[172,179,170,166,172,168,166,175,166,168]"))
((task . "Load view") (mean . "170.") (sdev . "4."))

(task-mean-sdev
 (parse-task-line
  (task-line "all-xeon" "Load view with XMI trace of size 10" "Load view")))
((task . "Load view") (mean . "161.") (sdev . "6."))

(task-mean-sdev
 (parse-task-line
  (task-line "all-xeon" "Load view with XMI trace of size 10" "Get all")))
((task . "Get all elements") (mean . "595.") (sdev . "5."))

(defun bench-tasks (buffer bench task sizes &optional suffix)
  (mapcar
   (lambda (s)
     (cons (cons 'size s)
           (parse-task-line
            (task-line buffer (format "%s of size %d%s" bench s (or suffix "")) task))))
   sizes))

(bench-tasks "all-xeon" "Load view with XMI trace" "Load view" '(10 100))
(((size . 10) "Load view" "[169,168,155,163,165,153,164,163,151,163]")
 ((size . 100) "Load view" "[163,153,165,164,155,164,163,154,165,152]"))

(defun bench-task-mean-sdev (bench task sizes &optional suffix)
  (mapcar
   (lambda (tsk) (cons (car tsk) (task-mean-sdev (cdr tsk))))
   (bench-tasks "all-xeon" bench task sizes suffix)))

(mapcar
 (lambda (tsk) (cons (car tsk)
                     (task-mean-sdev (cdr tsk))))
 (bench-tasks "all-xeon" "Load view with XMI trace" "Load view" '(10 100 1000)))
(((size . 10)   (task . "Load view") (mean . "161.") (sdev . "6."))
 ((size . 100)  (task . "Load view") (mean . "160.") (sdev . "6."))
 ((size . 1000) (task . "Load view") (mean . "191.") (sdev . "6.")))

(bench-task-mean-sdev "Load view with XMI trace" "Load view" '(10 100 1000))
(((size . 10)   (task . "Load view") (mean . "161.") (sdev . "6."))
 ((size . 100)  (task . "Load view") (mean . "160.") (sdev . "6."))
 ((size . 1000) (task . "Load view") (mean . "191.") (sdev . "6.")))


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


;; Outputting data to gnuplot to see if boxplots are worth it

(task-line "all-xeon" "Load view with XMI trace of size 10" "Load view")
"Load view: [169,168,155,163,165,153,164,163,151,163]"

(defun vector-string-to-list (str)
  (cdr (calc-eval str 'raw)))

(vector-string-to-list
 (cdr (parse-task-line (task-line "all-xeon" "Load view with XMI trace of size 10" "Load view"))))
(169 168 155 163 165 153 164 163 151 163)

(bench-tasks "all-xeon" "OCL query 0 for full view on XMI trace" "Evaluate query" '(10 100 1000))
(((size . 10) "Evaluate query" "[612,614,610,647,609,606,613,607,615,642]")
 ((size . 100) "Evaluate query" "[604,609,612,612,611,647,611,613,611,610]")
 ((size . 1000) "Evaluate query" "[619,623,647,614,612,614,620,622,646,615]"))

(defun bench-task-raw (bench bench-name task sizes &optional suffix)
  (mapcar
   (lambda (tsk)
     (list (cons 'bench bench-name)
           (cons 'task (nth 1 tsk))
           (nth 0 tsk)
           (cons 'values (vector-string-to-list (nth 2 tsk)))))
   (bench-tasks "all-xeon" bench task sizes suffix)))

(bench-task-raw "OCL query 0 for full view on XMI trace" "xmi" "Evaluate query" '(10 100 1000))
(((bench . "xmi") (task . "Evaluate query") (size . 10) (values 612 614 610 647 609 606 613 607 615 642))
 ((bench . "xmi") (task . "Evaluate query") (size . 100) (values 604 609 612 612 611 647 611 613 611 610))
 ((bench . "xmi") (task . "Evaluate query") (size . 1000) (values 619 623 647 614 612 614 620 622 646 615)))

(defun compare-benches (task benches &optional sizes)
  (-mapcat
   (lambda (b)
     (bench-task-raw (nth 0 b) (nth 1 b) task (or sizes '(10 100 1000 10000 100000 1000000)) (nth 2 b)))
   benches))

(compare-benches "Evaluate query"
                 '(("OCL query 0 for full view on XMI trace" "xmi")
                   ("OCL query 0 for full view on XMI trace" "xmi-fast" " with fast extents map"))
                 '(10 100))
(((bench . "xmi") (task . "Evaluate query") (size . 10) (values 612 614 610 647 609 606 613 607 615 642))
 ((bench . "xmi") (task . "Evaluate query") (size . 100) (values 604 609 612 612 611 647 611 613 611 610))
 ((bench . "xmi-fast") (task . "Evaluate query") (size . 10) (values 10 11 11 10 11 10 10 10 10 10))
 ((bench . "xmi-fast") (task . "Evaluate query") (size . 100) (values 10 10 11 10 10 9 11 9 10 10)))

(defun print-gnuplot (results)
  (dolist (r results)
    (dolist (x (alist-get 'values r))
      (insert (format "%d %d \"%s\"\n" (log10 (alist-get 'size r)) x (alist-get 'bench r))))))

(print-gnuplot
 (bench-task-raw "OCL query 0 for full view on XMI trace" "xmi" "Evaluate query" '(10 100)))

1 612 "xmi"
1 614 "xmi"
1 610 "xmi"
1 647 "xmi"
1 609 "xmi"
1 606 "xmi"
1 613 "xmi"
1 607 "xmi"
1 615 "xmi"
1 642 "xmi"
2 604 "xmi"
2 609 "xmi"
2 612 "xmi"
2 612 "xmi"
2 611 "xmi"
2 647 "xmi"
2 611 "xmi"
2 613 "xmi"
2 611 "xmi"
2 610 "xmi"
