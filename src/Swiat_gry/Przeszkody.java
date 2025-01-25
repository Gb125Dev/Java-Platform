package Swiat_gry;

/**
 * Klasa Przeszkody "przedluza" klase GraObjekt i na dodatek
 * pozwalajaca na zaladowanie i pozycjonowanie przeszkod na ekranie
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class Przeszkody extends GraObjekt {

    /** zmienna pozycjunajaca przeszkody */
    public static int[][] przeszPoz = {
            {195,306},{245,306},{390,256},{430,131},{90,111},{265,56},{315,56}
    };

    /**
     * Konstruktor sluzacy do dodania prrzeszkod i zaladowania zdjecia
     *
     * @param x - pozycja przeszkody w poziomej osi
     * @param y - pozycja przeszkody w pionowej osi
     */
    public Przeszkody(int x,int y){
        super(x,y);
        zaladujObraz("/latajaca_pulapka.png");
    }
}
