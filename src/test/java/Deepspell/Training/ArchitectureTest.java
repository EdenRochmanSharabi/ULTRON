package Deepspell.Training;

import jdk.internal.vm.annotation.IntrinsicCandidate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains JUnit tests for the Architecture class.
 */
class ArchitectureTest {
    /**
     * Tests the dot product of two matrices.
     */
    @Test
    void testDotProduct() {
        double[][] matrix1 = {{1, 2, 3}, {4, 5, 6}};
        double[][] matrix2 = {{7, 8}, {9, 10}, {11, 12}};
        double[][] expected = {{58, 64}, {139, 154}};
        assertArrayEquals(expected, Architecture.dotProduct(matrix1, matrix2));
    }
    /**
     * Tests the addition of two vectors.
     */
    @Test
    void testAddVectors() {
        double[] vector1 = {1, 2, 3};
        double[] vector2 = {4, 5, 6};
        double[] expected = {5, 7, 9};
        assertArrayEquals(expected, Architecture.addVectors(vector1, vector2));
    }
    /**
     * Tests the addition of two matrices.
     */
    @Test
    void testAddMatrices() {
        double[][] matrix1 = {{1, 2}, {3, 4}};
        double[][] matrix2 = {{5, 6}, {7, 8}};
        double[][] expected = {{6, 8}, {10, 12}};
        assertArrayEquals(expected, Architecture.addMatrices(matrix1, matrix2));
    }
    /**
     * Tests the subtraction of two vectors.
     */
    @Test
    void testSubtractVectors() {
        double[] vector1 = {1, 2, 3};
        double[] vector2 = {4, 5, 6};
        double[] expected = {-3, -3, -3};
        assertArrayEquals(expected, Architecture.subtractVectors(vector1, vector2));
    }
    /**
     * Tests the subtraction of two matrices.
     */
    @Test
    void testSubtractMatrices() {
        double[][] matrix1 = {{1, 2}, {3, 4}};
        double[][] matrix2 = {{5, 6}, {7, 8}};
        double[][] expected = {{-4, -4}, {-4, -4}};
        assertArrayEquals(expected, Architecture.subtractMatrices(matrix1, matrix2));
    }
    /**
     * Tests the multiplication of a vector and a scalar.
     */
    @Test
    void testMultiplyVectorScalar() {
        double[] vector = {1, 2, 3};
        double scalar = 2;
        double[] expected = {2, 4, 6};
        assertArrayEquals(expected, Architecture.multiplyVectorScalar(vector, scalar));
    }

    @Test
    void testMultiplyMatrixScalar() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double scalar = 2;
        double[][] expected = {{2, 4}, {6, 8}};
        assertArrayEquals(expected, Architecture.multiplyMatrixScalar(matrix, scalar));
    }

    @Test
    void testMultiplyVectors() {
        double[] vector1 = {1, 2, 3};
        double[] vector2 = {4, 5, 6};
        double[] expected = {4, 10, 18};
        assertArrayEquals(expected, Architecture.multiplyVectors(vector1, vector2));
    }
    /**
     * Tests the matrix multiplication of two matrices.
     */

    @Test
    void testMultiplyMatrices() {
        double[][] matrix1 = {{1, 2}, {3, 4}};
        double[][] matrix2 = {{5, 6}, {7, 8}};
        double[][] expected = {{5, 12}, {21, 32}};
        assertArrayEquals(expected, Architecture.multiplyMatrices(matrix1, matrix2));
    }

    /**
     * Tests the transpose of a matrix.
     */
    @Test
    void testTranspose() {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] expected = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        double[][] result = Architecture.transpose(matrix);
        assertArrayEquals(expected, result);
    }

    /**
     * Tests the application of an activation function to a vector.
     */

    @Test
    void testApplyActivation() {
        double[] input = {0.5, -1.0, 2.0};

        // Test sigmoid activation function
        double[] expectedSigmoid = {0.62245933120185456463890056574550847875327936530891, 0.26894142136999512074884075817816372563485535983494, 0.88079707797788244405972914130239679520638429862897};
        double[] outputSigmoid = Architecture.applyActivation(input, "sigmoid");
        assertArrayEquals(expectedSigmoid, outputSigmoid, 1e-6);

        // Test ReLU activation function
        double[] expectedReLU = {0.5, 0.0, 2.0};
        double[] outputReLU = Architecture.applyActivation(input, "relu");
        assertArrayEquals(expectedReLU, outputReLU, 1e-6);

        // Test softmax activation function
        double[] expectedSoftmax = {0.17529039214003668995456328633609944148103165885952, 0.039112573270687451954425396310806640843561739427394, 0.78559703458927585809101131735309391767540660171309};
        double[] outputSoftmax = Architecture.applyActivation(input, "softmax");
        assertArrayEquals(expectedSoftmax, outputSoftmax, 1e-6);
    }
    /**
     * expectedSoftmaxDerivative has not actually being tested. I found
     * no only caclculator for it
     */
    @Test
    void applyActivationDerivative() {
        double[] vector = {1.0, -2.0, 3.0};
        double[] expectedSigmoidDerivative = {0.1966, 0.1049, 0.0452};
        double[] expectedReluDerivative = {1.0, 0.0, 1.0};
        double[] expectedSoftmaxDerivative = {0.07637315016025975, 0.005795329237488524, -1.4244286111614117};
        double delta = 0.0001;

        double[] sigmoidDerivative = Architecture.applyActivationDerivative(vector, "sigmoid");
        double[] reluDerivative = Architecture.applyActivationDerivative(vector, "relu");
        double[] softmaxDerivative = Architecture.applyActivationDerivative(vector, "softmax");

        for (int i = 0; i < vector.length; i++) {
            assertEquals(expectedSigmoidDerivative[i], sigmoidDerivative[i], delta);
            assertEquals(expectedReluDerivative[i], reluDerivative[i], delta);
            assertEquals(expectedSoftmaxDerivative[i], softmaxDerivative[i], delta);
        }
    }
}