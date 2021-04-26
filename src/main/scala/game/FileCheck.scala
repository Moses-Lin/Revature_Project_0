package game

import java.nio.file.NoSuchFileException

class FileCheck {

  def check(): Unit = {

    try {

    val jsonString = os.read(os.pwd/"enemybeginner.json")
    val testread = ujson.read(jsonString)
    
    println(" ")
    println("All files are in order!")
    println(" ")

    } catch {

        case nsfe: NoSuchFileException => {
            val MinimumFile = ujson.Arr(
            ujson.Obj("name" -> "Mouse",
                                "health" -> 2,
                                "damage" ->  1,
                                "speed" -> 2,
                                "exp" -> 1),
            ujson.Obj("name" -> "Small Slime",
                                "health" -> 5,
                                "damage" ->  2,
                                "speed" -> 1,
                                "exp" -> 2),
            ujson.Obj("name" -> "Small Spider",
                                "health" -> 4,
                                "damage" ->  1,
                                "speed" -> 3,
                                "exp" -> 1),
            ujson.Obj("name" -> "Goblin",
                                "health" -> 5,
                                "damage" ->  3,
                                "speed" -> 2,
                                "exp" -> 5)
            )
            os.write(os.pwd/"enemybeginner.json", MinimumFile)
        }
    }

  }
}
