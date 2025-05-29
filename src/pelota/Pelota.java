/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pelota;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import player.Player;
/**
 * clase que modela una pelota 
 * @author Sebastian<sebastian.villanedag@autonoma.edu.co>
 * @author Sebastian<juanc.cardonav@autonoma.edu.co>
 * @version 1.0
 * @since 27052025
 */
public class Pelota {
    //variables que contienen las coordenadas d 
    private int x, y;
    private int diametro = 15;
    private int dx = 4; // Velocidad horizontal
    private int dy = -4; // Velocidad vertical
    private boolean perdio = false;
    /**
     * metodo contructor
     * @param x : coordenada en x
     * @param y : coordenada en y
     */
    public Pelota(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void movimiento(int ancho, int alto) {
        if (perdio) return; // No mover si ya perdió

        x += dx;
        y += dy;

        // Rebote en paredes izquierda y derecha
        if (x <= 0 || x + diametro >= ancho) {
            dx = -dx;
        }

        // Rebote en el techo
        if (y <= 0) {
            dy = -dy;
        }

        // Si toca el fondo pierde
        if (y >= alto) {
            perdio = true;
        }
    }

    public void colisionarJugador(Player player) {
        if (perdio) return; 

        Rectangle ballRect = new Rectangle(x, y, diametro, diametro);
        Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());

        if (ballRect.intersects(playerRect)) {
            dy = -Math.abs(dy);

            int ballCenter = x + diametro / 2;
            int playerCenter = player.getX() + player.getWidth() / 2;

            dx = (ballCenter < playerCenter) ? -Math.abs(dx) : Math.abs(dx);

            y = player.getY() - diametro;
        }
    }
    //metodo para volver a redibujar la pelota cuando perdemos
    public void reset(int panelWidth, int panelHeight) {
        x = panelWidth / 2 - diametro / 2;
        y = panelHeight / 2 - diametro / 2;
        dx = 4;
        dy = -4;
        perdio = false;
    }

    public boolean perdio() {
        return perdio;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, diametro, diametro);
    }

    public int getX() {
        return x;
    }
    public int getY(){
    return y;
    }
    public int getDiameter() {
        return diametro; 
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, diametro, diametro);
     }

    public void rebotar() {
            dy = -dy;
        }
    }
