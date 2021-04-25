package game

class Warrior {

    var classname = "warrior"
    var health =  10
    var damage =  2
    var speed =  2
    var level = 1

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
