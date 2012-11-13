// Rhys Keepence: git://gist.github.com/3983616.git
package example

object Stereotypes {
  val stereotypes = Seq(australia, newzealand, preston, liverpool, manchester)

  def find(name: String): Either[String, Stereotype] = {
    stereotypes
      .find(stereotype => stereotype.names.exists(name.equalsIgnoreCase))
      .toRight("Invalid Stereotype [%s]. Must be one of [%s]".format(name, allPossibleStereotypes))
  }

  def allPossibleStereotypes = stereotypes.flatMap(s => s.names).sorted.mkString(", ")
}

sealed abstract case

class Stereotype(description: String, aliases: String*) {
  val names = toString :: description :: aliases.toList
}

case object australia extends Stereotype("a person from australia", "aussie", "legend")

case object newzealand extends Stereotype("a person from new zealand", "kiwi")

case object preston extends Stereotype("a person from preston", "scally")

case object liverpool extends Stereotype("a person from liverpool", "scouser", "thief")

case object manchester extends Stereotype("a person from manchester", "manc", "mancunian", "fighter")