package Menu;

import Panele.MenuPanel;
import Panele.OpcjePanel;
import Panele.PanelGry;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa GlowneMenuPanel czyli glowna klasa odpowiadajaca za wczytanie
 * platformowki a konkretnie menu tej gry. Klasa jest "wydluzeniem" JPanelu
 * i implementuje metody klasy ActionListener
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class GlowneMenuPanel extends JPanel implements ActionListener {
    /** Zmienna odpowiadajaca za stan gry*/
    protected int graStan = 1;
    /** Timer(czasomierz) odpowiadajacy za czas w menu ale takze w grze i innych panelach*/
    private final Timer czasMenu;
    /** Instancja Klasy PanelMenu ktora sluzy do wczytania wizualnej czesci menu*/
    private final MenuPanel mP = new MenuPanel();
    /** Instancja Klasy OpcjePanel ktora sluzy do wczytania opcji*/
    private final OpcjePanel oP = new OpcjePanel();
    /** Instancja Klasy PanelGry ktora sluzy do wczytania glownej gry*/
    private final PanelGry pG = new PanelGry();

    /**
     * Ten konstruktor sluzy do odtworzenia muzyki,dodania nasluchiwacza kluczy
     * a takze dodania i uruchomienia czasomierza
     */
    public GlowneMenuPanel(){
        Muzyka mZ = new Muzyka();
        addKeyListener(new KluczePoruszanie(this,pG, mZ));
        setFocusable(true);
        mZ.grajMuzyke(2);


        czasMenu = new Timer(10, this);
        czasMenu.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        wybierzPanel(graStan,g);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Prywatna metoda wybierzPanel ktora sluzy do zaladowania panelu w zaleznosci
     * od zmiennej panelNr i rysuje korzystajac z zminnej graficznej g.
     *
     * @param panelNr - jest tekstowa wersja czasu podanego przez uzytkownika
     * @param g - jest tekstowa wersja czasu podanego przez uzytkownika
     */
    private void wybierzPanel(int panelNr,Graphics g){

        switch (panelNr){
            case 0 -> {
                czasMenu.stop();
                System.exit(0);
            }
            case 1 -> {
                setBackground(Color.CYAN);
                mP.rysujMenu(g);
            }
            case 2 -> oP.rysujOpcje(g);
            case 3 -> {
                pG.graczStan = 1;
                setBackground(Color.blue);
                pG.zrobRysowanie(g);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (graStan){
            case 1 -> mP.zmienTekst();
            case 2 -> oP.zmienTekst();
            case 3 -> pG.aktualizuj();
        }
        repaint();
    }
}
