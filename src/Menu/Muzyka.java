package Menu;

import Panele.OpcjePanel;
import Panele.PanelGry;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * Klasa Muzyka czyli klasa odpowiadajaca za muzyke i dzwiek w grze
 *
 * @author Gb125Dev
 * @version 1.1
 */
public class Muzyka {
    /** Zmienna klip sluzy do pobrania klipu dzwiekowego z muzyki*/
    private Clip klip;
    /** Tablica zmiennych typu URL(czyli linków) */
    private final URL[] dzwiekLink = new URL[6];
    /** Zmienna sprawdzajaca czy muzyka jest wlaczona w opcjach*/
    public boolean jestAktywna = true;

    /**
     * Ten konstruktor sluzy do dodania sciezek muzycznych do zmiennej dzwiekLink.
     */
    public Muzyka(){
        dzwiekLink[0] = getClass().getResource("/muzyka/moneta.wav");
        dzwiekLink[1] = getClass().getResource("/muzyka/skrzywdzenie.wav");
        dzwiekLink[2] = getClass().getResource("/muzyka/jump_to_action.wav");
        dzwiekLink[3] = getClass().getResource("/muzyka/jumping_ahead.wav");
        dzwiekLink[4] = getClass().getResource("/muzyka/zwyciestwo.wav");
        dzwiekLink[5] = getClass().getResource("/muzyka/koniec_gry.wav");
    }

    /**
     * Prywatna metoda ustawPlik ktora sluzy do ustawienia granej muzyki
     *
     * @param i - zmienna dzwieku z tablicy dzwiekLink
     */
    //change to private
    private void ustawPlik(int i){
        try {
            klip = AudioSystem.getClip();
            klip.open(AudioSystem.getAudioInputStream(dzwiekLink[i]));
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prywatna metoda graj sluzoca do zagrania muzyki
     */
    //change to private
    protected void graj(){
        klip.start();
    }

    /**
     * Prywatna metoda petla sluzoca do zapetlania muzyki
     */
    //change to private
    private void petla(){
        klip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Chroniona metoda stop sluzaca do zatrzymywania muzyki
     */
    //change to protected
    protected void stop(){
        klip.stop();
    }

    /**
     * Chroniona metoda grajMuzyke sluzaca do grania muzyki w tle
     * w zaleznosci od tego czy jest wlaczona
     *
     * @param i - zmienna dzwieku z tablicy dzwiekLink
     */
    //change to protected
    protected void grajMuzyke(int i){
        if (jestAktywna) {
            ustawPlik(i);
            graj();
            petla();
        }else{
            stop();
        }
    }

    /**
     * Prywatna metoda grajED ktora gra muzyke tylko raz
     * bo jest to efekt dzwiekowy grajacy raz w specyficym warunku
     *
     * @param i - zmienna dzwieku z tablicy dzwiekLink
     */
    //change to private
    private void grajED(int i){
        if (jestAktywna) {
            ustawPlik(i);
            graj();
        }else{
            stop();
        }
    }

    /**
     * Publiczna metoda efektyMuz ktora gra muzyke tylko raz
     * bo jest to efekt dzwiekowy grajacy raz w specyficym warunku
     * pod warunkiem ze muzyka w opcjach jest wlaczona
     *
     * @param num - zmienna dzwieku z tablicy dzwiekLink
     */
    public void efektyMuz(int num){
        if (OpcjePanel.czyWlaczona) {
            grajED(num);
        }
    }

    /**
     * Publiczna metoda grajRaz sluzaca do zagrania muzyki jeden raz
     *
     * @param num - zmienna dzwieku z tablicy dzwiekLink
     */
    public void grajRaz(int num){
        if (!PanelGry.zagrane) {
            efektyMuz(num);
            PanelGry.zagrane = true;
        }
    }

    /**
     * Chroniona metoda muzykaGraj sluzaca do zagrania muzyki w wypadku wlaczenia muzyki
     */
    protected void muzykaGraj(){
        if (!KluczePoruszanie.klikniete) {
            grajMuzyke(2);
            KluczePoruszanie.klikniete = true;
        }
    }
}
