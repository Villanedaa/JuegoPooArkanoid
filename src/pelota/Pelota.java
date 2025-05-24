/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pelota;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import player.Player;

public class Pelota {
    private int x, y;
    private int diametro = 15;
    private int dx = 4; // Velocidad horizontal
    private int dy = -4; // Velocidad vertical
    private boolean perdio = false;

    public Pelota(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int ancho, int alto) {
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

        // Si toca el fondo (pierde)
        if (y >= alto) {
            perdio = true;
        }
    }

    public void checkCollisionWithPlayer(Player player) {
        if (perdio) return; // No colisiones si ya perdió

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
