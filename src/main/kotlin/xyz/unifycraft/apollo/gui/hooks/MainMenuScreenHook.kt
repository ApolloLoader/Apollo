package xyz.unifycraft.apollo.gui.hooks

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.dsl.*
import java.awt.Color

class MainMenuScreenHook : GuiHook() {
    init {
        val block = UIBlock(Color.CYAN).constrain {
            x = 2.pixels()
            y = 5.pixels()
            width = 10.pixels()
            height = 10.pixels()
        } childOf window
    }
}