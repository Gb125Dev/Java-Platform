package Panele;

import Menu.Muzyka;
import Swiat_gry.Gracz;
import Swiat_gry.Monety;
import Swiat_gry.Platformy;
import Swiat_gry.Przeszkody;
import Swiat_gry.Zycia;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


/**
 * Klasa PanelGry czyli klasa pozwalajaca na
 * uruchomienie i granie w gre.
 *
 * @author Gb125Dev
 * @version 1.1
 */
//Modyfy lines like there
public class PanelGry extends JPanel {

    /** Instancja wczytajaca platfromy uzywane w grze */
    private final List<Platformy> platformy = new ArrayList<>();
    /** Instancja wczytajaca monety zbierane w grze */
    private List<Monety> monety;
    /** Instancja wczytajaca przeszkody napotykane w grze */
    private List<Przeszkody> przeszkody;
    /** Instancja wczytajaca zycia liczone w grze */
    private List<Zycia> zycia;
    /** Instancja wczytajaca gracza */
    public Gracz gracz;
    /** Instancja wczytajaca muzyke(zarowno muzyke w tle jak i efekty dzwiekowe) w grze */
    private final Muzyka mZ = new Muzyka();
    /** Instancja wczytajaca pnael pokazywnay gdy gra bedzie skonczona */
    private final KoniecGryPanel kgp = new KoniecGryPanel();
    /** zmienna liczbowa wczytujaca panele w zaleznosci od tej zminnej */
    public int graczStan = 1;
    /** sprawdza czy muzyka jest wlaczona */
    public static boolean zagrane;

    /**
     * Publiczny konstruktor kreujacy nowa instacje gracza do zmiennej gracz,
     * ustawiajacy focus(skupienie) na prawdziwe i urachamia komponenty poprzez
     * metode uruchomkomponenty.
     */
    public PanelGry(){
        setFocusable(true);
        gracz = new Gracz(20,20,32,40,platformy);
        uruchomKomponenty();
    }

    /**
     * Publiczna metoda uruchomKomponenty sluzaca do pozycji gracza,
     * ustawienia trudnoscia,platform,monet,przeszkod i zyc w zaleznosci od trudnosci
     */
    public void uruchomKomponenty() {
        gracz.x = 20;
        gracz.y = 20;
        int trudnosc;
        for (trudnosc = 0; trudnosc < gracz.zycia;) {
            trudnosc++;
        }
        for (int[] p : Platformy.platPoz) {
            platformy.add(new Platformy(p[0], p[1], p[2], p[3]));
        }
        monety = new ArrayList<>();

        for (int[] p : Monety.monPoz) {
            monety.add(new Monety(p[0], p[1]));
        }
        przeszkody = new ArrayList<>();

        for (int[] p : Przeszkody.przeszPoz) {
            przeszkody.add(new Przeszkody(p[0], p[1]));
        }


        zycia = new ArrayList<>();
        switch (trudnosc){
            case 1 -> zycia.add(new Zycia(10,5));
            case 3 -> {
                for (int[] p : Zycia.zycia3Poz) {
                    zycia.add(new Zycia(p[0],p[1]));
                }
            }
            case 5 -> {
                for (int[] p : Zycia.zycia5Poz) {
                    zycia.add(new Zycia(p[0],p[1]));
                }
            }
        }
    }

    /**
     * Publiczna metoda zrobRysowanie sluzaca do wczytania w zaleznosci
     * od zmiennej graczStan i od tego czy gracz spelnil konkretne warunki
     * czyli zebranie wszystkich monet lub utrata wszystkich zyc
     *
     * @param g - Instancja klasy Graphics(grafika) sluzacza do rysowania obiektow
     */
    public void zrobRysowanie(Graphics g) {
        //delete first line and move [(Graphics2D) g] to wczytanie method
        if (gracz.monety == 5) {
            graczStan = 2;
        }else if (gracz.zycia == 0) {
            graczStan = 0;
        }
        switch (graczStan){
            case 0 -> {
                setBackground(Color.CYAN);
                kgp.rysujKoniec(g,false);
            }
            case 1 -> wczytanie((Graphics2D) g);
            case 2 -> {
                setBackground(Color.CYAN);
                kgp.rysujKoniec(g,true);
            }
        }
    }

    /**
     * Prywatna metoda wczytanie sluzy do wczytania platform, pozostalych monet
     * na planszy, pozostalych przeszkod, pozostalych zyc i ruchu gracza
     *
     * @param g2d - Instancja klasy Graphics2D(grafika dwuwymiarowa)
     *            sluzacza do rysowania obiektow dwuwymiarowych
     */
    private void wczytanie(Graphics2D g2d){
        for (Platformy platfoma : platformy) {
            g2d.drawImage(platfoma.zdjecie, platfoma.x, platfoma.y,platfoma.szer,platfoma.wys, this);
        }
        for (Monety moneta : monety) {
            if (moneta.jestWidoczne()) {
                g2d.drawImage(moneta.zdjecie, moneta.x, moneta.y, this);
            }
        }
        for (Przeszkody przeszkoda : przeszkody) {
            if (przeszkoda.jestWidoczne()) {
                g2d.drawImage(przeszkoda.zdjecie, przeszkoda.x, przeszkoda.y, this);
            }
        }
        for (Zycia zycie : zycia) {
            g2d.drawImage(zycie.zdjecie, zycie.x, zycie.y, this);
        }

        if (gracz.widoczne) {
            g2d.drawImage(gracz.zdjecie, gracz.x, gracz.y,gracz.szer,gracz.wys, this);
        }
    }

    /**
     * Publiczna metoda aktualizajaca wizualna czesc panelu
     * w zaleznosci od zmiennej graczStan
     */
    public void aktualizuj(){
        switch (graczStan) {
            case 0 -> {
                mZ.grajRaz(5);
                kgp.zmienTekst();
            }
            case 1 -> {
                ruchGracza();
                zaktualizujObiekty();
                sprawdzKolizje();
            }
            case 2 -> {
                mZ.grajRaz(4);
                kgp.zmienTekst();
            }
        }
    }

    /**
     * Prywatna metoda ruchGracza sluzaca do zmieniania
     * pozycji gracza i uruchamiania grawitacji
     */
    private void ruchGracza(){
        gracz.ruch();
        gracz.grawtacja();
    }

    /**
     * Prywatna metoda zaktualizujObiekty sluzaca do
     * aktualizacji liczby monet i przeszkod w grze
     */
    private void zaktualizujObiekty(){
        for (int i = 0; i < monety.size(); i++) {

            Monety m = monety.get(i);

            if (!m.jestWidoczne()) {
                monety.remove(i);
            }
        }
        for (int i = 0; i < przeszkody.size(); i++) {

            Przeszkody p = przeszkody.get(i);

            if (!p.jestWidoczne()) {
                przeszkody.remove(i);
            }
        }
    }

    /**
     * Prywatna metoda sprawdzajaca kolizje miedzy obiektami
     * a graczem i odpowiednio reagujacy na nie. Kolizje
     * odbywaja sie miedzy graczem a przeszkodo lub
     * gracze a monetom
     */
    private void sprawdzKolizje(){

        Rectangle rGracz = gracz.pobierzGranice();
        for (Monety moneta : monety) {

            Rectangle rMon = moneta.pobierzGranice();

            if (rGracz.intersects(rMon)) {
                moneta.ustawWidoczne(false);
                mZ.efektyMuz(0);
                gracz.monety++;
            }
        }
        for (Przeszkody przeszkoda : przeszkody) {

            Rectangle rPrzesz = przeszkoda.pobierzGranice();

            if (rGracz.intersects(rPrzesz)) {
                przeszkoda.ustawWidoczne(false);
                mZ.efektyMuz(1);
                gracz.zycia--;
                zycia.remove(zycia.size() - 1);
            }
        }
    }
}