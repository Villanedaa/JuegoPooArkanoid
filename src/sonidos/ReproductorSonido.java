/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sonidos;

/**
 * clase reproductora de sonido
 *@author Sebastian<sebastian.villanedag@autonoma.edu.co>
 *  @author Sebastian<juanc.cardonav@autonoma.edu.co>
 * @since 21/05/2025
 * @version 1.0
 */



import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ReproductorSonido {
    //sirve para reproducir audios
    private Clip clip;

    public ReproductorSonido(String soundPath) {
        try {
            //url de donde esta nuestro audio
            URL url = getClass().getResource(soundPath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            //abre el audio
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e) {
            System.err.println("Error al cargar el sonido: " + e.getMessage());
        }
    }
    //bucle de reproduccion para generar los sonidos
    public void playLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }
    //metodo para detener el audio
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}

