package game

import java.sql.ResultSet
import game.com.revature.dbcli.PostgreSQLUtil

object DAO {
    def viewAll(): Unit ={
    val conn = PostgreSQLUtil.getConnection()

    val stmt = conn.prepareStatement("SELECT * FROM currentplayerstate;")
    stmt.execute()

    val rs = stmt.getResultSet()
    while(rs.next) {
      println(rs.getString("pname"))
    }
    conn.close()
  }
}
