package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map

class DungeonMenu extends Menu {

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  override def menu(): Unit = {

    printWelcome()

    var continueMenuLoop = true
    while (continueMenuLoop) {
      printMenuOptions()

      var input = StdIn.readLine()
      input match {
        case commandArgPattern(cmd, arg) if cmd == "Fight" => {
          println("You look for a fight.")
          continueMenuLoop = false
          val FightMenu = new FightMenu
          FightMenu.menu()
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
    println(" ")
    println("You're now at a dungeon")
    println(" ")
    println("What should I do now?")
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
	"        *     1. Fight   |   Go to fight monsters       *",
    "        *     2. Exit    |   Exit back to town          *",
	"        *                                               *",
	"        *************************************************"
    ).foreach(println)

  }

}
