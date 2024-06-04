/**
 * Introduction to Neural Networks with Java, 2nd Edition
 * Copyright 2008 by Heaton Research, Inc.
 * http://www.heatonresearch.com/books/java-neural-2/
 *
 * ISBN13: 978-1-60439-008-7
 * ISBN:   1-60439-008-5
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.mycompany.mavenproject1;

import com.heatonresearch.book.introneuralnet.neural.feedforward.FeedforwardLayer;
import com.heatonresearch.book.introneuralnet.neural.feedforward.FeedforwardNetwork;
import com.heatonresearch.book.introneuralnet.neural.feedforward.train.anneal.NeuralSimulatedAnnealing;
import java.util.Scanner;

/**
 * Chapter 7: Training using Simulated Annealing
 *
 * XOR: Learn the XOR pattern with a feedforward neural network that uses
 * simulated annealing.
 *
 * @author Jeff Heaton
 * @version 2.1
 */
public class AnnealXOR {

    public static void main(final String args[], Scanner scanner) {
        //EJEMPLO
        //public static double XOR_INPUT[][] = {{0.0, 0.0}, {1.0, 0.0},{0.0, 1.0}, {1.0, 1.0}};
        //public static double XOR_IDEAL[][] = {{0.0}, {1.0}, {1.0}, {0.0}};

        System.out.println("Ingrese los valores para XOR_INPUT:");
        double[][] XOR_INPUT = new double[4][2];
        for (int i = 0; i < 4; i++) {
            XOR_INPUT[i][0] = obtenerValorValido(scanner, String.format("Ingrese el primer número de la entrada %d (0 o 1): ", (i + 1)));
            XOR_INPUT[i][1] = obtenerValorValido(scanner, String.format("Ingrese el segundo número de la entrada %d (0 o 1): ", (i + 1)));
        }

        System.out.println("Ingrese los valores para XOR_IDEAL:");
        double[][] XOR_IDEAL = new double[4][1];
        for (int i = 0; i < 4; i++) {
            XOR_IDEAL[i][0] = obtenerValorValido(scanner, String.format("Para la entrada %d, ingrese el valor ideal (0 o 1): ", (i + 1)));
        }

        final FeedforwardNetwork network = new FeedforwardNetwork();
        network.addLayer(new FeedforwardLayer(2));
        network.addLayer(new FeedforwardLayer(3));
        network.addLayer(new FeedforwardLayer(1));
        network.reset();

        // train the neural network
        final NeuralSimulatedAnnealing train = new NeuralSimulatedAnnealing(
                network, XOR_INPUT, XOR_IDEAL, 10, 2, 100);

        int epoch = 1;

        do {
            train.iteration();
            System.out.println("Epoch #" + epoch + " Error:" + train.getError());
            epoch++;
        } while ((epoch < 100) && (train.getError() > 0.001));

        // network = train.getNetwork();
        // test the neural network
        System.out.println("Resultados:");
        for (int i = 0; i < XOR_IDEAL.length; i++) {
            final double actual[] = network.computeOutputs(XOR_INPUT[i]);
            System.out.println(XOR_INPUT[i][0] + "," + XOR_INPUT[i][1]
                    + ", actual=" + actual[0] + ",ideal=" + XOR_IDEAL[i][0]);
        }
    }

    public static double obtenerValorValido(Scanner scanner, String mensaje) {
        double valor;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();
                if (valor == 0 || valor == 1) {
                    break;
                } else {
                    System.out.println("Por favor, ingrese un valor válido (0 o 1).");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número (0 o 1).");
                scanner.next(); // descartar la entrada no válida
            }
        }
        return valor;
    }
}
