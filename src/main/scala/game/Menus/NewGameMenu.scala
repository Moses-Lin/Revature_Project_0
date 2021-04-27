package game

import scala.io.StdIn
import scala.collection.mutable.Map
import scala.annotation.varargs
import java.nio.file.FileAlreadyExistsException
import better.files._
import java.util.UUID

class NewGameMenu extends Menu {

  override def menu(): Unit = {

    val uuid = UUID.randomUUID().toString().replace("-", "")

    val playername = StdIn.readLine("What's your name adventurer?     ")

    println(" ")
    println(s"Ah, it's nice to meet you $playername.")
    println(" ")
    println("Let us begin.")
    println(" ")

    val newGame = new Player
    newGame.SaveState(playername, 10, 10, 2, 2, 1, uuid)

    val TownMenu = new TownMenu
    TownMenu.menu()
  }
}
