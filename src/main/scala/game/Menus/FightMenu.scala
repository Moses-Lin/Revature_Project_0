package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map

class FightMenu extends Menu{

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  val jsonString = os.read(os.pwd/"CurrentPlayerState.json")
  val currentstats = ujson.read(jsonString)

  val jsonString2 = os.read(os.pwd/"enemybeginner.json")
  val weakenemystats = ujson.read(jsonString2)

  val e = new scala.util.Random
  val enemyselect = e.nextInt((4))

  override def menu(): Unit = {

    val randomenemy = weakenemystats(enemyselect)("name")

    println("-------------------------------------------------------------------------")
    println(" ")
    println(s"A $randomenemy has appeared! It seems hostile.")
    println(" ")
    println("-------------------------------------------------------------------------")

    var continueMenuLoop = true
    while (continueMenuLoop) {
      printMenuOptions()

      var input = StdIn.readLine()
      input match {
        case commandArgPattern(cmd, arg) if cmd == "Fight" => {
         continueMenuLoop = false
            // placeholder
        }

        case commandArgPattern(cmd, arg) if cmd == "Item" => {
         continueMenuLoop = false
            // placeholder
        }

        case commandArgPattern(cmd, arg) if cmd == "Run" => {
          continueMenuLoop = false
          val DungeonMenu = new DungeonMenu
          DungeonMenu.menu()
        }
        case commandArgPattern(cmd, arg) => {
          println(" ")
          println(
            s"I'm not sure what that means!"
          )
          println("Please enter a valid option.")
          println(" ")
        }
        case _ => { 
          println("Please enter an option on the menu!")
        }
      }

    }

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
