package cz.novros.tex.semestral_work;

import cz.novros.tex.semestral_work.automat.Automat;
import cz.novros.tex.semestral_work.file.InputFile;
import cz.novros.tex.semestral_work.file.OutputFile;
import cz.novros.tex.semestral_work.processing.RemoveProcessor;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by rostislav on 26.4.15.
 */
public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String [] args) {

        String arguments = "";
        for(String arg : args) {
            arguments += arg + ",";
        }
        logger.info("Application started with arguments: " + arguments);

        Automat automat = new Automat("test.tex");
        automat.addProcessor(new RemoveProcessor());
        automat.run();

        logger.info("Application completed his job.");
    }
}
