package Deepspell.Training;

import java.util.Arrays;

public class Architecture {

    /**
     * Performs dot product of two matrices.
     *
     * @param matrix1 First matrix to be multiplied.
     * @param matrix2 Second matrix to be multiplied.
     * @return The dot product of the two input matrices.
     * @throws IllegalArgumentException If the number of columns in matrix1 is not equal to the number of rows in matrix2.
     */
    public static double[][] dotProduct(double[][] matrix1, double[][] matrix2) {
        if (matrix1[0].length != matrix2.length) {
            throw new IllegalArgumentException("The number of columns in matrix1 must be equal to the number of rows in matrix2.");
        }
        double [][] dot = new double[matrix1.length][matrix2[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                double sum = 0.0;
                for (int k = 0; k < matrix2.length; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                dot[i][j] = sum;
            }
        }
        return dot;
    }
    /**
     * Performs dot product of two matrices.
     *
     * @param matrix1 First matrix to be multiplied.
     * @param matrix2 Second matrix to be multiplied.
     * @param notArray This parameter is ignored. It is present to differentiate between this method and the other dotProduct method.
     * @return The dot product of the two input matrices.
     */
    public static double dotProduct(double[][] matrix1, double[][] matrix2, String notArray) {
        double dot = 0;
        int n = matrix1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dot += matrix1[i][j] * matrix2[j][i];
            }
        }
        return dot;
    }
    /**
     * Adds two vectors element-wise.
     *
     * @param vector1 The first vector to be added.
     * @param vector2 The second vector to be added.
     * @return A new vector containing the element-wise sum of the input vectors.
     * @throws IllegalArgumentException If the two input vectors do not have the same length.
     */
    public static double[] addVectors(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("The two vectors must have the same length.");
        }
        double [] add = new double[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            add[i] = vector1[i] + vector2[i];
        }
        return add;
    }
    /**
     * Adds two matrices element-wise.
     *
     * @param matrix1 The first matrix to be added.
     * @param matrix2 The second matrix to be added.
     * @return A new matrix containing the element-wise sum of the input matrices.
     * @throws IllegalArgumentException If the two input matrices are not of the same size.
     */
    public static double[][] addMatrices(double[][] matrix1, double[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length){
            throw new IllegalArgumentException("The matrices incompatible.");
        }
        double[][] add = new double[matrix1.length][matrix2[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                add[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return add;
    }
    /**
     * Subtracts two vectors element-wise.
     *
     * @param vector1 The vector to be subtracted from.
     * @param vector2 The vector to subtract.
     * @return A new vector containing the element-wise difference of the input vectors.
     * @throws IllegalArgumentException If the two input vectors do not have the same length.
     */
    public static double[] subtractVectors(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("The two vectors must have the same length.");
        }
        double [] substract = new double[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            substract[i] = vector1[i] - vector2[i];
        }
        return substract;
    }
    /**
     * Subtracts two matrices element-wise.
     *
     * @param matrix1 The matrix to be subtracted from.
     * @param matrix2 The matrix to subtract.
     * @return A new matrix containing the element-wise difference of the input matrices.
     * @throws IllegalArgumentException If the two input matrices are not of the same size.
     */
    public static double[][] subtractMatrices(double[][] matrix1, double[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length){
            throw new IllegalArgumentException("The matrices incompatible.");
        }
        double[][] substract = new double[matrix1.length][matrix2[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                substract[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return substract;
    }
    // Method to multiply a vector by a scalar
    public static double[] multiplyVectorScalar(double[] vector, double scalar) {
        double [] result = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            result[i] = vector[i] * scalar;
        }
        return result;
    }
    // Method to multiply a matrix by a scalar
    public static double[][] multiplyMatrixScalar(double[][] matrix, double scalar) {
        double [][] result = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }
        return result;
    }
    // Method to multiply two vectors element-wise
    public static double[] multiplyVectors(double[] vector1, double[] vector2) {
       double [] solution = new double[vector1.length];

        for (int i = 0; i < vector1.length; i++) {
            solution[i] = vector1[i] * vector2[i];
        }
        return solution;
    }

    // Method to multiply two matrices element-wise
    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        double[][] solution = new double[matrix1.length][matrix1.length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                solution[i][j] = matrix1[i][j] * matrix2[i][j];
            }
        }
        return solution;
    }

    // Method to get the transpose of a matrix
    public static double[][] transpose(double[][] matrix) {
        double[][] solution = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                solution[j][i] = matrix[i][j];
            }
        }
        return solution;
    }

    // Method to apply an activation function to a vector
    public static double[] applyActivation(double[] vector, String activationFunction) {
        double [] solution = new double[vector.length];
            switch (activationFunction){
                case "sigmoid" -> solution = sigmoid(vector);
                case "relu" -> solution = relu(vector);
                case "softmax" -> solution = softmax(vector);
                default ->  throw new IllegalArgumentException("Invalid activation function specified.");
                }
        return solution;
    }

    // Method to apply the derivative of an activation function to a vector
    public static double[] applyActivationDerivative(double[] vector, String activationFunction) {
        double [] solution = new double[vector.length];
        switch (activationFunction){
            case "sigmoid" -> solution = sigmoidDerivate(vector);
            case "relu" -> solution = reluDerivate(vector);
            case "softmax" -> solution = softmaxDerivate(vector);
        }
        return solution;
    }
    private static double[] sigmoid(double[] vector){
        double [] solution = new double[vector.length];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = 1 / (1 + Math.exp(-vector[i]));
        }
        return solution;
    }
    private static double[] relu(double[] vector){
        double [] solution = new double[vector.length];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = Math.max(0, vector[i]);
        }
        return solution;
    }
    private static double[] softmax(double[] vector) {
        double[] result = new double[vector.length];
        double expSum = 0;
        for (int i = 0; i < vector.length; i++) {
            result[i] = Math.exp(vector[i]);
            expSum += result[i];
        }
        for (int i = 0; i < vector.length; i++) {
            result[i] /= expSum;
        }
        return result;
    }
    private static double[] sigmoidDerivate(double[] vector ){
        double [] solution = new double[vector.length];
        double [] sigmoid = sigmoid(vector);
        for (int i = 0; i < solution.length; i++) {
            solution[i] = sigmoid[i] * (1 - sigmoid[i]);
        }
        return solution;
    }
    private static double[] reluDerivate(double[] vector){
        double [] solution = new double[vector.length];
        for (int i = 0; i < solution.length; i++) {
            if (vector[i] < 0 ){
                solution[i] = 0;
            } else {
                solution[i] = 1;
            }
        }
        return solution;
    }
    private static double [] softmaxDerivate(double[] vector){
        double [] solution = new double[vector.length];
        double [] softmaxVector = softmax(vector);

        for (int i = 0; i < vector.length; i++) {
            double derivate = 0;
            for (int j = 0; j < vector.length; j++) {
                if (i == j){
                    derivate += softmaxVector[i] * (1 - softmaxVector[i]);
                } else {
                    derivate -= softmaxVector[i] * softmaxVector[i];
                }
            }
            solution[i] = derivate;
        }
        return solution;
    }
}