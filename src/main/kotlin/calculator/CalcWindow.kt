package calculator

import javax.swing.JFrame
import javax.swing.JButton
//import javax.swing.JComponent //?
import javax.swing.JTextArea
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

//potrzebne stale
const val OdstGorKlaw: Int = 10 //odstep od gornej krawedzi okna
const val OdstLewKlaw: Int = 10 //odstep od lewej krawedzi okna
const val SzerTekst: Int = 250
const val WysTekst: Int = 40
const val OdstKlawTekst: Int = 20 //odstep pionowo miedzy polem tekstowym a klawiszami
const val SzerKlaw: Int = 50 //szerokosc klawisza
const val WysKlaw: Int = 50 //wysokosc klawisza
const val OdstPionKlaw: Int = 20 //odstep miedzy klawiszami w osi pionowej
const val OdstPozKlaw: Int = 20 //odstep miedzy klawiszami w osi poziomej
const val staly_pionowy_odstep = OdstGorKlaw+WysTekst+OdstKlawTekst

class CalcWindow(window_title: String) : JFrame(), ActionListener {
    var wyswietlacz : JTextArea
    var klawisze_dzialan : Array<JButton>
    var klawisze_cyfr : Array<JButton>
    var klawisz_kropka : JButton
    var klawisz_rowna_sie : JButton
    var wybrana_operacja : TypDzialania
    var A : Double //liczba po prawej stronie operatora arytmetycznego
    var B : Double //liczba po lewej stronie operatora arytmetycznego
    var C : Double //jakby wynik dzialania

    init {
        setTitle(window_title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(800, 700)
        setLayout(null)
        setLocationRelativeTo(null) //window appears in the middle of the screen
        //
        wyswietlacz = JTextArea(1,11) // 1 row, 11 columns
        klawisze_dzialan = arrayOf(JButton("+"), JButton("-"),JButton("*"),JButton("/"))
        klawisze_cyfr = arrayOf(JButton("0"),
            JButton("1"), JButton("2"), JButton("3"),
            JButton("4"), JButton("5"), JButton("6"),
            JButton("7"), JButton("8"), JButton("9"),
            )
        klawisz_kropka = JButton(".")
        klawisz_rowna_sie = JButton("=")
        wybrana_operacja = TypDzialania.Nic
        //
        wyswietlacz.setBounds(OdstLewKlaw,OdstGorKlaw,SzerTekst,WysTekst)
        add(wyswietlacz)
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
            OdstLewKlaw+2*(SzerKlaw+OdstPozKlaw),
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
        for(a in 0..9)
        {
            klawisze_cyfr[a].addActionListener(this)
        }
        for(a in 0..3)
        {
            klawisze_cyfr[a].addActionListener(this)
        }
        klawisz_kropka.addActionListener(this)
        klawisz_rowna_sie.addActionListener(this)
        //
        A = 0.0
        B = 0.0
        C = 0.0
    }

    fun dopisz_znak(znak: Char)
    {
        var napis: String = wyswietlacz.getText()
        napis += znak.toString()
        wyswietlacz.text = napis
    }

    fun policz() : Double
    {
        var wynik: Double = 0.0
        if(B == 0.0 && wybrana_operacja == TypDzialania.Dzielenie)
        {
            //ustaw bool
        }
        else
        {
            if(wybrana_operacja == TypDzialania.Dodawanie) {wynik=A+B}
            if(wybrana_operacja == TypDzialania.Odejmowanie) {wynik=A-B}
            if(wybrana_operacja == TypDzialania.Mnozenie) {wynik=A*B}
            if(wybrana_operacja == TypDzialania.Dzielenie) {wynik=A/B}
        }
        return wynik
    }

    //
    //funkcja reaguje na zdarzenia
    override fun actionPerformed(wyd: ActionEvent)
    {
        for(a in 0..9)
        {
            if(wyd.getSource() == klawisze_cyfr[a])
            {
                dopisz_znak(a.toChar())
                break
            }
        }
        if(wyd.getSource() == klawisz_kropka)
        {
            dopisz_znak('.')
            //operacje na bool'ach
        }
        if(klawisze_dzialan.contains(wyd.getSource()))
        {
            if(wyd.getSource() == klawisze_dzialan[0]) { wybrana_operacja = TypDzialania.Dodawanie }
            if(wyd.getSource() == klawisze_dzialan[1]) { wybrana_operacja = TypDzialania.Odejmowanie }
            if(wyd.getSource() == klawisze_dzialan[2]) { wybrana_operacja = TypDzialania.Mnozenie }
            if(wyd.getSource() == klawisze_dzialan[3]) { wybrana_operacja = TypDzialania.Dzielenie }
            //operacje na bool'ach
        }
        if(wyd.getSource() == klawisz_rowna_sie)
        {
            B = wyswietlacz.getText().toDouble()
            var wynik : Double = policz()
            //operacje na bool'ach
        }
    }
}