package Swiat_gry;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Klasa Gracz czyli klasa pozwalajaca na
 * uruchomienie i granie w gre.
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class Gracz extends GraObjekt{
    /** Zmienna stanowiaca o predkosci gracza w poziomie */
    private int dx;
    /** Zmienna stanowiaca o predkosci gracza w pionie */
    private int dy;
    /** Liczba zebranych monet */
    public int monety = 0;
    /** Makymalna liczba zyc w grze */
    public int maxZycia = 5;
    /** Liczba zyc w grze */
    public int zycia = maxZycia;
    /** platformy  na ktorych gracz skacze */
    private final List<Platformy> plat;
    /** Czasomierz mierzacy czas animacji */
    private Timer animCzas;
    /** Ta zmienna kontroluje dlugosc skoku w grze */
    private int licznik = 0;
    /** Ta zmienna kontroluje wyswietlane klatki gracza */
    private int klatka = 1;
    /** Ta zmienna odpowiada za kierunek gracza */
    private String kierunek;
    /** Ta zmienna odpowiada za czas wykonania animacja(w milisekundach) */
    private int czasWyk = 200;

    /**
     * Kostruktor sluzacy do wczytania gracza, obrazu,
     * platformy, animacji i ustalenia kierunku gracza
     *
     * @param x - pozycja gracza w poziomie
     * @param y - pozycja gracza w pionie
     * @param szer - szerokosc zdjecia gracza
     * @param wys - wysokosc zdjecia gracza
     * @param plat - lista platform wczytancyh przez gracza
     */
    public Gracz(int x, int y, int szer, int wys, List<Platformy> plat) {
        super(x,y,szer,wys);
        this.plat = plat;
        zaladujObraz("/gracz_obrazki/gracz_bezczynny_prawo_0.png");
        kierunek = "pasywny_prawy";
        animacjaCzas(czasWyk);
    }

    /**
     * Publiczna metoda ruch sluzaca do poruszania postaci gracza
     * i blokowania gry przekroczy pewne koordynaty
     */
    public void ruch() {
        x += dx;
        y += dy;
        if (x > 600) {
            x = 600;
        }
        if (x < 1) {
            x = 1;
        }
        if (y < 1) {
            y = 1;
        }
    }

    /**
     * Publiczna metoda sluzaca do spadania postaci i
     * odpowiedniego skoky
     */
    public void grawtacja(){
        if (dy >= 0) {
            dy = 3;
            for (Platformy platformy : plat) {
                if (y + wys > platformy.y && y + 0.8 * wys < platformy.y
                        && x + szer / 2 > platformy.x && x + szer / 2 < platformy.x + platformy.szer) {
                    dy = 0;
                    break;
                }
            }
        }else{
            int wysSkok = 60;
            licznik = licznik + 3;
            if (licznik >= wysSkok) {
                dy = 0;
                licznik = 0;
            }
        }
        y += dy;
    }

    /**
     * Prywatna metoda animacjaCzas sluzy do uruchomienia i
     * zmiany czasu animacji
     *
     * @param nowyCzas - bierze nowy czas(w milisekundach) do zmiany animacji
     */
    private void animacjaCzas(int nowyCzas){
        animCzas = new Timer();
        animCzas.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                przejscie();
            }
        }, 0,nowyCzas);
    }

    /**
     * Prywatna metoda animacjaWarunki  któr w zaleznosci od animacji
     * i jej czasu, wybiera odpowiednie klatkowanie(dla bezczynnej animacji:
     * 2 klatki i dla animacji ruchu: 4 klatki) i laduje pasywne animacje
     */
    private void animacjaWarunki(){
        if (Objects.equals(kierunek, "lewy") || Objects.equals(kierunek, "prawy") && czasWyk == 200){
            if (klatka == 1 || klatka == 2 || klatka == 3) {
                klatka++;
            }else{
                klatka = 1;
            }
        }else{
            czasWyk = 400;
            if (klatka == 1) {
                klatka++;
            }else{
                klatka--;
            }
        }

        if(Objects.equals(kierunek, "pasywny_lewy")) {
            if (klatka == 1) {
                zaladujObraz("/gracz_obrazki/gracz_bezczynny_lewo_0.png");
            }
            if (klatka == 2) {
                zaladujObraz("/gracz_obrazki/gracz_bezczynny_lewo_1.png");
            }
        }
        if (Objects.equals(kierunek, "pasywny_prawy")) {
            if (klatka == 1) {
                zaladujObraz("/gracz_obrazki/gracz_bezczynny_prawo_0.png");
            }
            if (klatka == 2) {
                zaladujObraz("/gracz_obrazki/gracz_bezczynny_prawo_1.png");
            }
        }
    }

    /**
     * Prywatna metoda przejscie polegajaca na wyboru animacji i
     * zaimplemenyowaniu jej a takze na uruchomieniu jej
     */
    private void przejscie(){
        if ((kierunek.equals("pasywny_lewy") && czasWyk == 200) || (kierunek.equals("pasywny_prawy") && czasWyk == 200)) {
            czasWyk = 400;
            animCzas.cancel();
            animacjaCzas(czasWyk);
        }else if((kierunek.equals("lewy") && czasWyk == 400) || (kierunek.equals("prawy") && czasWyk == 400)
                || (kierunek.equals("skok") && czasWyk == 400)){
            czasWyk = 200;
            animCzas.cancel();
            animacjaCzas(czasWyk);
        }else {
            animacjaWarunki();
        }
    }

    /**
     * Publiczna metoda sluzaca do zarowno poruszania graczem
     * jak i uruchomienia animacji
     * @param klucz - liczbowa zmienna nacisnietego klawisza
     */
    public void graczRuch(int klucz){
        if (klucz == KeyEvent.VK_LEFT || klucz == KeyEvent.VK_A) {
            dx = -2;
            kierunek = "lewy";
            switch (klatka){
                case 1 -> zaladujObraz("/gracz_obrazki/gracz_biegnacy_lewo_1.png");
                case 2, 4 -> zaladujObraz("/gracz_obrazki/gracz_bezczynny_lewo_0.png");
                case 3 -> zaladujObraz("/gracz_obrazki/gracz_biegnacy_lewo_2.png");
            }
        }

        if (klucz == KeyEvent.VK_RIGHT || klucz == KeyEvent.VK_D) {
            dx = 2;
            kierunek = "prawy";
            switch (klatka){
                case 1 -> zaladujObraz("/gracz_obrazki/gracz_biegnacy_prawo_1.png");
                case 2, 4 -> zaladujObraz("/gracz_obrazki/gracz_bezczynny_prawo_0.png");
                case 3 -> zaladujObraz("/gracz_obrazki/gracz_biegnacy_prawo_2.png");
            }
        }
        if ((klucz == KeyEvent.VK_UP || klucz == KeyEvent.VK_W || klucz == KeyEvent.VK_SPACE) && dy == 0) {
            dy = -3;
        }
    }

    /**
     * Publiczna metoda graczStop sluzacza do zatrzymania gracza i
     * posredniego uruchomienia pasywnej animacji
     * @param klucz - liczbowa zmienna nacisnietego klawisza
     */
    public void graczStop(int klucz){
        if (klucz == KeyEvent.VK_LEFT || klucz == KeyEvent.VK_A) {
            kierunek = "pasywny_lewy";
            dx = 0;
        }
        if (klucz == KeyEvent.VK_RIGHT || klucz == KeyEvent.VK_D) {
            kierunek = "pasywny_prawy";
            dx = 0;
        }
    }
}
