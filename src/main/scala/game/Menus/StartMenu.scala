package game

import scala.util.matching.Regex
import scala.io.StdIn
import game.com.revature.dbcli.PostgreSQLUtil

class StartMenu extends Menu {

    val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

    override def menu(): Unit ={
        printWelcome()

        val conn = PostgreSQLUtil.getConnection()
        val checkstmt = conn.prepareStatement("SELECT count(*) FROM currentplayerstate;")
        checkstmt.execute()
        val rs = checkstmt.getResultSet
        rs.next()

        var continueMenuLoop = true
        while (continueMenuLoop) {    
            printMenuOptions()

            var input = StdIn.readLine()
            input match {
                case commandArgPattern(cmd, arg) if cmd == "New_Game" => {

                    if(rs.getInt(1) == 0) {

                        println(" ")
                        println("Ah, a new adventurer. Let's begin.")
                        println(" ")

                        continueMenuLoop = false
                        conn.close()
                        val NewGameMenu = new NewGameMenu
                        NewGameMenu.menu()
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

                    if(rs.getInt(1) == 0) {

                        println(" ")
                        println("It seems you don't have a save file yet. Please select New_Game in the title screen.")
                        println(" ")

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
                case commandArgPattern(cmd, arg) if cmd == "debug" => {
                    val conn = PostgreSQLUtil.getConnection()


                    val jsonString = os.read(os.pwd/"enemybeginner.json")
                    val weakenemystats = ujson.read(jsonString)
                    var i = 0
                    
                    try {
                        while (i > -1) {
                            weakenemystats(i)
                            i = i + 1
                        }
                    } catch {
                        case ioobe: IndexOutOfBoundsException => {
                            for (x <- 0 to (i - 1)) {
                                println(x)

                                var insertstmt = conn.prepareStatement("INSERT INTO enemy VALUES (?, ?, ?, ?, ?)")
                                insertstmt.setString(1, weakenemystats(x)("name").toString())
                                insertstmt.setInt(2, weakenemystats(x)("health").toString().toInt)
                                insertstmt.setInt(3, weakenemystats(x)("damage").toString().toInt)
                                insertstmt.setInt(4, weakenemystats(x)("golddrop").toString().toInt)
                                insertstmt.setInt(5, weakenemystats(x)("exp").toString().toInt)
                                insertstmt.execute()

                                println(s"this is iteration $x")

                            }

                        }
                    } finally {
                        conn.close()
                    }
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