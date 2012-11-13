// Channing Walton: https://gist.github.com/4056646
package example

object FunctionThisUp1 {

  case class Stereotype(name: String, description: String, aliases: String*) {
    val allNames = name :: description :: aliases.toList
    def matches(s: String) = allNames.exists(_.equalsIgnoreCase(s))
  }

  val australia  = Stereotype("australia", "a person from australia", "aussie", "legend")
  val newzealand = Stereotype("newzealand", "a person from new zealand", "kiwi")
  val preston    = Stereotype("preston", "a person from preston", "scally")
  val liverpool  = Stereotype("liverpool", "a person from liverpool", "scouser", "thief")
  val manchester = Stereotype("manchester", "a person from manchester", "manc", "mancunian", "fighter")

  val all = List(australia, newzealand, preston, liverpool, manchester)

  def find(name: String) = all find(_ matches name) getOrElse exception(name)

  private def exception(name: String) = {
    val values = all.map(_.allNames).flatten.sorted.mkString(", ")
    throw new IllegalArgumentException(("Invalid Stereotype [" + name + "]. Must be one of [" + values + "]"))
  }
}