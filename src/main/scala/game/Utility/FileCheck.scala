package game

import java.nio.file.NoSuchFileException
import better.files._

class FileCheck {

  def check(): Unit = {

    try {
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
            ujson.Obj("name" -> "Small Goblin",
                      "health" -> 5,
                      "damage" ->  3,
                      "speed" -> 2,
                      "exp" -> 5)
        )

        val jsonString = os.read(os.pwd/"enemybeginner.json")
        val testread = ujson.read(jsonString)

        if (testread == MinimumFile) {

        println(" ")
        println("All files are in order! Commencing game!")
        println(" ")

        } else {

            val EnemyFile = file"enemybeginner.json"
            EnemyFile.delete()
            os.write(os.pwd/"enemybeginner.json", MinimumFile)

            println(" ")
            println("Something was wrong with the files, but we fixed it. Commencing game!")
            println(" ")
        }

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
                        ujson.Obj("name" -> "Small Goblin",
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
  

