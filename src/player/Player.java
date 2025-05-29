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
 *  @author Sebastian<juanc.cardonav@autonoma.edu.co>
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
        //vidas iniciales del jugador
        this.vidas = 2; 
        imagen = new ImageIcon(getClass().getResource("/images/maderaJugador.jpeg")).getImage();
    }
/*¨*
    metodo que retorna nuestra coordenada en X
    */
    public int getX() {
        return x;
    }
    /**
     * metodo que asigna un valor a nuestra coordenada en X
     * @param x : coordenada en X
     */

    public void setX(int x) {
        this.x = x;
    }
    /**
     * metodo que retorna nuestra coordenada en Y
     * @return : se retorna y
     */
    public int getY() {
        return y;
    }
    /**
     * metodo para asignarle un valor a nuestra coordenada en Y
     * @param y : coordenada en y
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * metodo que retorna nuestro ancho
     * @return : se retorna el ancho
     */
    public int getWidth() {
        return width;
    }
    /**
     * metodo para asignarle un valor al ancho
     * @param width : ancho
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * metodo para obterner nuestro alto
     * @return : se retorna la variable alto
     */
    public int getHeight() {
        return height;
    }
    /**
     * metodo para asignarle un valor a nuestra variable alto
     * @param height : alto
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * metodo para movernos a la izquierda mediante eventos de teclado
     */
    public void moverIzquierda() {
        x -= velocidad;
        if (x < 0) x = 0;
    }
    /**
     * metodo para movernos a la derecha mediante eventos de teclado
     * @param limitePantalla : se refiere al limite de la pantalla para no salirnos de ella
     */
    public void moverDerecha(int limitePantalla) {
        x += velocidad;
        if (x + width > limitePantalla) {
            x = limitePantalla - width;
        }
    }
    /**
     * metodo para divujar nuestro jugador
     * @param g 
     */
    public void draw(Graphics g) {
        g.drawImage(imagen, x, y, width, height, null);
    }
    /**
     * metodo que retorna los limites de la pantalla
     * @return : limites de pantalla
     */
    public Rectangle obtenerLimites() {
        return new Rectangle(x, y, width, height);
    }
    
    /**
     * metodo para asignarle un valor a nuestras vidas
     * @param vidas : vidas del jugador
     */
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
