package xyz.unifycraft.apollo.gui.hooks

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.components.Window
import gg.essential.universal.UMatrixStack

abstract class GuiHook {
    val window = Window(ElementaVersion.V1)
    fun onInitialize() = window.onWindowResize()
    fun onScreenDrawn() = window.draw(UMatrixStack.Compat.get())
    fun onKeyTyped(typedChar: Char, keyCode: Int) = window.keyType(typedChar, keyCode)
    fun onMouseClicked(mouseX: Double, mouseY: Double, mouseButton: Int) = window.mouseClick(mouseX, mouseY, mouseButton)
}