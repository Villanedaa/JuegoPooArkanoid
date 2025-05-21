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
   private int velocidad = 10;
   // imagen que sera dibujada para nuestro jugador
    private Image imagen;
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
        // Cargar imagen desde la carpeta images
        imagen = new ImageIcon(getClass().getResource("/images/maderaJugador.jpeg")).getImage();
    }
   /**
    * metodo que retorna coordenadas en x
    * @return 
    */
    public int getX() {
        return x;
    }
    /**
     * metodo para establecer coordenadas en x
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * metodo para retornar coordenadas en y
     * @return 
     */
    public int getY() {
        return y;
    }
    /**
     * metodo para establecer coordenadas en y
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * metodo pafa obtener el ancho
     * @return 
     */
    public int getWidth() {
        return width;
    }
    /**
     * metodo para establecer el ancho
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * metodo para obtener el alto
     * @return 
     */
    public int getHeight() {
        return height;
    }
    /**
     * metodo para establecer el alto
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }
   /**
    * metodo para mover nuestro jugador a la izquierda
    * aca el limite es 0
    */
    public void moverIzquierda() {
        x -= velocidad;
        if (x < 0) x = 0;
    }
        /**
         * metodo para mover nuestro jugador a la derecha
         * @param limitePantalla 
         */
        public void moverDerecha(int limitePantalla) {
        x += velocidad;
        if (x + width > limitePantalla) {
            x = limitePantalla - width;
        }
    }
    /**
     * metodo que dibuja nuestro jugador
     * @param g 
     */
    public void draw(Graphics g) {
        g.drawImage(imagen, x, y, width, height, null);
    }
    public Rectangle obtenerLimites() {
    return new Rectangle(x, y, width, height);
}

   
    
}
