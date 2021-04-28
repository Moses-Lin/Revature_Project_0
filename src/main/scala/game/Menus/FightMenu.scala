package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map

class FightMenu extends Menu{

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  val jsonString2 = os.read(os.pwd/"enemybeginner.json")
  val weakenemystats = ujson.read(jsonString2)

  val e = new scala.util.Random
  val enemyselect = e.nextInt((4))

  override def menu(): Unit = {

  }

  override def printMenuOptions(): Unit = {
    List(
	"                                                         ",
	"                                                         ",
	"        *************************************************",
	"        *                                               *",
	"        *         You're currently fighting             *",
	"        *                                               *",
	"        *     1. Fight  |   Attack the enemy            *",
  "        *     2. Item   |   Use an item                 *",
  "        *     3. Run    |   Exit the Game               *",
	"        *                                               *",
	"        *************************************************"
    ).foreach(println)
  }

}
