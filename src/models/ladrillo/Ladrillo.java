/*
 * Click nbfs://nbhost/SystemFile/Templates/Licenses/license.txt to change this license
 * Click nbfs://nbhost/SystemFile/Templates/Classes/Class.java to edit this template
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
import java.awt.Color;
import java.awt.Font;

public class Ladrillo {
    private int x, y, ancho, alto;
    private boolean destruido;
    private int valorPuntos;
    private static Image imagenLadrillo;

    private long tiempoDestruccion = 0;
    private static final long DURACION_MOSTRAR_PUNTOS = 1000;

    static {
        imagenLadrillo = new ImageIcon(Ladrillo.class.getResource("/images/ladrilloSangre.jpeg")).getImage();
    }

    public Ladrillo(int x, int y, int ancho, int alto, int fila) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.destruido = false;
        this.valorPuntos =  1;
    }

    public void draw(Graphics g) {
        if (!destruido) {
            g.drawImage(imagenLadrillo, x, y, ancho, alto, null);
        } else {
            long tiempoActual = System.currentTimeMillis();
            if (tiempoActual - tiempoDestruccion < DURACION_MOSTRAR_PUNTOS) {
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString("+" + valorPuntos, x + ancho / 4, y + alto / 2);
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    public boolean isDestruido() {
        return destruido;
    }

    public void setDestruido(boolean destruido) {
        if (destruido && !this.destruido) {
            this.tiempoDestruccion = System.currentTimeMillis();
        }
        this.destruido = destruido;
    }

    public int getValorPuntos() {
        return valorPuntos;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
