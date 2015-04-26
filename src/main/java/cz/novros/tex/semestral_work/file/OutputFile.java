package cz.novros.tex.semestral_work.file;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by rostislav on 26.4.15.
 */
public class OutputFile {
    Logger logger = Logger.getLogger("InputFile");
    final static Charset ENCODING = StandardCharsets.UTF_8;

    private String fileName = "";
    private Path path;
    private BufferedWriter writer = null;

    public OutputFile(String fileName) throws IOException {
        logger.debug("Openning file in constructor...");
        this.fileName = fileName;
        this.path = Paths.get(this.fileName);
        writer = Files.newBufferedWriter(path, ENCODING);
    }

    public void writeLine(String line) {
        logger.debug("Writing line: " + line);

        try {
            writer.write(line);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            logger.error("Could not write line. " + e);
            e.printStackTrace();
        }
    }
}
