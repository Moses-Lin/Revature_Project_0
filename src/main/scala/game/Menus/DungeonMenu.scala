package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map

class DungeonMenu extends Menu {

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r
  val jsonString = os.read(os.pwd/"CurrentPlayerState.json")
  val currentstats = ujson.read(jsonString)

  override def menu(): Unit = {

    printWelcome()

    var continueMenuLoop = true
    while (continueMenuLoop) {
      printMenuOptions()

      var input = StdIn.readLine()
      input match {
        case commandArgPattern(cmd, arg) if cmd == "Floor_1" => {
          println("You look for a fight.")
          continueMenuLoop = false
          val FightMenu = new FightMenu
          FightMenu.menu()
        }

        case commandArgPattern(cmd, arg) if cmd == "Floor_2" => {
          println("You look for a fight.")
          continueMenuLoop = false
          val FightMenu = new FightMenu
          FightMenu.menu()
        }

        case commandArgPattern(cmd, arg) if cmd == "Floor_3" => {
          println("You look for a fight.")
          continueMenuLoop = false
          val FightMenu = new FightMenu
          FightMenu.menu()
        }

        case commandArgPattern(cmd, arg) if cmd == "Floor_4" => {
          println("You can't go here yet.")
          
        }

        case commandArgPattern(cmd, arg) if cmd == "Exit" => {
          continueMenuLoop = false
          val TownMenu = new TownMenu
          TownMenu.menu()
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
            s"You stare idly into space."
          )
          println("Please enter a valid option.")
          println(" ")
        }
      }

    }

  }
  override def printWelcome(): Unit = {

    val healthvalue = currentstats("currenthealth")
    val levelvalue = currentstats("level")

    println(" ")
    println("You're now in a Dungeon.")
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
	"        *      You're currently in a dungeon            *",
	"        *                                               *",
	"        *     1. Floor_1  |   Beginner enemies          *",
  "        *     2. Floor_2  |   Intermediate enemies      *",
  "        *     3. Floor_3  |   Advanced enemies          *",
  "        *     4. Floor_4  |   ????                      *",
  "        *     5. Exit     |   Head back to town         *",
	"        *                                               *",
	"        *************************************************"
    ).foreach(println)

  }

}
