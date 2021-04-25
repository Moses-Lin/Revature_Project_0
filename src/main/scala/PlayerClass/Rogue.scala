package game

class Rogue {

    var classname = "rogue"
    var health =  6
    var damage =  3
    var speed =  3
    var level =  1

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
    def takeDamage (enemydamage: Int): Unit = {
        this.health - enemydamage
    }
    def heal (potionheal: Int): Unit = {
        this.health + potionheal
    }
}
