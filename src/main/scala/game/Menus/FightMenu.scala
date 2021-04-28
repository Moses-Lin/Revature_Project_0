package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map
import game.com.revature.dbcli.PostgreSQLUtil

class FightMenu extends Menu{

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  val e = new scala.util.Random
  val randomEnemySelectValue = e.nextInt((4)) + 1

  val conn = PostgreSQLUtil.getConnection()

  val loadenemy = conn.prepareStatement("Select * From (Select Row_Number() Over (Order By name) As RowNum, * From enemy) t2 Where RowNum = ?")
  loadenemy.setInt(1, randomEnemySelectValue)
  loadenemy.execute()
  val loadenemyres = loadenemy.getResultSet()
  loadenemyres.next()
  var enemyname = loadenemyres.getString(2)
  conn.close()

  var randomenemy = new RandomEnemy
  randomenemy.LoadState(enemyname)
  var randomEnemyName = randomenemy.ename
  var randomEnemyHP = randomenemy.ehealth
  var randomEnemyDamage = randomenemy.edamage

  var player = new Player
  player.LoadState()
  var playerdamage = player.pdamage
  var playerHPleft = player.pcurrenthealth

  override def menu(): Unit = {
    
    while (randomEnemyHP > 0) {

      println(" ")
      println(f"It seems that a $randomEnemyName%s has appeared. It seems hostile! ")
      println(" ")

      printMenuOptions()
      
      var input = StdIn.readLine("What will you do?  (Select 1, 2, or 3)    ")

      input match {
        case commandArgPattern(cmd, arg) if cmd == "1" => {

          randomEnemyHP = randomEnemyHP - playerdamage
          playerHPleft = playerHPleft - randomEnemyDamage

          println(" ")
          println(f"You attack the $randomEnemyName%s for $playerdamage%s! It has $randomEnemyHP%s HP left! ")
          println(" ")
          println(f"You are hit for $randomEnemyDamage%s! You have $playerHPleft%s HP left!")
          println(" ")

          player.SaveState(player.pname, player.pmaxhealth, playerHPleft, player.pdamage, player.gold, player.plevel, player.uniqueid)

          if (playerHPleft <= 0) {

            randomEnemyHP = 0

            println(" ")
            println(" ---------------------------GAME OVER--------------------------- ")
            println(" ")
          } else if (randomEnemyHP <= 0) {

            println(" ")
            println(" You won the fight! Returning to dungeon entrance...")
            println(" ")

            player.SaveState(player.pname, player.pmaxhealth, playerHPleft, player.pdamage, player.gold, player.plevel, player.uniqueid)

            val DungeonMenu = new DungeonMenu
            DungeonMenu.menu()
          }

        }
        case commandArgPattern(cmd, arg) if cmd == "2" => {
          println(" ")
          println("You open your inventory")
          println(" ")
        }
        case commandArgPattern(cmd, arg) if cmd == "3" => {

          randomenemy.ehealth = 0

          println(" ")
          println("You escaped back to the dungeon entrance! ")
          println(" ")

          val DungeonMenu = new DungeonMenu
          DungeonMenu.menu()

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

  override def printMenuOptions(): Unit = {
    List(
	"                                                         ",
	"                                                         ",
	"        *************************************************",
	"        *                                               *",
	"        *         You're currently fighting             *",
	"        *                                               *",
	"        *     1. Fight  |   Attack the enemy            *",
  "        *     2. Item   |   Use an item                 *",
  "        *     3. Run    |   Exit the Game               *",
	"        *                                               *",
	"        *************************************************"
    ).foreach(println)
  }

}
