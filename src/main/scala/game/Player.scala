package game

abstract class Player (name: String) {
    val health: Int
    val damage: Int
    val speed: Int
    val level: Int
    val gold: Int

    def getHealth {
        println(health)
    }
    def getDamage {
        println(damage)
    }
    def getSpeed {
        println(speed)
    }
    def getLevel {
        println(level)
    }
    def getGold {
        println(gold)
    }
    def takeDamage (enemydamage: Int): Unit = {
        this.health - enemydamage
    }
    def heal (potionheal: Int): Unit = {
        this.health + potionheal
    }
}