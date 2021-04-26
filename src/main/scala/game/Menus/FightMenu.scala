package game

import scala.io.StdIn
import scala.util.matching.Regex
import scala.collection.mutable.Map

class FightMenu extends Menu{

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  val jsonString = os.read(os.pwd/"CurrentPlayerState.json")
  val currentstats = ujson.read(jsonString)

  val jsonString2 = os.read(os.pwd/"enemybeginner.json")
  val weakenemystats = ujson.read(jsonString2)

  val e = new scala.util.Random
  val enemyselect = e.nextInt((4))

  override def menu(): Unit = {
    

    // Converting json file enemy into a case class for use.
    val randomenemyname = weakenemystats(enemyselect)("name")
    
    var randomenemyhealth = weakenemystats(enemyselect)("health")
    var randomenemyhealth2 = randomenemyhealth.toString()
    var randomenemyhealth3 = randomenemyhealth2.toInt

    val randomenemydamage = weakenemystats(enemyselect)("damage")
    val randomenemydamage2 = randomenemydamage.toString()
    val randomenemydamage3 = randomenemydamage2.toInt

    val randomenemyspeed = weakenemystats(enemyselect)("speed")
    val randomenemyspeed2 = randomenemyspeed.toString()
    val randomenemyspeed3 = randomenemyspeed2.toInt

    val randomenemyexp= weakenemystats(enemyselect)("exp")
    val randomenemyexp2 = randomenemyexp.toString()
    val randomenemyexp3 = randomenemyexp2.toInt

    var randomenemy = RandomEnemy(randomenemyname.toString(), 
                                  randomenemyhealth3,
                                  randomenemydamage3,
                                  randomenemyspeed3,
                                  randomenemyexp3
                                   )

    var EncounterHP = randomenemy.health                              
    val EncounterDamage = randomenemy.damage                               
    val EncounterName = randomenemy.name
    
    // Player stats during combat.
    val playerhealth = currentstats("currenthealth")
    val playerhealth2 = playerhealth.toString()
    var playerhealth3 = playerhealth2.toInt

    val playerdamage = currentstats("damage")
    val playerdamage2 = playerdamage.toString()
    val playerdamage3 = playerdamage2.toInt

    val playermaxhealth = currentstats("maxhealth")

    println("-------------------------------------------------------------------------")
    println(" ")
    println(s"A $EncounterName has appeared! It seems hostile.")
    println(" ")
    println("-------------------------------------------------------------------------")

    //var continueMenuLoop = true
    //while (continueMenuLoop) {
      while (EncounterHP > 0) {
      
      printMenuOptions()

      var input = StdIn.readLine()
      input match {
        case "Fight" => {

          EncounterHP = EncounterHP - playerdamage3
          playerhealth3 = playerhealth3 - randomenemy.damage

          println(" ")
          println(f"You hit the $EncounterName%s for $playerdamage3%s.")
          println(f"The $EncounterName%s has $EncounterHP%s HP left.")
          println(s"You are hit for $EncounterDamage.")
          println(f"You have $playerhealth3%s HP out of $playermaxhealth%s HP left")
          println(" ")

          if (playerhealth3 < 0) {
            println(" ")
            println("You have been defeated... GAME OVER")
            println(" ")
            EncounterHP = 0
            val startmenu = new StartMenu
            startmenu.menu()

          } else if (EncounterHP < 1) {
            println(" ")
            println("You have won the fight!")
            println(" ")
            
            val DungeonMenu = new DungeonMenu
            DungeonMenu.menu()

          } else {
            println("The fight continues.")
          }
          

        }

        case commandArgPattern(cmd, arg) if cmd == "Item" => {
          println("You use an item")
            // placeholder
        }

        case commandArgPattern(cmd, arg) if cmd == "Run" => {
          println("You escape")
          EncounterHP = 0
          val DungeonMenu = new DungeonMenu
          DungeonMenu.menu()
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
