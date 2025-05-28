/*
 * Click nbfs://nbhost/SystemFile/Templates/Licenses/license.txt to change this license
 * Click nbfs://nbhost/SystemFile/Templates/Classes/Class.java to edit this template
 */
package jumpscare;
/**
 * se importan las clases necesarias
 */
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import sonidos.ReproductorSonido;
/**
 * clase que modela a nuestro Screamer el cual saldra cuando el jugador pierda una vida
 * @author Sebastian<sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 24052025
 */
public class Screamer {

    public static void mostrarScreamerConSonido(String rutaGif, String rutaSonido) {
        // Crear ventana sin bordes y a pantalla completa
        JFrame ventana = new JFrame();
        ventana.setUndecorated(true);
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setAlwaysOnTop(true);

        // Cargar GIF
        ImageIcon gif = new ImageIcon(Screamer.class.getResource("/" + rutaGif));
        JLabel etiquetaGif = new JLabel(gif);
        ventana.add(etiquetaGif);
        ventana.pack();
        ventana.setVisible(true);

        // Reproducir sonido
        ReproductorSonido sonido = new ReproductorSonido("/" + rutaSonido);
        sonido.playLoop();

        // Cerrar ventana y parar sonido después d3 2.5 seg)
        new Timer(2500, e -> {
            sonido.stop();
            ventana.dispose();
        }).start();
    }
}
