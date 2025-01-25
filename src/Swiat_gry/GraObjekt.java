package Swiat_gry;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


/**
 * Klasa GraObjekt czyli klasa zbierajaca te same aspekty wielu klas.
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class GraObjekt {
    /** Zmienna ustawiajaca wsporzedna x w grze */
    public int x;
    /** Zmienna ustawiajaca wsporzedna x w grze */
    public int y;
    /** Zmienna ustawiajaca szeroksc obiektu w grze */
    public int szer;
    /** Zmienna ustawiajaca wysokosc obiektu w grze */
    public int wys;
    /** Zmienna odpowiadajaca za wczytywanie zdjec w grze */
    public BufferedImage zdjecie;
    /** Sprawdza widocznosc obiektow */
    public boolean widoczne;

    /**
     * Konstruktor GraObjekt dwuparametrowy sluzocy do
     * rysowania monet,przeszkod i zycia na ekrania
     *
     * @param x - wsporzedna pozioma x w grze
     * @param y - wsporzedna pionowa y w grze
     */
    public GraObjekt(int x,int y){
        this.x = x;
        this.y = y;
        this.widoczne = true;
    }

    /**
     * Konstruktor GraObjekt czteroparametrowy sluzocy do
     * rysowania monet,przeszkod i zycia na ekrania
     *
     * @param x - wsporzedna pozioma x w grze
     * @param y - wsporzedna pionowa y w grze
     * @param szer - Szerokosc obiektu
     * @param wys - Wysokosc obiektu
     */
    public GraObjekt(int x,int y,int szer,int wys){
        this.x = x;
        this.y = y;
        this.szer = szer;
        this.wys = wys;
        this.widoczne = true;
    }

    /**
     * Chroniona metoda zaladujObraz sluzaca do ladowania obrazu obiektu do gry
     *
     * @param imageName - Link do obrazu z folderu res
     */
    protected void zaladujObraz(String imageName) {
        try {
            zdjecie = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imageName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Publiczna metoda jestWidoczne sprawdzajaca widocznosc obiektow
     *
     * @return zmienna okreslajaca widocznosc typu prawda/falsz
     */
    public boolean jestWidoczne() {
        return widoczne;
    }

    /**
     * Publiczna metoda ustawWidoczne ustawiajaca widocznosc obiektow
     *
     * @param widoczne - pobiera widocznosc obiektu
     */
    public void ustawWidoczne(Boolean widoczne) {
        this.widoczne = widoczne;
    }

    /**
     * Publiczna metoda pobierzGranice tworzace granice dla obiektow
     *
     * @return Granica obieku zlozona z x,y szerokosci i wysokosci zdjecia
     */
    public Rectangle pobierzGranice(){
        return new Rectangle(x,y,zdjecie.getWidth(null),zdjecie.getHeight(null));
    }
}
