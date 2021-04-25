package game

case class Player(
    name: String,
    health: Int,
    damage: Int,
    speed: Int,
    level: Int,
    buffs: List[String]
)