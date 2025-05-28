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
public class Puntaje {
    private int puntos;
    private int multiplicador;
    private long tiempoInicio;
    private int racha;
/**
 * metodo constructor 
 */
    public Puntaje() {
        this.puntos = 0;
        this.multiplicador = 1;
        this.tiempoInicio = System.currentTimeMillis();
        this.racha = 0;
    }

    public void agregarPuntos(int base) {
        this.puntos += base * multiplicador;
        this.racha++;
        if (racha % 5 == 0) {
            multiplicador++;
        }
    }

    public void resetearRacha() {
        this.multiplicador = 1;
        this.racha = 0;
    }

    public int calcularBonificacionTiempo() {
        long tiempoTranscurrido = (System.currentTimeMillis() - tiempoInicio) / 1000;
        return Math.max(0, 0 - (int)tiempoTranscurrido * 10);
    }

    public int getPuntos() {
        return puntos + calcularBonificacionTiempo();
    }

    public int getMultiplicador() {
        return multiplicador;
    }

    public int getRacha() {
        return racha;
    }
}