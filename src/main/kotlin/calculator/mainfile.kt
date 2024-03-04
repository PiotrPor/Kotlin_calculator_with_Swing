package calculator

import java.awt.EventQueue

private fun stworzenieOknaKalkulatora()
{
    var okno = CalcWindow("kalkulator w Kotlin")
    okno.isVisible = true
}

fun main()
{
    EventQueue.invokeLater(::stworzenieOknaKalkulatora)
}