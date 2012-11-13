; Albert Latacz: https://github.com/albertlatacz/function-this-up-clj
(ns functional_stereotype_test
  (:use clojure.test
        functional_stereotype))

(deftest value-for-name-should-return-valid-stereotype-when-passed-description
  (is (= (find-stereotype "a person from new zealand") :newzealand )))

(deftest value-for-name-should-return-valid-stereotype-when-passed-enum-value-itself
  (is (= (find-stereotype "manchester") :manchester )))

(deftest value-for-name-should-return-valid-stereotype-when-passed-alternative-name
  (is (= (find-stereotype "thief") :liverpool )))

(deftest value-for-name-should-be-case-insensitive
  (is (= (find-stereotype "scally") :preston ))
  (is (= (find-stereotype "SCALLY") :preston ))
  (is (= (find-stereotype "ScAlLy") :preston )))

(deftest value-for-name-should-throw-error-when-passed-invalid-string
  (is (thrown-with-msg?
        IllegalArgumentException
        #"Invalid Stereotype \[Awesome\]\. Must be one of \[a person from australia, a person from liverpool, a person from manchester, a person from new zealand, a person from preston, aussie, australia, fighter, kiwi, legend, liverpool, manc, manchester, mancunian, newzealand, preston, scally, scouser, thief\]"
        (find-stereotype "Awesome"))))