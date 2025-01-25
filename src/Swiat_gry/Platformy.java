package Swiat_gry;

/**
 * Klasa Platformy "przedluza" klase GraObjekt i na dodatek
 * pozwalajaca na zaladowanie i pozycjonowanie platform na ekranie
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class Platformy extends GraObjekt {

    /** zmienna pozycjunajaca platformy i zmienajaca ich szerokosc i wysokosc */
    public static int[][] platPoz = {
            {0,500,640,160},{250,440,100,20},{50,400,100,20},{175,325,120,20},{350,275,140,20},
            {225,200,130,20},{400,150,125,20},{50,130,150,20},{250,75,110,20}
    };

    /**
     * Konstruktor sluzacy do dodania platform i zaladowania zdjecia
     *
     * @param x - pozycja platform w poziomej osi
     * @param y - pozycja platform w pionowej osi
     * @param szer - szerokosc platform
     * @param wys - wysokosc platform
     */
    public Platformy(int x,int y,int szer,int wys){
        super(x,y,szer,wys);
        zaladujObraz("/trawa.png");
    }
}
