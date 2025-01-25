package Panele;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Klasa MenuPanel czyli klasa pozwalajaca na
 * narysowanie panelu ktory jest menu gry
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class MenuPanel {
    /** Zmienna pokazjaca tekst Graj */
    private String grajText = ">Graj";
    /** Zmienna pokazjaca tekst Opcje */
    private String opcjaText = " Opcje";
    /** Zmienna sluzaca do wychodzenia z gry */
    private String wyjscieText = " Wyjście";
    /** Sluzy do zzmienienia wylgladu tekstu i funkcjonlanosci */
    public static int menuStan = 1;

    /**
     * Publiczna metoda rysujMenu rysuje panel menu
     * ktory pokazuje sie graczowi na poczatek gry
     *
     * @param g - Instancja klasy Graphics(grafika) sluzacza do rysowania obiektow
     */
    public void rysujMenu(Graphics g){
        g.setColor(new Color(23,124,24));
        g.setFont(new Font("Helvetica", Font.BOLD, 56));
        g.drawString("Platformówka w Javie",20,75);
        g.drawString(grajText,200,250);
        g.drawString(opcjaText,200,400);
        g.drawString(wyjscieText,200,550);
        g.setFont(new Font("Helvetica", Font.BOLD, 14));
        g.drawString("Zrobione przez: Gb125Dev | Ver: 1.1",10,590);
    }

    /**
     * Publiczna metoda zmienTekst sluzaca do zmiany tekstu w zaleznosci od zmiennej
     * liczbowej menuStan
     */
    public void zmienTekst(){
        switch (menuStan) {
            case 1 -> {
                grajText = ">Graj";
                opcjaText = " Opcje";
            }
            case 2 -> {
                grajText = " Graj";
                opcjaText = ">Opcje";
                wyjscieText = " Wyjście";
            }
            case 3 -> {
                grajText = " Graj";
                opcjaText = " Opcje";
                wyjscieText = ">Wyjście";
            }
        }
    }
}
