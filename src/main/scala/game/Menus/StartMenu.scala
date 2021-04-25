package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map

class StartMenu extends Menu {

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  override def menu(): Unit = {

    printWelcome()

    var continueMenuLoop = true
    while (continueMenuLoop) {
      printMenuOptions()

      var input = StdIn.readLine()
      input match {
        case commandArgPattern(cmd, arg) if cmd == "New_Game" => {
          println("this is a new game")
          continueMenuLoop = false
          val TownMenu = new TownMenu
          TownMenu.menu()
        }
        case commandArgPattern(cmd, arg) if cmd == "Saved_Game" => {
          println("this is a saved game")
        }
        case commandArgPattern(cmd, arg) if cmd == "Exit" => {
          continueMenuLoop = false
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

  override def printWelcome(): Unit = {
    println(" ")
    println("Welcome to a somewhat mediocre adventure!")
    println(" ")
    println("Please select one of the options!")
    println(" ")
  }

  override def printMenuOptions(): Unit = {
    List(
      "   ░▒▓▆▅▃▂▁𝐎𝐩𝐭𝐢𝐨𝐧𝐬▁▂▃▅▆▓▒░",
      "------------------------------",
      "★ New_Game    | Start a new adventure",
      "★ Saved_Game  | Resume where you left off",
      "★ Exit        | Exit the game."
    ).foreach(println)

  }

}