package Swiat_gry;

/**
 * Klasa Monety "przedluza" klase GraObjekt i na dodatek
 * pozwalajaca na zaladowanie i pozycjonowanie monet na ekranie
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class Monety extends GraObjekt {

    /** zmienna pozycjunajaca monety */
    public static int[][] monPoz = {
            {225,305},{450,255},{490,130},{60,110},{295,55}
    };

    /**
     * Konstruktor sluzacy do dodania monet i zaladowania zdjecia
     *
     * @param x - pozycja monety w poziomej osi
     * @param y - pozycja monety w pionowej osi
     */
    public Monety(int x,int y){
        super(x,y);
        zaladujObraz("/moneta.png");
    }
}
