/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Sebastian
 */
public class Player {
   
   private int x;
   private int y;
   //ancho
   private int width;
   //alto
   private int height;
   //velocidad de movimiento de 10 pixeles
   private int velocidad = 10;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }
    public Rectangle obtenerLimites() {
    return new Rectangle(x, y, width, height);
}

   
    
}
