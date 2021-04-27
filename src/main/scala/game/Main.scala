package game

object Main {

    def main(args: Array[String]):Unit = {

        val filecheck = new FileCheck
        filecheck.check()

        val startmenu = new StartMenu
        startmenu.menu()
    }
}