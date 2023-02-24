package Deepspell.CaesarCipher;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BinaryDecoder {
    public static void main(String[] args) {
        BinaryDecoder decode = new BinaryDecoder();
    }

    public BinaryDecoder() {
        String fileName = "src/main/java/Deepspell/trainingSets/encodedMiniTest.txt";
        String decodedFileName = "src/main/java/Deepspell/trainingSets/decodedMiniTest.txt";

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

        // decode the text
        String decodedText = "";
        for (int i = 0; i < inputText.length(); i += 8) {
            String byteStr = inputText.substring(i, i + 8);
            int byteVal = Integer.parseInt(byteStr, 2);
            decodedText += (char) byteVal;
        }

        // write the decoded text to a file
        try {
            FileWriter writer = new FileWriter(decodedFileName);
            writer.write(decodedText);
            writer.close();
            System.out.println("Decoded text written to " + decodedFileName);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
    }
}
