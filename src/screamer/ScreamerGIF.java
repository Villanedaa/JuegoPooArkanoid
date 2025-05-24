package screamer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;

public class ScreamerGIF {

    /**
     * Reproduce el sonido, luego muestra un JFrame fullscreen con un GIF.
     * El JFrame se cierra automáticamente después de 2.5 segundos.
     * @param rutaGif ruta relativa del gif dentro del proyecto, ejemplo: "videos/jefGIF.gif"
     * @param rutaSonido ruta relativa del sonido dentro del proyecto, ejemplo: "sounds/gritoTerror.wav"
     */
    public static void mostrarScreamerConSonido(String rutaGif, String rutaSonido) {
        try {
            // Reproducir sonido
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                new File(ScreamerGIF.class.getClassLoader().getResource(rutaSonido).toURI())
            );
            clip.open(audioStream);
            clip.start();

            // Esperar 1 segundo antes de mostrar el GIF
            Thread.sleep(500);

            // Obtener tamaño completo de pantalla
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Rectangle screenRect = ge.getMaximumWindowBounds();

            // Crear ventana para mostrar el GIF
            JFrame frame = new JFrame();
            frame.setUndecorated(true); // Sin bordes
            frame.setAlwaysOnTop(true);
            frame.setBounds(screenRect); // Ocupa toda la pantalla usable (sin barra de tareas)

            // Cargar imagen GIF
            ImageIcon gifIcon = new ImageIcon(ScreamerGIF.class.getClassLoader().getResource(rutaGif));
            JLabel labelGif = new JLabel(gifIcon);
            labelGif.setHorizontalAlignment(SwingConstants.CENTER);
            labelGif.setVerticalAlignment(SwingConstants.CENTER);
            labelGif.setOpaque(true);
            labelGif.setBackground(Color.BLACK); // Fondo negro para no ver bordes raros

            frame.add(labelGif);

            // Mostrar ventana
            SwingUtilities.invokeLater(() -> frame.setVisible(true));

            // Esperar 2.5 segundos y cerrar ventana
            Thread.sleep(2500);
            SwingUtilities.invokeLater(() -> frame.dispose());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
