package game

import game.Main
import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map
import scala.annotation.varargs

class TownMenu extends Menu {

  var player = new Player
  player.LoadState()

  var pcurrenthealth = player.pcurrenthealth
  var pmaxhealth = player.pmaxhealth
  var plevel = player.plevel

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  override def menu(): Unit = {

    printWelcome()

    var continueMenuLoop = true
    while (continueMenuLoop) {
      printMenuOptions()

      var input = StdIn.readLine()
      input match {
        case commandArgPattern(cmd, arg) if cmd == "1" => {
          println("You head to the nearby dungeon to fight monsters")
          continueMenuLoop = false
          val DungeonMenu = new DungeonMenu
          DungeonMenu.menu()
        }
        case commandArgPattern(cmd, arg) if cmd == "2" => {
          println("You head to the town shop")
          continueMenuLoop = false
          val shopmenu = new ShopMenu
          shopmenu.menu()
        }
        case commandArgPattern(cmd, arg) if cmd == "3" => {

          player.HealDamage(1000)

          println(" ")
          println("You recover all your HP")
          println(" ")

          player.SaveState(player.pname, player.pmaxhealth, player.pcurrenthealth, player.pdamage, player.gold, player.plevel, player.uniqueid)

        }
        case commandArgPattern(cmd, arg) if cmd == "4" => {
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

    println(" ")
    println("You're now in town.")
    println("--------------------------------------------------------")
    println(f"You currently have $pcurrenthealth%s HP out of $pmaxhealth%s HP remaining.")
    println(s"You are currently level $plevel")
    println("--------------------------------------------------------")
    println(" ")
  }

  override def printMenuOptions(): Unit = {
    List(
	"                                                         ",
	"        *************************************************",
  "        *            You are currently home             *",
	"        *                                               *",
	"        *                  `'::::.                      *",
  "        *                   _____A_                     *",
  "        *                  /      /\\                    *",
  "        *               __/__/\\__/  \\___                *",
  "        *           ---/__|x '' x| /___/\\----           *",
  "        *              |''|x'||'x| |' '||               *",
  "        *              `^^`^^))^^`^  ^                  *",
	"        *                                               *",
	"        *     1. Dungeon |   Go to fight monsters       *",
	"        *     2. Shop    |   Go to buy or sell items    *",
	"        *     3. Sleep   |   Rest up                    *",
  "        *     4. Exit    |   Exit the Game              *",
	"        *                                               *",
	"        *************************************************"
    ).foreach(println)

  }

}
