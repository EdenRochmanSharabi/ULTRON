package Deepspell.CaesarCipher;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        CaesarCipher encode = new CaesarCipher();
    }
    public CaesarCipher(){
        int shift = 3; // set the shift amount
        String fileName = "src/main/java/Deepspell/trainingSets/trainingData.txt";
        String encodedFileName = "src/main/java/Deepspell/trainingSets/encodedTrainingData.txt";


        // read the contents of the input file
        String inputText = "";
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                inputText += scanner.nextLine();
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        // encode the text
        String encodedText = "";
        for (int i = 0; i < inputText.length(); i++) {
            char c = inputText.charAt(i);
            String binary = Integer.toBinaryString((int) c);
            while (binary.length() < 8) {
                binary = "0" + binary;
            }
            encodedText += binary;
        }

        // write the encoded text to a file
        try {
            FileWriter writer = new FileWriter(encodedFileName);
            writer.write(encodedText);
            writer.close();
            System.out.println("Encoded text written to " + encodedFileName);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
    }
}
