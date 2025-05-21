/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import player.Player;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
/**
 *
 * @author Sebastian<sebastian.villanedag@autonoma.edu.co>
 */

public class PantallaPrincipal extends JPanel {
   
    private Player jugador;

    public PantallaPrincipal() {
        //permite los eventos del teclado
        setFocusable(true);
        //arregla los parpadeos de pantalla
        setDoubleBuffered(true);

        // posicion inicial del jugador
        jugador = new Player(350, 550, 100, 15);

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
        Timer timer = new Timer(16, e -> repaint());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //pintamos el jugador
        jugador.draw(g);
    }
    public static void main(String[] args) {
        JFrame ventanaJuego = new JFrame("ARKANOID-TERROR");
        //cierra la ventana y finaliza todos los procesos que se esten ejecutando en segundo plano
        ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaJuego.setSize(800, 600);
        //centrar la pantalla
        ventanaJuego.setLocationRelativeTo(null);
        ventanaJuego.add(new PantallaPrincipal());
        ventanaJuego.setVisible(true);
    }
}

