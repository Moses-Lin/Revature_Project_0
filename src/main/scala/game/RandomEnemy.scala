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

    def SaveEnemy() {
      val conn = PostgreSQLUtil.getConnection()

      val checkstmt = conn.prepareStatement("SELECT count(*) FROM enemy;")
      checkstmt.execute()
      val rs = checkstmt.getResultSet()
      rs.next()

       if(rs.getInt(1) == 0) {
  
        }
    }

}