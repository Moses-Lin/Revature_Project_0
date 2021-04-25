package game

class Warrior {

    var classname = "warrior"
    var health: Int =  10
    var damage: Int =  2
    var speed: Int =  2
    var level: Int = 1

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
