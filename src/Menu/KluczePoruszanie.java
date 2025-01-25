package Menu;

import Panele.MenuPanel;
import Panele.KoniecGryPanel;
import Panele.OpcjePanel;
import Swiat_gry.Gracz;
import Panele.PanelGry;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Klasa KluczePoruszanie czyli glowna klasa odpowiadajaca za klawisze
 * i ich funkcje a zeby dzialac to "przedluza" klase KeyAdpater czyli adapter klucza
 *
 * @author Gb125Dev
 * @version 1.1
 */
public class KluczePoruszanie extends KeyAdapter {
    /** Inicjalizacja klasy GlowneMenuPanel*/
    private final GlowneMenuPanel gmp;
    /** Inicjalizacja klasy PanelGry*/
    private final PanelGry pG;
    /** Inicjalizacja klasy Gracz*/
    private final Gracz gracz;
    /** Inicjalizacja klasy Muzyka*/
    private final Muzyka mZ;
    /** Zmienna typu prawda/falsz(boolean) odpowiadajacz za sprawdzienie klikniecia*/
    public static boolean klikniete = true;

    /**
     * Ten konstruktor sluzy do wczytania odpowiednich klas.
     *
     * @param gmp - sluzy do zrobienia i wczytania instancji klasy GlowneMenuPanel
     * @param mZ - sluzy do zrobienia i wczytania instancji klasy Muzyka
     * @param pG - sluzy do zrobienia i wczytania instancji klasy PanelGry
     */
    public KluczePoruszanie(GlowneMenuPanel gmp,PanelGry pG,Muzyka mZ){
        this.gmp = gmp;
        this.pG = pG;
        this.mZ = mZ;
        this.gracz = pG.gracz;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        zmianaPozycji(e.getKeyCode());
        if (gmp.graStan == 3 && (pG.graczStan == 0 || pG.graczStan == 2)) {
            mZ.stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gmp.graStan == 3) {
            gracz.graczStop(e.getKeyCode());
        }
        if (gmp.graStan == 3 && (pG.graczStan == 0 || pG.graczStan == 2)) {
            mZ.stop();
        }
    }

    /**
     * Prywatna metoda zmianaPozycji ktora jak nazwa wskazuje, zmienia pozycje danego
     * panelu w zaleznosci od stanu gry
     *
     * @param klucz - jest to liczbowa wersja wcisnietego klawisza
     */
    private void zmianaPozycji(int klucz){
        switch (gmp.graStan){
            case 1 -> pozycjaMenu(klucz);
            case 2 -> pozycjaOpcje(klucz);
            case 3 -> stanGry(klucz);
        }
    }
    /**
     * Prywatna metoda pozycjaMenu ktora jak nazwa wskazuje, zmienia pozycje w zaleznosci
     * od wcisznietego klawisza i po wcisnieciu klawisza ENTER, robi funkcje w zaleznosci
     * od numeru funkcji
     *
     * @param klucz - jest to liczbowa wersja wcisnietego klawisza
     */
    private void pozycjaMenu(int klucz){
        if (klucz == KeyEvent.VK_UP && MenuPanel.menuStan > 1) {
            MenuPanel.menuStan--;
        }
        if (klucz == KeyEvent.VK_DOWN && MenuPanel.menuStan < 3) {
            MenuPanel.menuStan++;
        }
        if (klucz == KeyEvent.VK_ENTER) {
            switch (MenuPanel.menuStan) {
                case 1 -> {
                    gmp.graStan = 3;
                    mZ.stop();
                    mZ.grajMuzyke(3);
                }

                case 2 -> gmp.graStan = 2;
                case 3 -> gmp.graStan = 0;
            }
        }
    }

    /**
     * Prywatna metoda pozycjaMenu ktora jak nazwa wskazuje, zmienia pozycje w zaleznosci
     * od wcisznietego klawisza gorny lub dolny
     *
     * @param klucz - jest to liczbowa wersja wcisnietego klawisza
     */
    private void pozycjaOpcje(int klucz) {
        if (klucz == KeyEvent.VK_UP && OpcjePanel.opcjeStan > 1) {
            OpcjePanel.opcjeStan--;
        }
        if (klucz == KeyEvent.VK_DOWN && OpcjePanel.opcjeStan < 3) {
            OpcjePanel.opcjeStan++;
        }
        funkcjeOpcje(OpcjePanel.opcjeStan,klucz);
    }

    /**
     * Prywatna metoda funkcjeOpcje ktora sluzy do ustawiania opcji w panelu opcje
     *
     * @param wartosc - jest to liczbowa wersja zmiennej odpowiedzialnej za stan opcji
     * @param klucz - jest to liczbowa wersja wcisnietego klawisza
     */
    private void funkcjeOpcje(int wartosc,int klucz){
        if (wartosc == 1) {
            if (klucz == KeyEvent.VK_LEFT) {
                OpcjePanel.czyWlaczona = true;
                mZ.jestAktywna = true;
                mZ.muzykaGraj();
            }
            if (klucz == KeyEvent.VK_RIGHT) {
                OpcjePanel.czyWlaczona = false;
                mZ.jestAktywna = false;
                klikniete = false;
                mZ.stop();
            }
        } else if (wartosc == 2) {
            if (klucz == KeyEvent.VK_LEFT && OpcjePanel.pozTrudnosc > 0) {
                OpcjePanel.pozTrudnosc--;
            }
            if (klucz == KeyEvent.VK_RIGHT && OpcjePanel.pozTrudnosc < 2) {
                OpcjePanel.pozTrudnosc++;
            }
            trudnoscUstaw(OpcjePanel.pozTrudnosc);
        } else {
            if (klucz == KeyEvent.VK_ENTER) {
                gmp.graStan = 1;
            }
        }
    }

    /**
     * Prywatna metoda trudnoscUstaw ktora sluzy do ustawiania trudnosci gry i aktualizacji
     * poziomu trudnosci
     *
     * @param trudnosc - sluzy do pobrania i ustawienie poziomu trudnosci w zaleznosci od liczby
     */
    private void trudnoscUstaw(int trudnosc){
        switch (trudnosc){
            case 0 -> gracz.maxZycia = 5;
            case 1 -> gracz.maxZycia = 3;
            case 2 -> gracz.maxZycia = 1;
        }
        pG.gracz.zycia = pG.gracz.maxZycia;
        pG.uruchomKomponenty();
    }

    /**
     * Prywatna metoda stanGry ktora sluzy do ustawiania stanu gry
     * w zaleznosci od tego czy gracz skonczyl gre
     *
     * @param klucz - jest to liczbowa wersja wcisnietego klawisza
     */
    //change to private
    private void stanGry(int klucz){
        switch (pG.graczStan) {
            case 0, 2 -> koniecRuch(klucz);
            case 1 -> gracz.graczRuch(klucz);
        }
    }

    /**
     * Prywatna metoda stanGry ktora sluzy do ustawiania tego co
     * sie dzieje po koncu gry i tego czy gracz chce wrocic do menu
     * czy chce wyjsc
     *
     * @param klucz - jest to liczbowa wersja wcisnietego klawisza
     */
    //change to private
    private void koniecRuch(int klucz){
        if (klucz == KeyEvent.VK_LEFT && KoniecGryPanel.koniecStan == 2) {
            KoniecGryPanel.koniecStan--;
        }
        if (klucz == KeyEvent.VK_RIGHT && KoniecGryPanel.koniecStan == 1) {
            KoniecGryPanel.koniecStan++;
        }
        if (klucz == KeyEvent.VK_ENTER && KoniecGryPanel.koniecStan == 1) {
            pG.graczStan = 1;
            gmp.graStan = 1;
            pG.gracz.monety = 0;
            pG.gracz.zycia = pG.gracz.maxZycia;
            pG.uruchomKomponenty();
            PanelGry.zagrane = false;
            mZ.stop();
            mZ.grajMuzyke(2);
        }
        if (klucz == KeyEvent.VK_ENTER && KoniecGryPanel.koniecStan == 2) {
            gmp.graStan = 0;
        }
    }
}