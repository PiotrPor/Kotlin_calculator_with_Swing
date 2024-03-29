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
const val SzerTekst: Int = 360
const val WysTekst: Int = 40
const val OdstKlawTekst: Int = 20 //odstep pionowo miedzy polem tekstowym a klawiszami
const val SzerKlaw: Int = 75 //50 //szerokosc klawisza
const val WysKlaw: Int = 75 //50 //wysokosc klawisza
const val OdstPionKlaw: Int = 20 //odstep miedzy klawiszami w osi pionowej
const val OdstPozKlaw: Int = 20 //odstep miedzy klawiszami w osi poziomej
const val staly_pionowy_odstep = OdstGorKlaw+WysTekst+OdstKlawTekst

class CalcWindow(window_title: String) : JFrame(), ActionListener {
    // ZMIENNE
    var wyswietlacz : JTextArea
    var klawisze_dzialan : Array<JButton>
    var klawisze_cyfr : Array<JButton>
    var klawisz_kropka : JButton
    var klawisz_rowna_sie : JButton
    var wybrana_operacja : TypDzialania
    var A : Double //liczba po prawej stronie operatora arytmetycznego
    var B : Double //liczba po lewej stronie operatora arytmetycznego
    //var C : Double //jakby wynik dzialania
    //
    var wpisano_pierwsza_cyfre : Boolean
    var wpisano_kropke : Boolean
    var wpisano_pierwsza_liczbe : Boolean
    var wpisano_druga_liczbe : Boolean
    var dzielenie_przez_0 : Boolean

    // KONSTRUKTOR itd
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
        wyswietlacz.font = java.awt.Font.decode("Arial Black") //?
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
        //C = 0.0
        dopisz_znak('0')
        //
        wpisano_pierwsza_cyfre = false
        wpisano_kropke = false
        wpisano_pierwsza_liczbe = false
        wpisano_druga_liczbe = false
        dzielenie_przez_0 = false
    }

    // FUNKCJE
    fun dopisz_znak(znak: Char)
    {
        var napis: String = wyswietlacz.getText()
        napis += znak.toString()
        wyswietlacz.text = napis
    }

    fun napisz_komunikat(wpis : String)
    {
        wyswietlacz.text = wpis
    }

    fun policz() : Double
    {
        var wynik: Double = 0.0
        if(B == 0.0 && wybrana_operacja == TypDzialania.Dzielenie)
        {
            dzielenie_przez_0 = true
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

    //funkcja reaguje na zdarzenia
    override fun actionPerformed(wyd: ActionEvent)
    {
        for(a in 0..9)
        {
            if(wyd.getSource() == klawisze_cyfr[a])
            {
                if(dzielenie_przez_0) {dzielenie_przez_0 = false}
                dopisz_znak(a.toChar())
                break
            }
        }
        if(wyd.getSource() == klawisz_kropka)
        {
            if(!wpisano_kropke)
            {
                dopisz_znak('.')
                wpisano_kropke = true
            }
        }
        if(klawisze_dzialan.contains(wyd.getSource()))
        {
            //B = wyswietlacz.getText().toDouble()
            if(wyd.getSource() == klawisze_dzialan[0]) { wybrana_operacja = TypDzialania.Dodawanie }
            if(wyd.getSource() == klawisze_dzialan[1]) { wybrana_operacja = TypDzialania.Odejmowanie }
            if(wyd.getSource() == klawisze_dzialan[2]) { wybrana_operacja = TypDzialania.Mnozenie }
            if(wyd.getSource() == klawisze_dzialan[3]) { wybrana_operacja = TypDzialania.Dzielenie }
            //operacje na bool'ach
            wpisano_kropke = false
            if(wpisano_pierwsza_liczbe && !wpisano_druga_liczbe)
            {
                // ??
                B = wyswietlacz.getText().toDouble()
            }
            if(!wpisano_pierwsza_liczbe)
            {
                A = wyswietlacz.getText().toDouble()
                wpisano_pierwsza_liczbe = true
            }
        }
        if(wyd.getSource() == klawisz_rowna_sie)
        {
            B = wyswietlacz.getText().toDouble()
            if(wybrana_operacja != TypDzialania.Nic)
            {
                val wynik : Double = policz()
                if(dzielenie_przez_0)
                {
                    napisz_komunikat("dzielenie przez zero!")
                }
                else
                {
                    B = wynik
                    wyswietlacz.text = wynik.toString()
                }
            }
        }
    }
}