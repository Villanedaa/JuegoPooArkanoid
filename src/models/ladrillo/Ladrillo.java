package models.ladrillo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * clase que modela un ladrillo
 * @author Sebastian<sebastian.villanedag@autonoma.edu.co>
 * @author Sebastian<juanc.cardonav@autonoma.edu.co>
 * @version 1.0
 * @since 27052025
 */

public class Ladrillo {
    // Variables para inicializar los ladrillos
    private int x, y, ancho, alto;
    // Variable booleana conoce el estado del ladrillo
    private boolean destruido;
    // Variable para nuestros puntos
    private int valorPuntos;
    // Imagen que ocupará nuestro ladrillo
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
        this.valorPuntos = 1;
    }

    public void draw(Graphics g) {
        if (!destruido) {
            g.drawImage(imagenLadrillo, x, y, ancho, alto, null);
        }
        // Si está destruido, no se dibuja nada
    }
    // Si está destruido, no se dibuja nada
    public Rectangle getLimites() {
        return new Rectangle(x, y, ancho, alto);
    }
    // Si está destruido, no se dibuja nada
    public boolean isDestruido() {
        return destruido;
    }
    // metodo para cambiaar el estado destruido 
    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }
    // metodo valor de puntos 
    public int getValorPuntos() {
        return valorPuntos;
    }
    // metodo para obtener cordena en x
    public int getX() {
        return x;
    }
    // metodo para obtener cordenada en y
    public int getY() {
        return y;
    }
    // metodo ancho
    public int getAncho() {
        return ancho;
    }
    // metodo alto
    public int getAlto() {
        return alto;
    }
}
