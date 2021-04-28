package game

import scala.io.StdIn
import java.sql.ResultSet
import game.com.revature.dbcli.PostgreSQLUtil
import scala.util.matching.Regex

class RandomEnemy {

    var ename = "null"
    var ehealth = 0
    var edamage = 0
    var egolddrop = 0
    var eexp = 0

    def TakeDamage(damage: Int): Unit = {

        ehealth = ehealth - damage

    }
    def LoadState(enemyname: String): Unit = {
    val conn = PostgreSQLUtil.getConnection()

    val loadstate = conn.prepareStatement("SELECT * FROM enemy WHERE name = ?")
    loadstate.setString(1, enemyname)
    loadstate.execute()
    val loadstateres = loadstate.getResultSet()
    loadstateres.next()

    ename = loadstateres.getString(1)
    ehealth = loadstateres.getInt(2)
    edamage = loadstateres.getInt(3)
    egolddrop = loadstateres.getInt(4)
    eexp = loadstateres.getInt(5)

    conn.close()

    }
  
}