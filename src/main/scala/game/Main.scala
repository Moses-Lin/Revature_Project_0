package game

object Main {

    def main(args: Array[String]):Unit = {

        val filecheck = new FileCheckEnemy
        filecheck.check()

        val toDatabase = new JsonToDatabase
        toDatabase.enemyToDatabase()

        val filecheck2 = new FileCheckShop
        filecheck2.check()

        toDatabase.shopToDatabase()

        val startmenu = new StartMenu
        startmenu.menu()
    }
}