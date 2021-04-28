package game

import scala.io.StdIn
import java.nio.file.FileAlreadyExistsException
import java.util.UUID

class NewGameMenu extends Menu {

  override def menu(): Unit = {

    // Players are given a randomly generated unique ID in case this application supports multiple save states later on.
    val uuid = UUID.randomUUID().toString().replace("-", "")

    val playername = StdIn.readLine("What's your name adventurer?     ")

    println(" ")
    println(s"Ah, it's nice to meet you $playername.")
    println(" ")
    println("Let us begin.")
    println(" ")

    // Player is given arbitrary values for their stats as well as 20 starting gold.
    val newGame = new Player
    newGame.SaveState(playername, 10, 10, 2, 20, 1, uuid)

    val TownMenu = new TownMenu
    TownMenu.menu()
  }
}
