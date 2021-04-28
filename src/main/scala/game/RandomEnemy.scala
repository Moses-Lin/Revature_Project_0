package game

case class RandomEnemy(
    val name: String,
    var health: Int,
    val damage: Int,
    val golddrop: Int,
    val exp: Int
)