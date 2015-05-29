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

/**
 * Class for writing to system output.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-28
 */
public class OutputSystem implements IOutput {

    /**
     * Write text to output.
     *
     * @param text Text, which will be printed.
     */
    @Override
    public void write(String text) {
        System.out.print(text);
    }

    /**
     * Write text to output with new line.
     *
     * @param text Text, which will be printed.
     */
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
