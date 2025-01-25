package Panele;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Klasa OpcjePanel czyli klasa pozwalajaca na
 * ustawianie muzyki i poziomu trudnosci
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class OpcjePanel {
    /** Tekst muzyka */
    private String muzykaText = ">Muzyka";
    /** Tekst dzialajacy jak wlacznik, sluzacy do wlaczania */
    private String wlaczona = "< Wł >";
    /** Tekst przedstawiajacy poziom trudnosci */
    private String trudnoscText = " Poziom trudnosci:";
    /** Tekst poziomu trudnosci */
    private String trudnosc = "Łatwy";
    /** Tekst wyjscia z opcji */
    private String wyjscieText = " Wyjście";
    /** Zmienna wybierjaca opcje do zmodyfikowania */
    public static int opcjeStan = 1;
    /** Zmienna sprawdzjaca czy muzyka jest wlaczona */
    public static boolean czyWlaczona = true;
    /** Zmienna ustawiajaca poziom trudnosci w grze */
    public static int pozTrudnosc = 0;

    /**
     * Publiczna metoda rysujOpcje rysuje panel opcji
     * w ktory gracz modyfikuje opcje gry
     *
     * @param g - Instancja klasy Graphics(grafika) sluzacza do rysowania obiektow
     */
    public void rysujOpcje(Graphics g){
        g.setColor(new Color(23,124,24));
        g.setFont(new Font("Helvetica", Font.BOLD, 56));
        g.drawString("Opcje",200,75);
        g.drawString(muzykaText,10,250);
        g.drawString(wlaczona,250,250);
        g.drawString(trudnoscText,10,400);
        g.drawString(trudnosc,300,475);
        g.drawString(wyjscieText,10,550);
    }

    /**
     * Publiczna metoda sluzoca do zmiany wygladou opcji w
     * zaleznosci od klaisza i zmiennej opcjeStan i ustawiajaca
     * takze metode w zaleznosci od opcji
     */
    public void zmienTekst(){
        switch (opcjeStan) {
            case 1 -> {
                muzykaText = ">Muzyka";
                trudnoscText = " Poziom trudnosci:";
                zmienMuzyke();
            }
            case 2 -> {
                muzykaText = " Muzyka";
                trudnoscText = ">Poziom trudnosci:";
                wyjscieText = " Wyjście";
                zmienTrudnosc();
            }
            case 3 -> {
                muzykaText = " Muzyka";
                trudnoscText = " Poziom trudnosci:";
                wyjscieText = ">Wyjście";
            }
        }
    }

    /**
     * Prywatna metoda zmienMuzyke ktora wlacza i wylacza muzyke
     * w zaleznoci od tego czy jest wlaczona
     *
     */
    //change to private
    private void zmienMuzyke(){
        if (czyWlaczona) {
            wlaczona = "< Wł >";
        } else {
            wlaczona = "< Wył >";
        }
    }

    /**
     * Prywatna metoda zmienTrudnosc zmienia trudnosc gry
     * w zaleznosci od zmiennej pozTrudnosc
     */
    //change to private
    private void zmienTrudnosc(){
        switch (pozTrudnosc){
            case 0 -> trudnosc = "Łatwy";
            case 1 -> trudnosc = "Normalny";
            case 2 -> trudnosc = "Jedno życie";
        }
    }
}
