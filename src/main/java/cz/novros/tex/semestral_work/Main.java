package cz.novros.tex.semestral_work;

import cz.novros.tex.semestral_work.file.InputFile;
import cz.novros.tex.semestral_work.file.OutputFile;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by rostislav on 26.4.15.
 */
public class Main {

    private static Logger logger = Logger.getLogger("InputFile");

    public static void main(String [] args) {

        String arguments = "";
        for(String arg : args) {
            arguments += arg + ",";
        }
        logger.info("Application started with arguments: " + arguments);

        try {
            OutputFile outputFile = new OutputFile("TestFile.txt");
            outputFile.writeLine("Ahoj.");
            outputFile.writeLine("Právě zapisuji do souboru v klidu.");

            InputFile inputFile = new InputFile("TestFile.txt");
            System.out.println(inputFile.readLine());
            System.out.println(inputFile.readLine());

        } catch (IOException e) {
            logger.error("Something went wrong. " + e);
            e.printStackTrace();
        }

    }
}
