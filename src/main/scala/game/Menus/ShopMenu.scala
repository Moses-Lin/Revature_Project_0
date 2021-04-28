package game

import scala.util.matching.Regex
import scala.io.StdIn
import game.com.revature.dbcli.PostgreSQLUtil

class ShopMenu extends Menu {

    var player = new Player
    player.LoadState()
    var currentgold = player.gold
  
    val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

    var r = scala.util.Random

    override def menu(): Unit = {

        var continueMenuLoop = true
        while (continueMenuLoop) {    
            printMenuOptions()

            var input = StdIn.readLine()
            input match {
                case commandArgPattern(cmd, arg) if cmd == "1" => {

                    println(" ")
                    println("Purchased!")
                    println(" ")

                    player.SaveState(player.pname, player.pmaxhealth, player.pcurrenthealth, player.pdamage, player.gold, player.plevel, player.uniqueid)                    
                }
                case commandArgPattern(cmd, arg) if cmd == "2" => {
                    
                    println(" ")
                    println("Purchased!")
                    println(" ")

                    player.SaveState(player.pname, player.pmaxhealth, player.pcurrenthealth, player.pdamage, player.gold, player.plevel, player.uniqueid)
                }
                case commandArgPattern(cmd, arg) if cmd == "3" => {
                    
                    println(" ")
                    println("Purchased!")
                    println(" ")

                    player.SaveState(player.pname, player.pmaxhealth, player.pcurrenthealth, player.pdamage, player.gold, player.plevel, player.uniqueid)                    
                }
                case commandArgPattern(cmd, arg) if cmd == "4" => {
                    
                    println(" ")
                    println("Purchased!")
                    println(" ")

                    player.SaveState(player.pname, player.pmaxhealth, player.pcurrenthealth, player.pdamage, player.gold, player.plevel, player.uniqueid)                    
                }
                case commandArgPattern(cmd, arg) if cmd == "5" => {
                    
                    println(" ")
                    println("Purchased!")
                    println(" ")

                    player.SaveState(player.pname, player.pmaxhealth, player.pcurrenthealth, player.pdamage, player.gold, player.plevel, player.uniqueid)                    
                }
                case commandArgPattern(cmd, arg) if cmd == "6" => {
                    
                    println(" ")
                    println("You leave the store.")
                    println(" ")

                    player.SaveState(player.pname, player.pmaxhealth, player.pcurrenthealth, player.pdamage, player.gold, player.plevel, player.uniqueid)

                    continueMenuLoop = false
                    val TownMenu = new TownMenu
                    TownMenu.menu()
                }
                case commandArgPattern(cmd, arg) => {
                    println(" ")
                    println("We don't sell that here. Please pick something in stock.")
                    println(" ")
                }
                case _ => {
                    println("Please enter an option on the menu!")
                }
            }
        }
    }

    override def printWelcome(): Unit = {

    }

    override def printMenuOptions(): Unit = {
        List(
	"                                                                     ",
	"                                                                     ",
	"        *************************************************************",
	"        *                                                           *",
	"        *              You're currently in a shop                   *",
    s"        *           You have $currentgold gold on hand                        *",
    "        *                                                           *",
    "        *               (Y) (X) (=) (>+<) /`-'\\                     *",
    "        *                |   |   |    |   \\,T./                     *",   
    "        *                |   |   |    |     |                       *",
    "        *                                   |                       *",
	"        *                                                           *",
	"        *     1. Potion               | Heals 10 HP (combat)        *",
    "        *     2. Enchanted Stone      | Makes you stronger          *",
    "        *     3. Strange Mushroom     | Makes you stronger          *",
    "        *     4. Bomb                 | Does 10 damage (combat)     *",
    "        *     5. Protecting STone     | Reduce damage taken         *",
    "        *     6. Exit                 | Leave the shop              *",
	"        *                                                           *",
	"        *************************************************************"
    ).foreach(println)
    }
}
