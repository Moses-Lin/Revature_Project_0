package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map
import java.io.FileNotFoundException
import java.nio.file.NoSuchFileException
import java.util.UUID

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

          println(" ")
          println("Ah, a new adventurer. Let's begin.")
          println(" ")

          continueMenuLoop = false
          val NewGameMenu = new NewGameMenu
          NewGameMenu.menu()
        }
        case commandArgPattern(cmd, arg) if cmd == "Saved_Game" => {
          continueMenuLoop = false
          try {
          val jsonString = os.read(os.pwd/"CurrentPlayerState.json")
          } catch {
            
            case nsfe: NoSuchFileException => {

              println(" ")
              println("There are no save files yet!")
              println("Sending you back to the title screen")
              println(" ")

              val StartMenu = new StartMenu
              StartMenu.menu()
            }
          }
        }
        case commandArgPattern(cmd, arg) if cmd == "Exit" => {
          continueMenuLoop = false
        }

        case commandArgPattern(cmd, arg) if cmd == "Debug" => {
          val drool = Time.timestamp()
          drool

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
    println("================================================")
    println(" ")
    println("Welcome to a somewhat mediocre adventure!")
    println(" ")
    println("Please select one of the options!")
    println(" ")
  }

  override def printMenuOptions(): Unit = {
    List(
      "================================================",
      "                                                ",
      "    ░▒▓▆▅▃▂▁Mediocre Adventure▁▂▃▅▆▓▒░   ",
      "------------------------------------------------",
      "                                                ",
      "★ New_Game    |  Start a new adventure          ",
      "★ Saved_Game  |  Resume where you left off      ",
      "★ Exit        |  Exit the game.                 ",
      "                                                ",
      "================================================",
    ).foreach(println)

  }

}