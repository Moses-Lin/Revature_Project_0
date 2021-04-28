package game

import scala.util.matching.Regex
import scala.io.StdIn
import game.com.revature.dbcli.PostgreSQLUtil

class StartMenu extends Menu {

    val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

    override def menu(): Unit ={
        printWelcome()

        // Establishing connection to obtain count to determine if a save file exists or not.
        val conn = PostgreSQLUtil.getConnection()
        val checkstmt = conn.prepareStatement("SELECT count(*) FROM currentplayerstate;")
        checkstmt.execute()
        val rs = checkstmt.getResultSet
        rs.next()

        // Menu that loops as long as continueMenuLoop is specified to be true.
        var continueMenuLoop = true
        while (continueMenuLoop) {    
            printMenuOptions()

            var input = StdIn.readLine()
            input match {
                case commandArgPattern(cmd, arg) if cmd == "New_Game" => {

                    // If the number returned is 0, there is no save file, so user will be brought to menu to create save state.
                    if(rs.getInt(1) == 0) {

                        println(" ")
                        println("Ah, a new adventurer. Let's begin.")
                        println(" ")

                        continueMenuLoop = false
                        conn.close()
                        val NewGameMenu = new NewGameMenu
                        NewGameMenu.menu()

                    // The game only allows one save file, hence why it is only else.
                    } else {
                        println(" ")
                        val overwrite = StdIn.readLine("Ah, adventurer, you may only have one save file! Would you like to overwrite it? (Y/N)  ")
                        println(" ") 

                        overwrite match {
                            case commandArgPattern(cmd, arg) if cmd == "Y" => {

                                println(" ")
                                println("Very well adventurer, let us begin anew...  ")
                                println(" ")

                                continueMenuLoop = false
                                conn.close()
                                val NewGameMenu = new NewGameMenu
                                NewGameMenu.menu()              
                            }
                            case commandArgPattern(cmd, arg) if cmd == "N" => {

                                println(" ")
                                println("Very well adventurer, I shall bring you back to the title screen.")
                                println(" ")
                                conn.close()

                            }
                            // Cases past this point just to deal with if input isnt correctly specified.
                            case commandArgPattern(cmd, arg) => {
                                println(" ")
                                println("I'm not sure what that means!")
                                println("I shall bring you back to the title screen")
                                println(" ")
                                conn.close()
                            }
                            case _ => {
                                println("Please enter an option on the menu!")
                                conn.close()
                            }
                        }
                    }
                }
                case commandArgPattern(cmd, arg) if cmd == "Saved_Game" => {

                    // Check if there is a save file or not when selecting this option.
                    if(rs.getInt(1) == 0) {

                        println(" ")
                        println("It seems you don't have a save file yet. Please select New_Game in the title screen.")
                        println(" ")

                    // If there is a save file, it will bring user to main game menu, as there is a load state in that menu.
                    } else {

                    continueMenuLoop = false
                    conn.close()

                    val TownMenu = new TownMenu
                    TownMenu.menu()

                    }
                }
                case commandArgPattern(cmd, arg) if cmd == "Exit" => {
                    continueMenuLoop = false
                }

                case commandArgPattern(cmd, arg) => {
                    println(" ")
                    println("I'm not sure what that means!")
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