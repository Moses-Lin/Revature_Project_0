package game

class Warrior {

    var classname = "warrior"
    var maxhealth: Int =  10
    var currenthealth: Int = 10
    var damage: Int =  2
    var speed: Int =  2
    var level: Int = 1

    def getHealth {
        println(currenthealth)
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
        this.currenthealth - enemydamage
    }
    def heal (potionheal: Int): Unit = {
        this.currenthealth + potionheal
    }
}
