package calculator

import javax.swing.JFrame
import javax.swing.JButton
import javax.swing.JComponent //?

//potrzebne stale
val OdstGorKlaw: Int = 10 //odstep od gornej krawedzi okna
val OdstLewKlaw: Int = 10 //odstep od lewej krawedzi okna
val SzerTekst: Int = 250
val WysTekst: Int = 40
val OdstKlawTekst = 20 //odstep pionowo miedzy polem tekstowym a klawiszami
val SzerKlaw: Int = 50 //szerokosc klawisza
val WysKlaw: Int = 50 //wysokosc klawisza
val OdstPionKlaw: Int = 20 //odstep miedzy klawiszami w osi pionowej
val OdstPozKlaw: Int = 20 //odstep miedzy klawiszami w osi poziomej

class CalcWindow(window_title: String) : JFrame() {
    var klawisze_dzialan : Array<JButton>
    var klawisze_cyfr : Array<JButton>
    var klawisz_kropka : JButton
    var klawisz_rowna_sie : JButton

    init {
        setTitle(window_title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(800, 700)
        setLayout(null)
        setLocationRelativeTo(null) //window appears in the middle of the screen
        //
        klawisze_dzialan = arrayOf(JButton("+"), JButton("-"),JButton("*"),JButton("/"))
        klawisze_cyfr = arrayOf(JButton("0"),
            JButton("1"), JButton("2"), JButton("3"),
            JButton("4"), JButton("5"), JButton("6"),
            JButton("7"), JButton("8"), JButton("9"),
            )
        klawisz_kropka = JButton(".")
        klawisz_rowna_sie = JButton("=")
        var a = 0
        var b = 0
        var staly_pionowy_odstep = OdstGorKlaw+WysTekst+OdstKlawTekst
        for(a in 0..2)
        {
            for(b in 0..2)
            {
                klawisze_cyfr[3*a+b+1].setBounds(
                    OdstLewKlaw+b*(SzerKlaw+OdstPozKlaw),
                    staly_pionowy_odstep+a*(WysKlaw+OdstPionKlaw),
                    SzerKlaw,
                    WysKlaw
                    )
                add(klawisze_cyfr[3*a+b+1])
            }
        }
        klawisze_cyfr[0].setBounds(
            OdstLewKlaw+SzerKlaw+OdstPozKlaw,
            staly_pionowy_odstep+3*(WysKlaw+OdstPionKlaw),
            SzerKlaw,
            WysKlaw
        )
        add(klawisze_cyfr[0])
        for(a in 0..3)
        {
            klawisze_dzialan[a].setBounds(
                OdstLewKlaw+3*(SzerKlaw+OdstPozKlaw),
                staly_pionowy_odstep+a*(WysKlaw+OdstPionKlaw),
                SzerKlaw,
                WysKlaw
            )
            add(klawisze_dzialan[a])
        }
        klawisz_kropka.setBounds(
            OdstLewKlaw+3*(SzerKlaw+OdstPozKlaw),
            staly_pionowy_odstep+3*(WysKlaw+OdstPionKlaw),
            SzerKlaw,
            WysKlaw
        )
        add(klawisz_kropka)
        klawisz_rowna_sie.setBounds(
            OdstLewKlaw,
            staly_pionowy_odstep+3*(WysKlaw+OdstPionKlaw),
            SzerKlaw,
            WysKlaw
        )
        add(klawisz_rowna_sie)
        //
    }
}