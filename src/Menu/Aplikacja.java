package Menu;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.Objects;

/**
 * Klasa Aplikacja czyli glowna klasa implementuje uruchomienie calej gry
 * platformowki a konkretnie menu tej gry
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class Aplikacja {
    /**
     * Metoda statyczna sluzaca do uruchomienia calej ramki aplikacji,ikony i menu gry
     *
     * @param args sluzy do uruchomienia calej aplikacji
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame okno = new JFrame("Prosta gra platformowa");
            okno.pack();
            okno.setVisible(true);
            okno.setSize(640,640);
            okno.setResizable(true);

            okno.add(new GlowneMenuPanel());
            okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            okno.setLocationRelativeTo(null);
            okno.setResizable(false);
            try {
                okno.setIconImage(ImageIO.read(Objects.requireNonNull(Aplikacja.class.getResourceAsStream("/moneta.png"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}