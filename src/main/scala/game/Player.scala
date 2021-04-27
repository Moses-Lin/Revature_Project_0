package game

import scala.io.StdIn
import java.sql.ResultSet
import game.com.revature.dbcli.PostgreSQLUtil
import scala.util.matching.Regex
import upickle.default

class Player {

  var pname = "null"
  var pmaxhealth = 0
  var pcurrenthealth = 0
  var pdamage = 0
  var pspeed = 0
  var plevel = 0
  var uniqueid = "null"

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
      } else {

          val insertstmt2 = conn.prepareStatement("UPDATE currentplayerstate SET pmaxhealth = ?, pcurrenthealth = ?, pdamage =?, pspeed = ?, plevel = ? WHERE pname = ? AND uniqueid = ?")

          insertstmt2.setInt(1, pmaxhealth)
          insertstmt2.setInt(2, pcurrenthealth)
          insertstmt2.setInt(3, pdamage)
          insertstmt2.setInt(4, pspeed)
          insertstmt2.setInt(5, plevel)
          insertstmt2.setString(7, pname) 
          insertstmt2.setString(8, uniqueid) 
          insertstmt2.execute()

          conn.close() 
        }
      }    
 
  def LoadState(): Unit = {
      val conn = PostgreSQLUtil.getConnection()

      val loadstate = conn.prepareStatement("SELECT * FROM currentplayerstate")
      loadstate.execute()
      val loadstateres = loadstate.getResultSet()
      loadstateres.next()

      pname = loadstateres.getString(1)
      pmaxhealth = loadstateres.getInt(2)
      pcurrenthealth = loadstateres.getInt(3)
      pdamage = loadstateres.getInt(4)
      pspeed = loadstateres.getInt(5)
      plevel = loadstateres.getInt(6)
      uniqueid = loadstateres.getString(7)



      }
}
      
    
