package cz.novros.tex.semestral_work.file;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by rostislav on 26.4.15.
 */
public class InputFile {
    Logger logger = Logger.getLogger("InputFile");
    final static Charset ENCODING = StandardCharsets.UTF_8;

    private String fileName = "";
    private Path path;
    private BufferedReader reader = null;
    private boolean end = false;

    public InputFile(String fileName) throws IOException {
        logger.debug("Openning file in constructor...");
        this.fileName = fileName;
        this.path = Paths.get(this.fileName);
        reader = Files.newBufferedReader(path, ENCODING);
    }

    public String readLine() {
        logger.debug("Reading line. End=" + end);
        String line = null;

        try {
            if (!end) {
                line = reader.readLine();
            }
        } catch (IOException e) {
            logger.error("Could not read line. " + e);
            e.printStackTrace();
        }

        if(line == null) {
            end = true;
        }

        return line;
    }
}
