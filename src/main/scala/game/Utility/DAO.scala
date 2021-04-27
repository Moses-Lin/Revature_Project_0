package game

import java.sql.ResultSet
import game.com.revature.dbcli.PostgreSQLUtil

object DAO {
    def viewtest(): Unit = {
    val conn = PostgreSQLUtil.getConnection()

    val stmt = conn.prepareStatement("SELECT * FROM currentplayerstate;")
    stmt.execute()

    val rs = stmt.getResultSet()
    while(rs.next) {
      println(rs.getString("pname"))
    }
    conn.close()
  }

    def SaveState(pname: String, pmaxhealth: Int, pcurrenthealth: Int, pdamage: Int, pspeed: Int, plevel: Int): Unit ={
    val conn = PostgreSQLUtil.getConnection()

    val checkstmt = conn.prepareStatement("SELECT count(*) FROM currentplayerstate;")
    checkstmt.execute()

    val rs = checkstmt.getResultSet()
    rs.next()
    println(rs.getInt(1))

    if(rs.getInt(1) == 0) {
      val insertstmt = conn.prepareStatement("INSERT INTO currentplayerstate VALUES (?, ?, ?, ?, ?, ?);")
      insertstmt.setString(1, pname)
      insertstmt.setInt(2, pmaxhealth)
      insertstmt.setInt(3, pcurrenthealth)
      insertstmt.setInt(4, pdamage)
      insertstmt.setInt(5, pspeed)
      insertstmt.setInt(6, plevel)
      insertstmt.execute()

    conn.close()
   } else {
      val insertstmt2 = conn.prepareStatement("UPDATE currentplayerstate " +
                                              s"SET pname = $pname, " +
                                              s"pmaxhealth = $pmaxhealth, " +
                                              s"pcurrenthealth = $pcurrenthealth, " +
                                              s"pdamage = $pdamage, " +
                                              s"pspeed = $pspeed, " +
                                              s"plevel = $plevel, "
      )
      insertstmt2.execute()

    conn.close()     
   }
 }
}