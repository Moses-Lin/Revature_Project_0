package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map

class NewGameMenu extends Menu {
  
  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  def nameselect(): Unit = {

    val playername = StdIn.readLine("What's your name adventurer?     ")

    val CurrentPlayerState = ujson.Obj("playername" -> playername)
    os.write(os.pwd/"CurrentPlayerState.json", CurrentPlayerState)

    println(" ")
    println(s"Ah, it's nice to meet you $playername.")
    println(" ")
    println("How would you like to start?")
    println(" ")

    val NewGameMenu = new NewGameMenu
    NewGameMenu.menu()
  }

  override def menu(): Unit = {

    var continueMenuLoop = true
    while (continueMenuLoop) {
      printMenuOptions()

      var input = StdIn.readLine()
      input match {
        case commandArgPattern(cmd, arg) if cmd == "Warrior" => {
          val CurrentPlayer = new Warrior

          val TownMenu = new TownMenu
          TownMenu.menu()
          continueMenuLoop = false
        }

        case commandArgPattern(cmd, arg) if cmd == "Rogue" => {
          val CurrentPlayer = new Rogue


          val TownMenu = new TownMenu
          TownMenu.menu()
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

  override def printMenuOptions(): Unit = {
      List(
      "================================================",
      "                                                ",
      "                Pick a Class                    ",
      "            Type Warrior or Rogue               ",
      "------------------------------------------------",
      "      Warrior        |         Rogue            ",
      "                     |                          ",
      "      10 HP          |         6 HP             ",
      "      2 Atk          |         3 Atk            ",
      "      2 Spd          |         3 Spd            ",
      "                     |                          ",
      "                     |                          ",
      "================================================",
    ).foreach(println)

  }
}
