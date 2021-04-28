package game

import java.nio.file.NoSuchFileException
import better.files._

class FileCheckShop {

    val MinimumFile = ujson.Arr(
        ujson.Obj("itemname" -> "Potion",
            "itemdescription" -> "Heals you for 10 HP",
            "buyprice" ->  3,
            "buff" -> 10),
        ujson.Obj("itemname" -> "Enchanted Stone",
            "itemdescription" -> "Increases damage permanently",
            "buyprice" ->  10,
            "buff" -> 1),
        ujson.Obj("itemname" -> "Strange Mushroom",
            "itemdescription" -> "Increases max HP permanently",
            "buyprice" ->  10,
            "buff" -> 1),
        ujson.Obj("itemname" -> "Bomb",
            "itemdescription" -> "Deals 10 damage to enemy",
            "buyprice" ->  5,
            "buff" -> 10),
        ujson.Obj("itemname" -> "Protecting Stone",
            "itemdescription" -> "Reduces enemy damage to 0 for one turn",
            "buyprice" ->  10,
            "buff" -> 100)
        )

  def check(): Unit = {

    try {

        val jsonString = os.read(os.pwd/"shopinventory.json")
        val testread = ujson.read(jsonString)

        if (testread == MinimumFile) {

        println(" ")
        println("All files are in order! Commencing game!")
        println(" ")

        } else {

            val shopFile = file"shopinventory.json"
            shopFile.delete()
            os.write(os.pwd/"shopinventory.json", MinimumFile)

            println(" ")
            println("Something was wrong with the files, but we fixed it. Commencing game!")
            println(" ")
        }

        } catch {

            case nsfe: NoSuchFileException => {

            os.write(os.pwd/"shopinventory.json", MinimumFile)
            
            }
        }
    }
}
