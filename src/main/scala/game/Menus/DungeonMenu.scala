package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map

class DungeonMenu extends Menu {

  var player = new Player
  player.LoadState()

  var currentlevel = player.plevel

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  override def menu(): Unit = {

    printWelcome()

    var continueMenuLoop = true
    while (continueMenuLoop) {
      printMenuOptions()

      var input = StdIn.readLine()
      input match {
        case commandArgPattern(cmd, arg) if cmd == "Floor_1" => {

          println(" ")
          println("You look for a fight.")
          println(" ")

          continueMenuLoop = false

          val FightMenu = new FightMenu
          FightMenu.menu()
        }

        case commandArgPattern(cmd, arg) if cmd == "Floor_2" => {

          if (currentlevel < 10) {
          
          println(" ")
          println("You are too weak to enter this are. You decide not to go.")
          println(" ")

          } else {

          println(" ")
          println("You look for a fight.")
          println(" ")

          continueMenuLoop = false

          val FightMenu = new FightMenu
          FightMenu.menu()
          }
        }

        case commandArgPattern(cmd, arg) if cmd == "Floor_3" => {

          if (currentlevel < 20) {
          
          println(" ")
          println("You are too weak to enter this are. You decide not to go.")
          println(" ")

          } else {

          println(" ")
          println("You look for a fight.")
          println(" ")

          continueMenuLoop = false

          val FightMenu = new FightMenu
          FightMenu.menu()
          }
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
    println("You're now in a Dungeon.")
    println("--------------------------------------------------------")
    println(" ")
  }

  override def printMenuOptions(): Unit = {
    List(
	"                                                         ",
	"                                                         ",
	"        *************************************************",
	"        *                                               *",
	"        *        You're currently in a dungeon          *",
  s"        *               You are level $currentlevel                 *",
  "        *                   _____                       *",
  "        *                  /     \\                      *",
  "        *                 | () () |                     *",
  "        *                  \\  ^  /                      *",
  "        *                   |||||                       *",
	"        *                                               *",
	"        *     1. Floor_1  |   Beginner enemies          *",
  "        *     2. Floor_2  |   Intermediate enemies      *",
  "        *     3. Floor_3  |   Advanced enemies          *",
  "        *     4. Exit     |   Head back to town         *",
	"        *                                               *",
	"        *************************************************"
    ).foreach(println)

  }

}
