package cz.novros.tex.semestral_work.file;


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


import org.apache.log4j.Logger;

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

    public void write(String text) {
        logger.debug("Writing: " + text);

        try {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            logger.error("Could not write line. " + e);
            e.printStackTrace();
        }
    }
}
