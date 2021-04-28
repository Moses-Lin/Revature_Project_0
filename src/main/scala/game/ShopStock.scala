package game

import scala.io.StdIn
import java.sql.ResultSet
import game.com.revature.dbcli.PostgreSQLUtil
import scala.util.matching.Regex

class ShopStock {
  
    var itemname = "null"
    var itemdescription = "null"
    var buyprice = 0
    var buff = 0
    
    def LoadState(nameofitem: String): Unit = {
    val conn = PostgreSQLUtil.getConnection()

    val loadstate = conn.prepareStatement("SELECT * FROM shopinventory WHERE itemname = ?")
    loadstate.setString(1, nameofitem)
    loadstate.execute()
    val loadstateres = loadstate.getResultSet()
    loadstateres.next()

    itemname = loadstateres.getString(1)
    itemdescription = loadstateres.getString(2)
    buyprice = loadstateres.getInt(3)
    buff = loadstateres.getInt(4)

    conn.close()

    }
}
