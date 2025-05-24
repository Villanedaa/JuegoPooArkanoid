/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import sonidos.ReproductorSonido;
import player.Player;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import models.ladrillo.Ladrillo;
import pelota.Pelota;
import screamer.ScreamerGIF;

/**
 * Ventana principal del juego
 * @author Sebastian<sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 21/05/2025
 */

public class PantallaPrincipal extends JPanel {
    private boolean enScreamer = false;
    private Pelota pelota;
    private Player jugador;
    private Image fondo;
    private ArrayList<Ladrillo> ladrillos;


    public PantallaPrincipal() {
         fondo = new ImageIcon(getClass().getResource("/images/Jena.jpeg")).getImage();
        //permite los eventos del teclado
        setFocusable(true);
        //arregla los parpadeos de pantalla
        setDoubleBuffered(true);
        
        // posicion inicial del jugador
        jugador = new Player(350, 550, 100, 15);
        //posicion de la pelota
        pelota = new Pelota(390, 400);
        
        // se crean los ladrillos 
        ladrillos = new ArrayList<>();
        int columnas = 10;
        int filas = 5;
        int anchoLadrillo = 60;
        int altoLadrillo = 20;
        int espacio = 10;
        int margenX = 50;
        int margenY = 50;

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int x = margenX + col * (anchoLadrillo + espacio);
                int y = margenY + fila * (altoLadrillo + espacio);
                ladrillos.add(new Ladrillo(x, y, anchoLadrillo, altoLadrillo));
            }
        }

        // eventos de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    jugador.moverIzquierda();
                } else if (key == KeyEvent.VK_RIGHT) {
                    jugador.moverDerecha(getWidth());
                }
                repaint();
            }
        });

        /**
         * repintar cada 16 ms (aprox 60 FPS)
         * sirve para evitar que el juego o la pantalla se congele
         * este también funciona para que nuestro frame se siga repitiendo así no hayan
         * eventos de teclado
         */
       Timer timer = new Timer(16, e -> {
            if (!enScreamer) {
                pelota.move(getWidth(), getHeight());
                pelota.checkCollisionWithPlayer(jugador);

                if (pelota.perdio()) {
                    enScreamer = true;

                    // Mostrar screamer + sonido en otro hilo para no bloquear GUI
                    new Thread(() -> {
                        ScreamerGIF.mostrarScreamerConSonido("videos/jefGIF.gif", "sounds/gritoTerror.wav");

                        // Reiniciar pelota y continuar juego
                        pelota.reset(getWidth(), getHeight());
                        enScreamer = false;
                    }).start();
                }
            }
            
            repaint();
            for (Ladrillo ladrillo : ladrillos) {
    if (!ladrillo.isDestruido() && pelota.getBounds().intersects(ladrillo.getBounds())) {
        ladrillo.setDestruido(true);
        pelota.rebotar(); // debes asegurarte de tener este método o implementarlo
        break;
    }
}
        });
       timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // pintar fondo negro
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        int imgWidth = fondo.getWidth(this);
        int imgHeight = fondo.getHeight(this);

        // centrar la imagen para no deformarla
        int x = (getWidth() - imgWidth) / 2;
        int y = (getHeight() - imgHeight) / 2;

        // Dibujar imagen centrada sin deformar
        g.drawImage(fondo, x, y, this);

        // Dibujar el jugador
        jugador.draw(g);

        // Dibujar la pelota
        pelota.draw(g);
        //dibujar los ladrillos
        for (Ladrillo ladrillo : ladrillos) {
            ladrillo.draw(g);
}
    }
/*
    public static void main(String[] args) {
        //sonido de fondo
        ReproductorSonido sonido = new ReproductorSonido("/sounds/sonidoFondoJuego.wav");
        sonido.playLoop();

        //se genera la ventana
        JFrame ventanaJuego = new JFrame("ARKANOID-TERROR");
        //cierra la ventana y finaliza todos los procesos que se estén ejecutando en segundo plano
        ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaJuego.setSize(800, 600);
        //centrar la pantalla
        ventanaJuego.setLocationRelativeTo(null);
        ventanaJuego.add(new PantallaPrincipal());
        ventanaJuego.setVisible(true);
    }
    */
}
