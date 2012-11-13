; Albert Latacz: https://github.com/albertlatacz/function-this-up-clj
(ns functional_stereotype
  (:require [clojure.string :as str]))

(defn find-stereotype [stereotype-name]
  (let
    [stereotypes {:australia ["a person from australia", "aussie", "legend"]
                  :newzealand ["a person from new zealand", "kiwi"]
                  :preston ["a person from preston", "scally"]
                  :liverpool ["a person from liverpool", "scouser", "thief"]
                  :manchester ["a person from manchester", "manc", "mancunian", "fighter"]
                  }

     allowed-names (fn [entry]
      (cons (name (key entry)) (val entry)))

     find-with-name (fn [name]
      (filter
        (fn [x] (some #(.equalsIgnoreCase name %) (allowed-names x)))
        stereotypes))

     found (find-with-name stereotype-name)]

    (if (empty? found)
      (throw (IllegalArgumentException. (format "Invalid Stereotype [%s]. Must be one of [%s]" stereotype-name (str/join ", " (sort (mapcat allowed-names stereotypes))))))
      (key (first found)))))
