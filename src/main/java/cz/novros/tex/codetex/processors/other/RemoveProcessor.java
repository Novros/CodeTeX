package cz.novros.tex.codetex.processors.other;

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

import cz.novros.tex.codetex.processors.IProcessor;
import cz.novros.tex.codetex.settings.LanguageSettings;

/**
 * Processor, which remove everything in line.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-27
 */
public class RemoveProcessor implements IProcessor {

    /**
     * Remove evrything from line.
     *
     * @param line One line, which will be processed.
     * @param language Language setting.
     * @return Returns removed line. String "".
     */
    @Override
    public String processLine(String line, LanguageSettings language) {
        return "";
    }
}
