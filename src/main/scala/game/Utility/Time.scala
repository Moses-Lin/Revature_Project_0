package game

import java.util.Calendar

// Time stamp utility created in case multiple save states are supported and player needs visual
// confirmation as to which save file is which should names on both save files be the same.
object Time {
    def timestamp(): Unit = {
    val now = Calendar.getInstance()
    val currentmonth = now.get(Calendar.MONTH)
    val currentday = now.get(Calendar.DAY_OF_MONTH)
    val currenthour = now.get(Calendar.HOUR)
    val currentminute = now.get(Calendar.MINUTE)
    val currentime = (currentmonth + "/" + currentday + " " + currenthour + ":" + currentminute)
    }
}
