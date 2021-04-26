package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map

class TownMenu extends Menu {

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  override def menu(): Unit = {

    printWelcome()

    var continueMenuLoop = true
    while (continueMenuLoop) {
      printMenuOptions()

      var input = StdIn.readLine()
      input match {
        case commandArgPattern(cmd, arg) if cmd == "Dungeon" => {
          println("You head to the nearby dungeon to fight monsters")
          continueMenuLoop = false
          val DungeonMenu = new DungeonMenu
          DungeonMenu.menu()
        }
        case commandArgPattern(cmd, arg) if cmd == "Shop" => {
          println("You head to the town shop")
        }
        case commandArgPattern(cmd, arg) if cmd == "Sleep" => {
          println("You recover all your HP and recorded your progress")
        }
        case commandArgPattern(cmd, arg) if cmd == "Exit" => {
          continueMenuLoop = false
        }
        case commandArgPattern(cmd, arg) => {
          println(" ")
          println(
            s"You are not sure what that means."
          )
          println("Please enter a valid option.")
          println(" ")
        }
        case _ => { 
          println(" ")
          println(
            s"You stare idly at the sky."
          )
          println("Please enter a valid option.")
          println(" ")
        }
      }

    }

  }
  override def printWelcome(): Unit = {

    val jsonString = os.read(os.pwd/"CurrentPlayerState.json")
    val currentstats = ujson.read(jsonString)
    val healthvalue = currentstats("health")
    val levelvalue = currentstats("level")

    println(" ")
    println("You're now in town.")
    println("--------------------------------------------------------")
    println(f"You currently have $healthvalue%s HP")
    println(s"Your current level is: $levelvalue")
    println("--------------------------------------------------------")
    println(" ")
  }

  override def printMenuOptions(): Unit = {
    List(
	"                                                         ",
	"                                                         ",
	"        *************************************************",
	"        *                                               *",
	"        *         You're Currently at Home              *",
	"        *                                               *",
	"        *     1. Dungeon |   Go to fight monsters       *",
	"        *     2. Shop    |   Go to buy or sell items    *",
	"        *     3. Sleep   |   Rest up and save progress  *",
  "        *     4. Exit    |   Exit the Game              *",
	"        *                                               *",
	"        *************************************************"
    ).foreach(println)

  }

}
