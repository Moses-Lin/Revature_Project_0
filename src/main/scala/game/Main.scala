package game

object Main {

    def main(args: Array[String]):Unit = {

        // File Check of integrity of enemy JSON file.
        val filecheck = new FileCheckEnemy
        filecheck.check()

        // Reading of enemy JSON file and storage in Postgresql Database.
        val toDatabase = new JsonToDatabase
        toDatabase.enemyToDatabase()

        // File Check of integrity of shop inventory file.
        val filecheck2 = new FileCheckShop
        filecheck2.check()

        // Reading of shop inventory JSON file and storage in Postgresql Database.
        toDatabase.shopToDatabase()

        // Initiate first menu.
        val startmenu = new StartMenu
        startmenu.menu()
    }
}