/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.ladrillo;

/**
 *
 * @author GEIDG<correo>
 * @since 24052025
 * @version 1.0
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.Rectangle;

public class Ladrillo {
    private int x, y, ancho, alto;
    private boolean destruido;

    public Ladrillo(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.destruido = false;
    }

    public void draw(Graphics g) {
        if (!destruido) {
            g.setColor(Color.RED);
            g.fillRect(x, y, ancho, alto);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, ancho, alto);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    public boolean isDestruido() {
        return destruido;
    }

    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }
}
