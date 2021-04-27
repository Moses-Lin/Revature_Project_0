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
    val timestamp = Time.timestamp().toString()

    val playername = StdIn.readLine("What's your name adventurer?     ")

    println(" ")
    println(s"Ah, it's nice to meet you $playername.")
    println(" ")
    println("Let us begin.")
    println(" ")

    DAO.SaveState(playername, 10, 10, 2, 2, 1, uuid, timestamp)

    val TownMenu = new TownMenu
    TownMenu.menu()
  }
}
