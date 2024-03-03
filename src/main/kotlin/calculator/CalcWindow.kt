package calculator

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent; //?

class CalcWindow(window_title: String) : JFrame() {
    init {
        setTitle(window_title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(600, 600)
        setLocationRelativeTo(null)
    }
}