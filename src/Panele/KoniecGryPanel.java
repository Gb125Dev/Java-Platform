package Panele;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Klasa KoniecGryPanel czyli klasa narysowanie panelu
 * konczacego gre w zaleznosci od zwyciestwa lub porazki
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class KoniecGryPanel {
    /** Tekst menu prowadzacy do menu gry */
    private String doMenuText = ">Menu";
    /** Tekst prowadzacy do wyjscia gry */
    private String wyjscieText = " Wyjście";
    /** zmienna odpowiadajaca za wybor danej czynnosci */
    public static int koniecStan = 1;

    /**
     * Chroniona metoda rysujKoniec rysuje panel w zaleznosci od tego czy
     * gracz wygral lub przegal
     *
     * @param g - Instancja klasy Graphics(grafika) sluzacza do rysowania obiektow
     * @param czyZwyciezone - zmienna typu prawda/falsz ktora rysuje
     *                      dany tekst w zaleznosci od tej zmiennej
     */
    //zmienic na protected
    protected void rysujKoniec(Graphics g,boolean czyZwyciezone){
        g.setFont(new Font("Helvetica", Font.BOLD, 56));
        g.setColor(new Color(23,124,24));

        if (czyZwyciezone) {
            g.drawString("Zwycięstwo",150,75);
            g.drawString("Porada: Spróbuj na",20,200);
            g.drawString("trudniejszych trybach",20,275);
        }else{
            g.drawString("Porażka", 150, 75);
            g.drawString("Porada: skup się na", 20, 200);
            g.drawString("każym skoku", 20, 275);
        }
        g.drawString(doMenuText,70,550);
        g.drawString(wyjscieText,300,550);

    }

    /**
     * Chroniona metoda zmienTekst sluzaca do zmiany tekstu w zaleznosci od zmiennej
     * liczbowej koniecStan
     */
    //zmienić na protected
    protected void zmienTekst(){
        switch (koniecStan) {
            case 1 -> {
                doMenuText = ">Menu";
                wyjscieText = " Wyjście";
            }
            case 2 -> {
                doMenuText = " Menu";
                wyjscieText = ">Wyjście";
            }
        }
    }
}
