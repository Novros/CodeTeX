package cz.novros.tex.codetex;

/**
 * LICENSE This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details at
 * http://www.gnu.org/copyleft/gpl.html
 **/

import cz.novros.tex.codetex.automaton.Automaton;
import cz.novros.tex.codetex.file.OutputFile;
import cz.novros.tex.codetex.file.OutputSystem;
import cz.novros.tex.codetex.settings.Settings;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Main class of this program. Take command line arguments and create automaton.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-04-26
 */
public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    private static String fileName = null;
    private static String outputFileName = null;

    private static Automaton automaton;

    public static void main(String [] args) {
        logger.info("Application has started.");

        String arguments = "";
        for(String arg : args) {
            arguments += arg + ",";
        }
        logger.debug("Arguments: " + arguments);

        processCommandLineArguments(args);
        try {
            Settings.loadSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        createAutomaton();
        automaton.run();

        logger.info("Application completed his job.");
    }

    /**
     * Process command line arguments and set class variables.
     *
     * @param arguments Arguments from command line.
     */
    private static void processCommandLineArguments(String [] arguments) {
        // Bad arguments
        if(arguments.length < 1 || arguments.length == 2 || arguments.length > 3) {
            printUsage();
            System.exit(1);
        }

        for(int i = 0; i < arguments.length; i++) {
            if(arguments[i].equals("-o")) {
                outputFileName = arguments[++i];
            }
        }
        fileName = arguments[arguments.length-1];
        logger.debug("File input: " + fileName);
        logger.debug("File output: " + outputFileName);
    }

    /**
     * Creates automaton.
     */
    private static void createAutomaton() {
        automaton = new Automaton(fileName);

        if(outputFileName != null) {
            try {
                automaton.setOutput(new OutputFile(outputFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            automaton.setOutput(new OutputSystem());
        }
    }

    /**
     * Print help information about how to start app.
     */
    private static void printUsage() {
        System.out.println("Bad start of application CodeTeX.");
        System.out.println("codetex [-o outputFile] inputFile");
    }
}
