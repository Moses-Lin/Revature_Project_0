package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map

class Cli {

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  def menu(): Unit = {

    printWelcome()

    var continueMenuLoop = true
    while (continueMenuLoop) {
      printMenuOptions()

      var input = StdIn.readLine()
      input match {
        case commandArgPattern(cmd, arg) if cmd == "New_Game" => {
          println("this is a new game")
        }
        case commandArgPattern(cmd, arg) if cmd == "Saved_Game" => {
          println("this is a saved game")
        }
        case commandArgPattern(cmd, arg) if cmd == "Exit" => {
          continueMenuLoop = false
        }
        case commandArgPattern(cmd, arg) => {
          println(
            s"Parsed command $cmd with args $arg did not correspond to an option"
          )
        }
        case _ => { 
          println("Please enter an option on the menu!")
        }
      }

    }

  }

  def printWelcome(): Unit = {
    println(" ")
    println("Welcome to a somewhat mediocre adventure!")
    println(" ")
  }

  def printMenuOptions(): Unit = {
    List(
      "   ░▒▓▆▅▃▂▁𝐎𝐩𝐭𝐢𝐨𝐧𝐬▁▂▃▅▆▓▒░",
      "------------------------------",
      "★ New_Game    | Start a new adventure",
      "★ Saved_Game  | Resume where you left off",
      "★ Exit        | Exit the game."
    ).foreach(println)

  }

}