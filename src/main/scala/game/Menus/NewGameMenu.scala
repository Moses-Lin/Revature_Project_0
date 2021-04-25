package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map
import scala.annotation.varargs

class NewGameMenu extends Menu {
  
  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  override def menu(): Unit = {

    val playername = StdIn.readLine("What's your name adventurer?     ")

    val CurrentPlayerState = ujson.Obj("playername" -> playername, "health" -> 0, "damage" -> 0, "speed" -> 0, "level" -> 0)

    println(" ")
    println(s"Ah, it's nice to meet you $playername.")
    println(" ")
    println("How would you like to start?")
    println(" ")

    var continueMenuLoop = true
    while (continueMenuLoop) {
      printMenuOptions()

    var input = StdIn.readLine()
      input match {
        case commandArgPattern(cmd, arg) if cmd == "Warrior" => {
          val CurrentPlayer = new Warrior

          CurrentPlayerState("health") = CurrentPlayer.health
          CurrentPlayerState("damage") = CurrentPlayer.damage
          CurrentPlayerState("speed") = CurrentPlayer.speed
          CurrentPlayerState("level") = CurrentPlayer.level

          os.write(os.pwd/"CurrentPlayerState.json", CurrentPlayerState)

          continueMenuLoop = false

          val TownMenu = new TownMenu
          TownMenu.menu()
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
