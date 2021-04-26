package game

case class RandomEnemy(
    val name: String,
    var health: Int,
    val damage: Int,
    val speed: Int,
    val exp: Int
)