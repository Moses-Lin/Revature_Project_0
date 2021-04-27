package game

import scala.io.StdIn
import java.sql.ResultSet
import game.com.revature.dbcli.PostgreSQLUtil
import scala.util.matching.Regex

object DAO {

    val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

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

    def SaveState(pname: String, pmaxhealth: Int, pcurrenthealth: Int, pdamage: Int, pspeed: Int, plevel: Int, uniqueid: String, timestamp: String): Unit ={
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
      val insertstmt = conn.prepareStatement("INSERT INTO currentplayerstate VALUES (?, ?, ?, ?, ?, ?, ?, ?);")
      insertstmt.setString(1, pname)
      insertstmt.setInt(2, pmaxhealth)
      insertstmt.setInt(3, pcurrenthealth)
      insertstmt.setInt(4, pdamage)
      insertstmt.setInt(5, pspeed)
      insertstmt.setInt(6, plevel)
      insertstmt.setString(7, uniqueid)
      insertstmt.setString(8, timestamp)
      insertstmt.execute()

    conn.close()
   } else if (rs2.getBoolean(1)) {
      val insertstmt2 = conn.prepareStatement("UPDATE currentplayerstate SET pmaxhealth = ?, pcurrenthealth = ?, pdamage =?, pspeed = ?, plevel = ?, timestamp = ? WHERE pname = ? AND uniqueid = ?")
      insertstmt2.setInt(1, pmaxhealth)
      insertstmt2.setInt(2, pcurrenthealth)
      insertstmt2.setInt(3, pdamage)
      insertstmt2.setInt(4, pspeed)
      insertstmt2.setInt(5, plevel)
      insertstmt2.setString(6, timestamp) 
      insertstmt2.setString(7, pname) 
      insertstmt2.setString(8, uniqueid) 
      insertstmt2.execute()

    conn.close()     
   } else {
      val insertstmt = conn.prepareStatement("INSERT INTO currentplayerstate VALUES (?, ?, ?, ?, ?, ?, ?, ?);")
      insertstmt.setString(1, pname)
      insertstmt.setInt(2, pmaxhealth)
      insertstmt.setInt(3, pcurrenthealth)
      insertstmt.setInt(4, pdamage)
      insertstmt.setInt(5, pspeed)
      insertstmt.setInt(6, plevel)
      insertstmt.setString(7, uniqueid)
      insertstmt.setString(8, timestamp)
      insertstmt.execute()
   }
 }
    def LoadState(): Unit ={
      val conn = PostgreSQLUtil.getConnection()

      val checkstmt = conn.prepareStatement("SELECT pname, timestamp FROM currentplayerstate;")
      checkstmt.execute()

      val rs = checkstmt.getResultSet()

      while(rs.next()) {

        println("===========================")
        println("=    " + rs.getString(1) + " | " + rs.getString(2))
        println("===========================")
      }

      val numberoffiles = conn.prepareStatement("SELECT count(*) FROM currentplayerstate;")
      numberoffiles.execute()

      val numfile = numberoffiles.getResultSet()
      numfile.next()
      
      val FileIndex = numfile.getInt(1)

      if (FileIndex == 0) {

        println(" ")
        println("It looks like you don't have any save files yet! Sending you back to the menu screen.")
        println(" ")

      } else if (FileIndex == 1) {
        
        println(" ")
        var fileselect = StdIn.readLine("Please select a load file! (Type 1)   ")
        println(" ")

        fileselect match {
          case commandArgPattern(cmd, arg) if cmd == "1" => {

            val savefileload = conn.prepareStatement("select * from (select ROW_NUMBER () OVER (ORDER BY pname) AS RowNum, * from currentplayerstate) sub where RowNum = 1")
            savefileload.execute()
            val savefileloadres = savefileload.getResultSet()
            savefileloadres.next()

            val TownMenu = new TownMenu
            TownMenu.menu()

            // Starting at 2 because 1 is the Row Number in the SQL window.
            var pname = savefileloadres.getString(2)
            var pcurrenthealth = savefileloadres.getInt(3)
            var pmaxhealth = savefileloadres.getInt(4)
            var pdamage = savefileloadres.getInt(5)
            var pspeed = savefileloadres.getInt(6)
            var plevel = savefileloadres.getInt(7)
            val uniqueid = savefileloadres.getString(8)
        } 
                case commandArgPattern(cmd, arg) => {
          println(" ")
          println(
            s"I'm not sure what that means!"
          )
          println("Please enter a valid option.")
          println(" ")
        }
        case _ => { 
          println("Please enter an option on the menu!")
        }

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
}