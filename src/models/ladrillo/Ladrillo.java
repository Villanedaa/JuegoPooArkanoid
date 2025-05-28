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


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Ladrillo {
    private int x, y, ancho, alto;
    private boolean destruido;
    private int valorPuntos;
    private static Image imagenLadrillo;

    static {
        imagenLadrillo = new ImageIcon(Ladrillo.class.getResource("/images/ladrilloSangre.jpeg")).getImage();
    }

    public Ladrillo(int x, int y, int ancho, int alto, int fila) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.destruido = false;
        this.valorPuntos = (6 - fila) * 10;
    }

    public void draw(Graphics g) {
        if (!destruido) {
            g.drawImage(imagenLadrillo, x, y, ancho, alto, null);
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

    public int getValorPuntos() {
        return valorPuntos;
    }
}

