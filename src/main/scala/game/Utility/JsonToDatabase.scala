package game

import scala.io.StdIn
import game.com.revature.dbcli.PostgreSQLUtil

class JsonToDatabase {
  
    def enemyToDatabase(): Unit = {

        val conn = PostgreSQLUtil.getConnection()
        val checkstmt = conn.prepareStatement("SELECT count(*) FROM enemy;")
        checkstmt.execute()
        val rs = checkstmt.getResultSet()
        rs.next()

        val jsonString = os.read(os.pwd/"enemybeginner.json")
        val weakenemystats = ujson.read(jsonString)
        var i = 0

        if(rs.getInt(1) == 0) {
                    
            try {
                while (i > -1) {
                    weakenemystats(i)
                    i = i + 1
                }
            } catch {
                case ioobe: IndexOutOfBoundsException => {
                    for (x <- 0 to (i - 1)) {

                        var insertstmt = conn.prepareStatement("INSERT INTO enemy VALUES (?, ?, ?, ?, ?)")
                        insertstmt.setString(1, weakenemystats(x)("name").toString().replace("\"", ""))
                        insertstmt.setInt(2, weakenemystats(x)("health").toString().toInt)
                        insertstmt.setInt(3, weakenemystats(x)("damage").toString().toInt)
                        insertstmt.setInt(4, weakenemystats(x)("golddrop").toString().toInt)
                        insertstmt.setInt(5, weakenemystats(x)("exp").toString().toInt)
                        insertstmt.execute()

                    }

                }
            } finally {
                conn.close()
            }
        } else {

            println(" ")
            println("Files already stored to database! Moving on.")
            println(" ")
        
        }
    }
    def shopToDatabase(): Unit = {

    val conn = PostgreSQLUtil.getConnection()
    val checkstmt = conn.prepareStatement("SELECT count(*) FROM shopinventory;")
    checkstmt.execute()
    val rs = checkstmt.getResultSet()
    rs.next()

    val jsonString = os.read(os.pwd/"shopinventory.json")
    val shopinventorystats = ujson.read(jsonString)
    var i = 0

    if(rs.getInt(1) == 0) {
                    
        try {
            while (i > -1) {
                shopinventorystats(i)
                i = i + 1
            }
        } catch {
            case ioobe: IndexOutOfBoundsException => {
                for (x <- 0 to (i - 1)) {

                    var insertstmt = conn.prepareStatement("INSERT INTO shopinventory VALUES (?, ?, ?, ?)")
                    insertstmt.setString(1, shopinventorystats(x)("itemname").toString().replace("\"", ""))
                    insertstmt.setString(2, shopinventorystats(x)("itemdescription").toString())
                    insertstmt.setInt(3, shopinventorystats(x)("buyprice").toString().toInt)
                    insertstmt.setInt(4, shopinventorystats(x)("buff").toString().toInt)
                    insertstmt.execute()

                }

            }
        } finally {
            conn.close()
        }
    } else {

        println(" ")
        println("Files already stored to database! Moving on.")
        println(" ")
        
        }
    }
}