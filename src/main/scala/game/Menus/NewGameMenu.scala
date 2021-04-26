package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map
import scala.annotation.varargs
import java.nio.file.FileAlreadyExistsException
import better.files._


class NewGameMenu extends Menu {
  
  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  override def menu(): Unit = {

    val playername = StdIn.readLine("What's your name adventurer?     ")

    val CurrentPlayerState = ujson.Obj("playername" -> playername,
                                       "maxhealth" -> 0,
                                       "currenthealth" -> 0,
                                       "damage" -> 0,
                                       "speed" -> 0,
                                       "level" -> 0)

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

          CurrentPlayerState("maxhealth") = CurrentPlayer.maxhealth
          CurrentPlayerState("currenthealth") = CurrentPlayer.currenthealth
          CurrentPlayerState("damage") = CurrentPlayer.damage
          CurrentPlayerState("speed") = CurrentPlayer.speed
          CurrentPlayerState("level") = CurrentPlayer.level

          try {
          os.write(os.pwd/"CurrentPlayerState.json", CurrentPlayerState)

          continueMenuLoop = false

          val TownMenu = new TownMenu
          TownMenu.menu()
          } catch {

              case faee: FileAlreadyExistsException => {
                  
                val understand = StdIn.readLine("It seems you already have a save file, would you like to overwrite it?    Y/N?   ")
                understand match {
                    case "Y" => {
                        val savefile = file"CurrentPlayerState.json"
                        savefile.delete()

                        os.write(os.pwd/"CurrentPlayerState.json", CurrentPlayerState)

                        continueMenuLoop = false
 
                        val TownMenu = new TownMenu
                        TownMenu.menu()
                    }
                    case "N" => {
                        println("Understood, returning to title screen.")
                        continueMenuLoop = false
                        val startmenu = new StartMenu
                        startmenu.menu()
                    }
                    case _ => {
                        println("Please enter either Y or N, returning to title screen")
                        continueMenuLoop = false
                        val startmenu = new StartMenu
                        startmenu.menu()
                    }
                }
              }
          }
        }

        case commandArgPattern(cmd, arg) if cmd == "Rogue" => {
          val CurrentPlayer = new Rogue

          CurrentPlayerState("maxhealth") = CurrentPlayer.maxhealth
          CurrentPlayerState("currenthealth") = CurrentPlayer.currenthealth
          CurrentPlayerState("damage") = CurrentPlayer.damage
          CurrentPlayerState("speed") = CurrentPlayer.speed
          CurrentPlayerState("level") = CurrentPlayer.level

          try {
          os.write(os.pwd/"CurrentPlayerState.json", CurrentPlayerState)

          continueMenuLoop = false
 
          val TownMenu = new TownMenu
          TownMenu.menu()
          } catch {

              case faee: FileAlreadyExistsException => {
                  
                val understand = StdIn.readLine("It seems you already have a save file, would you like to overwrite it?    Y/N?   ")
                understand match {
                    case "Y" => {
                        val savefile = file"CurrentPlayerState.json"
                        savefile.delete()

                        os.write(os.pwd/"CurrentPlayerState.json", CurrentPlayerState)

                        continueMenuLoop = false
 
                        val TownMenu = new TownMenu
                        TownMenu.menu()
                    }
                    case "N" => {
                        println("Understood, returning to title screen.")
                        continueMenuLoop = false
                        val startmenu = new StartMenu
                        startmenu.menu()
                    }
                    case _ => {
                        println("Please enter either Y or N, returning to title screen")
                        continueMenuLoop = false
                        val startmenu = new StartMenu
                        startmenu.menu()
                    }
                }
              }
          }
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
