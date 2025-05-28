/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package puntaje;

/**
 * clase que modela el sistema de puntaje
 * @authorc camilo cardona
 * @version 1.0
 * @since 27/05/2025
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Puntaje {
    //variable que guardata nuestros puntos
    private int puntos;
    //variable que multiplicara nuestros puntos cuanto tengamos una racha
    private int multiplicador;
    private long tiempoInicio;
    //variable que guardara cuantos ladrillos golpeamos de seguido
    private int racha;

    /**
     * metodo constructor 
     */
    public Puntaje() {
        this.puntos = 0;
        this.multiplicador = 1;
        //this.tiempoInicio = System.currentTimeMillis();
        this.racha = 0;
    }
    /**
     * metooo para ir sumando puntos
     */
    public void agregarPuntos(int base) {
        this.puntos += base * multiplicador;
        this.racha++;
        if (racha % 5 == 0) {
            multiplicador++;
        }
    }
    /**
     * metodo para cuando perdamos una vida, se resetea la racha
     */
    public void resetearRacha() {
        this.multiplicador = 1;
        this.racha = 0;
    }

  //  public int calcularBonificacionTiempo() {
    //    long tiempoTranscurrido = (System.currentTimeMillis() - tiempoInicio) / 1000;
      //return Math.max(0, 0 - (int)tiempoTranscurrido * 10);
    //}
    /**
     * metodo que retorna nuestro total de puntos
     * @return : puntos
     */
    public int getPuntos() {
        return puntos;
    }
     /**
      *  metodo para obtener nuetro multiplicador de puntos
      * @return : multiplicador
      */
    public int  getMultiplicador() {
        return multiplicador;
    }
    /**
     * metodo para obtener nuestra racha
     * @return : racha
     */
    public int getRacha() {
        return racha;
    }

    /**
     * Guarda el puntaje actual en un archivo de texto
     * @param nombreArchivo Nombre del archivo donde se almacenará el puntaje
     */
    public void guardarPuntajeEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write("Puntos: " + getPuntos() + ", Racha: " + racha);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar el puntaje: " + e.getMessage());
        }
    }
    
}
