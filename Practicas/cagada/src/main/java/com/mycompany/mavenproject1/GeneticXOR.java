package com.mycompany.mavenproject1;

import com.heatonresearch.book.introneuralnet.neural.feedforward.FeedforwardLayer;
import com.heatonresearch.book.introneuralnet.neural.feedforward.FeedforwardNetwork;
import com.heatonresearch.book.introneuralnet.neural.feedforward.train.genetic.TrainingSetNeuralGeneticAlgorithm;
import java.util.Scanner;

public class GeneticXOR {

    public static void main(final String args[]) {
        //EJEMPLO
//public static double XOR_INPUT[][] = {{0.0, 0.0}, {1.0, 0.0},{0.0, 1.0}, {1.0, 1.0}};
//    public static double XOR_IDEAL[][] = {{0.0}, {1.0}, {1.0}, {0.0}};
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los valores para XOR_INPUT:");
        double[][] XOR_INPUT = new double[4][2];
        for (int i = 0; i < 4; i++) {
            XOR_INPUT[i][0] = AnnealXOR.obtenerValorValido(scanner, String.format("Ingrese el primer número de la entrada %d (0 o 1): ", (i + 1)));
            XOR_INPUT[i][1] = AnnealXOR.obtenerValorValido(scanner, String.format("Ingrese el segundo número de la entrada %d (0 o 1): ", (i + 1)));
        }

        System.out.println("Ingrese los valores para XOR_IDEAL:");
        double[][] XOR_IDEAL = new double[4][1];
        for (int i = 0; i < 4; i++) {
            XOR_IDEAL[i][0] = AnnealXOR.obtenerValorValido(scanner, String.format("Para la entrada %d, ingrese el valor ideal (0 o 1): ", (i + 1)));
        }
        FeedforwardNetwork network = new FeedforwardNetwork();
        network.addLayer(new FeedforwardLayer(2));
        network.addLayer(new FeedforwardLayer(3));
        network.addLayer(new FeedforwardLayer(1));
        network.reset();

        final TrainingSetNeuralGeneticAlgorithm train = new TrainingSetNeuralGeneticAlgorithm(
                network, true, XOR_INPUT, XOR_IDEAL, 5000, 0.1, 0.25);
        int epoch = 1;
        do {
            train.iteration();
            System.out.println("Epoch #" + epoch + " Error:" + train.getError());
            epoch++;
        } while ((epoch < 5000) && (train.getError() > 0.001));

        network = train.getNetwork();

        System.out.println("Neural Network Results:");
        for (int i = 0; i < XOR_IDEAL.length; i++) {
            final double actual[] = network.computeOutputs(XOR_INPUT[i]);
            System.out.println(XOR_INPUT[i][0] + "," + XOR_INPUT[i][1] + ", actual=" + actual[0] + ",ideal=" + XOR_IDEAL[i][0]);
        }
    }
}
