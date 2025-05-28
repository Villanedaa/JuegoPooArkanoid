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
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import models.ladrillo.Ladrillo;
import puntaje.Puntaje;
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
    private Puntaje puntaje;
    private int puntajeVisual = 0; // Puntaje mostrado visualmente

    public PantallaPrincipal() {
        fondo = new ImageIcon(getClass().getResource("/images/Jena.jpeg")).getImage();
        setFocusable(true);
        setDoubleBuffered(true);
        puntaje = new Puntaje();

        jugador = new Player(350, 550, 100, 15);
        jugador.setVidas(2); // Establecer 2 vidas al iniciar
        pelota = new Pelota(390, 400);

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
                ladrillos.add(new Ladrillo(x, y, anchoLadrillo, altoLadrillo, fila));
            }
        }

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

        Timer timer = new Timer(16, e -> {
            if (!enScreamer) {
                pelota.move(getWidth(), getHeight());
                pelota.checkCollisionWithPlayer(jugador);

                for (Ladrillo ladrillo : ladrillos) {
                    if (!ladrillo.isDestruido() && pelota.getBounds().intersects(ladrillo.getBounds())) {
                        ladrillo.setDestruido(true);
                        puntaje.agregarPuntos(1);  // <-- Aquí sumamos solo 1 punto por ladrillo
                        pelota.rebotar();
                        break;
                    }
                }

                // Incrementar visualmente el puntaje de uno en uno (animado)
                if (puntajeVisual < puntaje.getPuntos()) {
                    puntajeVisual++;
                }

                if (pelota.perdio()) {
                    // Se perdió una vida
                    jugador.setVidas(jugador.getVidas() - 1);
                    puntaje.resetearRacha();
                    enScreamer = true;

                    // Si ya no tiene vidas, termina el juego
                    if (jugador.getVidas() <= 0) {
                        puntaje.guardarPuntajeEnArchivo("puntajes.txt");

                        new Thread(() -> {
                            ScreamerGIF.mostrarScreamerConSonido("videos/jefGIF.gif", "sounds/gritoTerror.wav");
                            javax.swing.SwingUtilities.invokeLater(() -> {
                                new VolverAJugar().setVisible(true);
                                // Obtener ventana contenedora del panel y cerrarla
                                java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);
                                if (ventana != null) {
                                    ventana.dispose();
                                    
                                }
                            });
                        }).start();
                    }else {
                        new Thread(() -> {
                            ScreamerGIF.mostrarScreamerConSonido("videos/jefGIF.gif", "sounds/gritoTerror.wav");
                            pelota.reset(getWidth(), getHeight());
                            enScreamer = false;
                        }).start();
                    }
                }
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        int imgWidth = fondo.getWidth(this);
        int imgHeight = fondo.getHeight(this);
        int x = (getWidth() - imgWidth) / 2;
        int y = (getHeight() - imgHeight) / 2;
        g.drawImage(fondo, x, y, this);

        // Dibujar información de puntaje y vidas
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Puntos: " + puntajeVisual, 20, 30);
        g.drawString("Racha: " + puntaje.getRacha(), 300, 30);
        g.drawString("Vidas: " + jugador.getVidas(), 600, 30);

        jugador.draw(g);
        pelota.draw(g);

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
