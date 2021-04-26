package game

package com.revature.dbcli

import java.sql.Connection
import java.sql.DriverManager

object PostgreSQLUtil {

  def getConnection(): Connection = {

    classOf[org.postgresql.Driver].newInstance()

    val conn = DriverManager.getConnection(
      "jdbc:postgresql://localhost:5432/postgres",
      "sadcat",
      "gigasadcat"
    )

    conn

    
  }
}