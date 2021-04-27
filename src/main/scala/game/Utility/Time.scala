package game

import java.util.Calendar

object Time {
    def timestamp(): Unit = {
    val now = Calendar.getInstance()
    val currentmonth = now.get(Calendar.MONTH)
    val currentday = now.get(Calendar.DAY_OF_MONTH)
    val currenthour = now.get(Calendar.HOUR)
    val currentminute = now.get(Calendar.MINUTE)
    val currentime = (currentmonth + "/" + currentday + " " + currenthour + ":" + currentminute)
    println(currentime)
    }
}
