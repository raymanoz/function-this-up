-- Franck Rasolo: https://gist.github.com/3985593
import Data.Char (toLower)
import Data.List (find, intercalate, isInfixOf, sort)

data Stereotype = Stereotype { name :: String, description :: String, aliases :: [String] }
                  deriving (Show)

stereotypes = [
    Stereotype "australia"  "a person from australia"   ["aussie", "legend"]
  , Stereotype "newzealand" "a person from new zealand" ["kiwi"]
  , Stereotype "preston"    "a person from preston"     ["scally"]
  , Stereotype "liverpool"  "a person from liverpool"   ["scouser", "thief"]
  , Stereotype "manchester" "a person from manchester"  ["manc", "mancunian", "fighter"]
  ]

search :: String -> Either String Stereotype
search term = maybe (Left $ message term) Right (find match stereotypes)
    where match stereotype = isInfixOf (map toLower term) (concat $ flatten stereotype)

message term = "Invalid Stereotype [" ++ term ++ "]. Must be one of [" ++ allowed ++ "]"
    where allowed = intercalate ", " (sort $ concatMap flatten stereotypes)

flatten :: Stereotype -> [String]
flatten s = name s : description s : aliases s