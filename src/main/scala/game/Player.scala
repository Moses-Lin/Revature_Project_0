package game

case class Player(
    name: String,
    health: Int,
    damage: Int,
    armor: Int,
    speed: Int,
    buffs: List[String]
)