package Swiat_gry;

/**
 * Klasa Zycia "przedluza" klase GraObjekt i na dodatek
 * pozwalajaca na zaladowanie i pozycjonowanie zyc na ekranie
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class Zycia extends GraObjekt{

    /** zmienna pozycjunajaca zycia gdy gracz ma 5 zyc */
    public static int[][] zycia5Poz = {{10,5},{40,5},{70,5},{100,5},{130,5}};
    /** zmienna pozycjunajaca zycia gdy gracz ma 3 zycia */
    public static int[][] zycia3Poz = {{10,5},{40,5},{70,5}};

    /**
     * Konstruktor sluzacy do dodania zyc i zaladowania zdjecia
     *
     * @param x - pozycja zyc w poziomej osi
     * @param y - pozycja zyc w pionowej osi
     */
    public Zycia(int x, int y) {
        super(x, y);
        zaladujObraz("/gracz_zycie.png");
    }
}