/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author laevie
 */
import com.heatonresearch.book.introneuralnet.neural.hopfield.HopfieldNetwork;

//Cosas de evies
import java.util.Scanner;

public class Mavenproject1 {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        // Crear instancia de HopfieldNetwork
        System.out.println("Escribe el tamaño de la matriz");
        int matrixSize = read.nextInt();
        HopfieldNetwork hopfield = new HopfieldNetwork(matrixSize);

        try {
            // Definir el patrón que se presentará a la red neuronal
            boolean[] pattern = {true, false, true, false};

            // Presentar el patrón a la red neuronal
            boolean[] result = hopfield.present(pattern);

            // Imprimir el proceso de la presentación del patrón
            System.out.println("Proceso de la presentación del patrón:");
            for (int i = 0; i < pattern.length; i++) {
                System.out.println("Procesando bit " + (i + 1) + ": Entrada = " + pattern[i] + ", Salida = " + result[i]);
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir durante la presentación
            System.err.println("Error al presentar el patrón: " + e.getMessage());
        }

        try {
            // Definir el patrón con el que se entrenará la red neuronal
            boolean[] patternToTrain = {true, true, false, false};

            // Entrenar la red neuronal con el patrón especificado
            hopfield.train(patternToTrain);

            // Imprimir el proceso del entrenamiento
            System.out.println("\nProceso del entrenamiento:");
            for (int i = 0; i < patternToTrain.length; i++) {
                System.out.println("Procesando bit " + (i + 1) + ": Entrada = " + patternToTrain[i]);
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir durante el entrenamiento
            System.err.println("Error al entrenar la red neuronal: " + e.getMessage());
        }
    }
}