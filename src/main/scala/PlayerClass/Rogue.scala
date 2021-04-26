package game

class Rogue {

    var classname = "rogue"
    var maxhealth =  6
    var currenthealth = 6
    var damage =  3
    var speed =  3
    var level =  1

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
