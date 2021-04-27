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

    def SaveState(pname: String, pmaxhealth: Int, pcurrenthealth: Int, pdamage: Int, pspeed: Int, plevel: Int, uniqueid: String): Unit ={
    val conn = PostgreSQLUtil.getConnection()

    val checkstmt = conn.prepareStatement("SELECT count(*) FROM currentplayerstate;")
    val validationcheck = conn.prepareStatement("SELECT EXISTS(SELECT 1 FROM currentplayerstate WHERE pname = ? and uniqueid = ?) FROM currentplayerstate;")
    validationcheck.setString(1, pname)
    validationcheck.setString(2, uniqueid)

    checkstmt.execute()
    validationcheck.execute()

    val rs = checkstmt.getResultSet()
    val rs2 = validationcheck.getResultSet()
    rs.next()
    rs2.next()

    if(rs.getInt(1) == 0) {
      val insertstmt = conn.prepareStatement("INSERT INTO currentplayerstate VALUES (?, ?, ?, ?, ?, ?, ?);")
      insertstmt.setString(1, pname)
      insertstmt.setInt(2, pmaxhealth)
      insertstmt.setInt(3, pcurrenthealth)
      insertstmt.setInt(4, pdamage)
      insertstmt.setInt(5, pspeed)
      insertstmt.setInt(6, plevel)
      insertstmt.setString(7, uniqueid)
      insertstmt.execute()

    conn.close()
   } else if (rs2.getBoolean(1)) {
      val insertstmt2 = conn.prepareStatement("UPDATE currentplayerstate SET pmaxhealth = ?, pcurrenthealth = ?, pdamage =?, pspeed = ?, plevel = ? WHERE pname = ? AND uniqueid = ?")
      insertstmt2.setInt(1, pmaxhealth)
      insertstmt2.setInt(2, pcurrenthealth)
      insertstmt2.setInt(3, pdamage)
      insertstmt2.setInt(4, pspeed)
      insertstmt2.setInt(5, plevel)
      insertstmt2.setString(6, pname) 
      insertstmt2.setString(7, uniqueid) 
      insertstmt2.execute()

    conn.close()     
   } else {
      val insertstmt = conn.prepareStatement("INSERT INTO currentplayerstate VALUES (?, ?, ?, ?, ?, ?, ?);")
      insertstmt.setString(1, pname)
      insertstmt.setInt(2, pmaxhealth)
      insertstmt.setInt(3, pcurrenthealth)
      insertstmt.setInt(4, pdamage)
      insertstmt.setInt(5, pspeed)
      insertstmt.setInt(6, plevel)
      insertstmt.setString(7, uniqueid)
      insertstmt.execute()
   }
 }
    def LoadState(pname: String, uniqueid: String): Unit ={
      val conn = PostgreSQLUtil.getConnection()

      val checkstmt = conn.prepareStatement("SELECT pname FROM currentplayerstate;")
      checkstmt.execute()

      val rs = checkstmt.getResultSet()
      while(rs.next()) {
        println("===========================")
        println(rs.getString(1))
        println("===========================")
      }



    }

    def CurrentDisplay(): Unit = {
      val conn = PostgreSQLUtil.getConnection()  

    }

    def SaveGold(): Unit = {
      val conn = PostgreSQLUtil.getConnection()  
      
    }

    def LoadGold(): Unit = {
      val conn = PostgreSQLUtil.getConnection()  
      
    }
}