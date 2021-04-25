package game

case class RandomEnemy(
    name: String,
    health: Int,
    damage: Int,
    speed: Int,
    exp: Int,
    drops: List[String],
    encounter: Int
)