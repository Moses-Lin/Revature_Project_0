package game

object Main {

        var pname = "null"
        var pmaxhealth = 0
        var pcurrenthealth = 0
        var pdamage = 0
        var pspeed = 0
        var plevel = 0
        var uniqueid = "null"

    def main(args: Array[String]):Unit = {

        val filecheck = new FileCheck
        filecheck.check()

        val startmenu = new StartMenu
        startmenu.menu()
    }
}