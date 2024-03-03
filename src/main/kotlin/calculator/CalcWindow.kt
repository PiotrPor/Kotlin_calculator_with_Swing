package calculator

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent; //?

class CalcWindow(window_title: String) : JFrame() {
    //var klawisz_dodaj: JButton
    var klawisze_dzialan : Array<JButton>
    var klawisze_cyfr : Array<JButton>

    init {
        setTitle(window_title)
        //
        //klawisz_dodaj = JButton("+")
        klawisze_dzialan = arrayOf(JButton("+"), JButton("-"),JButton("*"),JButton("/"))
        klawisze_cyfr = arrayOfNulls<JButton>(10)
        //
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(800, 700)
        setLocationRelativeTo(null) //window appears in the middle of the screen
    }
}