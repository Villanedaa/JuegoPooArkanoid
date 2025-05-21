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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import pelota.Pelota;
/**
 * Ventana principal del juego
 * @author Sebastian<sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 21/05/2025
 */

public class PantallaPrincipal extends JPanel {
    private Pelota pelota;
    private Player jugador;
    private Image fondo;
    public PantallaPrincipal() {
         fondo = new ImageIcon(getClass().getResource("/images/Jena.jpeg")).getImage();
        //permite los eventos del teclado
        setFocusable(true);
        //arregla los parpadeos de pantalla
        setDoubleBuffered(true);
        
        // posicion inicial del jugador
        jugador = new Player(350, 550, 100, 15);
        //posicion de la pellota
        pelota = new Pelota(390, 400);

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
         * 
         * repintar cada 16 ms
         * sirve para evitar que el juego o la pantalla se congele
         * este tambien funciona para que nuestro frame se siga repitando asi no hayan
         * eventos de teclado
         */
       Timer timer = new Timer(16, e -> {
    pelota.move(getWidth(), getHeight());
    pelota.checkCollisionWithPlayer(jugador);
    repaint();
    });
       //se inicia el timer
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
    
    //dibujar pelota
    pelota.draw(g);
}
    
    /**protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //imagen de fondo
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        //pintamos el jugador
        jugador.draw(g);
        
    }*/
    
    public static void main(String[] args) {
        //sonido de fondo
         ReproductorSonido sonido = new ReproductorSonido("/sounds/sonidoFondoJuego.wav");
         sonido.playLoop();
         //se genera la ventana
        JFrame ventanaJuego = new JFrame("ARKANOID-TERROR");
        //cierra la ventana y finaliza todos los procesos que se esten ejecutando en segundo plano
        ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaJuego.setSize(800, 600);
        //centrar la pantallaa
        ventanaJuego.setLocationRelativeTo(null);
        ventanaJuego.add(new PantallaPrincipal());
        ventanaJuego.setVisible(true);
    }
}

