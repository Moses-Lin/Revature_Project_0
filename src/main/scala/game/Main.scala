package game

object Main {

    def main(args: Array[String]):Unit = {

        val filecheck = new FileCheckEnemy
        filecheck.check()

        val filecheck2 = new FileCheckShop
        filecheck2.check()

        val startmenu = new StartMenu
        startmenu.menu()
    }
}