package cz.novros.tex.codetex.io;

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
 * Output class for writing to io.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-04-26
 */
public class OutputFile implements IOutput {

    private Logger logger = Logger.getLogger("InputFile");
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private String fileName = "";
    private Path path;
    private BufferedWriter writer = null;

    public OutputFile(String fileName) throws IOException {
        logger.debug("Openning file in constructor...");
        this.fileName = fileName;
        this.path = Paths.get(this.fileName);
        writer = Files.newBufferedWriter(path, ENCODING);
    }

    /**
     * Write text to output with new line.
     *
     * @param text Text, which will be printed.
     */
    public void writeLine(String text) {
        logger.debug("Writing line: " + text);

        try {
            writer.write(text);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            logger.error("Could not write line. " + e);
            e.printStackTrace();
        }
    }

    /**
     * Write text to output.
     *
     * @param text Text, which will be printed.
     */
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
