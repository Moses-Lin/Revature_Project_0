package game

case class RandomEnemy(
    name: String,
    health: Int,
    damage: Int,
    armor: Int,
    speed: Int,
    drops: List[String]
)