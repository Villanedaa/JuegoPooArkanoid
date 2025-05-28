/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * clase que modela a nuestro jugador
 * @author Sebastian<sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 21/05/2025
 */
public class Player {
   //coordenadas en x
   private int x;
   //coordenadas en y
   private int y;
   //ancho
   private int width;
   //alto
   private int height;
   //velocidad de movimiento de 10 pixeles
   private int velocidad = 12;
   // imagen que sera dibujada para nuestro jugador
   private Image imagen;
   // vidas del jugador
   private int vidas;

    /**
     * Metodo constructor
     * @param x : coordenadas en x
     * @param y: coordenadas en y
     * @param width : ancho
     * @param height  : alto
     */
    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.vidas = 2; // el jugador inicia con 2 vidas
        imagen = new ImageIcon(getClass().getResource("/images/maderaJugador.jpeg")).getImage();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void moverIzquierda() {
        x -= velocidad;
        if (x < 0) x = 0;
    }

    public void moverDerecha(int limitePantalla) {
        x += velocidad;
        if (x + width > limitePantalla) {
            x = limitePantalla - width;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(imagen, x, y, width, height, null);
    }

    public Rectangle obtenerLimites() {
        return new Rectangle(x, y, width, height);
    }
    public void setVidas(int vidas){
    
        this.vidas = vidas;
    }
    // método para obtener las vidas
    public int getVidas() {
        return vidas;
    }

    // método para restar una vida
    public void perderVida() {
        if (vidas > 0) {
            vidas--;
        }
    }
}
