package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import Model.Text;

public class Normalize {

    public static void readAndWrite() {

        BufferedReader br = null;
        try {
            Text text = new Text();
            int countLine = text.countLine();
            int count = 1;
            br = new BufferedReader(new FileReader(new File("Input.txt")));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Output.txt", true)));
            String line;
            
            System.out.println("File Input.txt:");
            while ((line = br.readLine()) != null) {
                if (text.isLineEmpty(line)) {
                    continue;
                }
                System.out.println(line);
                line = text.formatOneSpace(line);
                line = text.formatSpecialCharacters(line);
                line = text.afterDotUpperCase(line);
                line = text.noSpaceQuotes(line);
                line = text.firstUpercase(line);
                line = text.lastAddDot(line);
                pw.print(line);
                if (count < countLine) {
                    pw.print(System.getProperty("line.separator"));
                }
                count++;
            }
            br.close();
            pw.close();
            System.out.println("\nNormalize successful.");

            System.out.println("\nFile Output.txt:");
            BufferedReader outputReader = new BufferedReader(new FileReader(new File("Output.txt")));
            String outputLine;
            while ((outputLine = outputReader.readLine()) != null) {
                System.out.println(outputLine);
            }
            outputReader.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found!!!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}