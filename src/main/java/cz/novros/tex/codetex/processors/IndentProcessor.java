package cz.novros.tex.codetex.processors;

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

import cz.novros.tex.codetex.settings.LanguageSettings;
import cz.novros.tex.codetex.settings.Settings;

/**
 * Processor for indent of code.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-27
 */
public class IndentProcessor implements IProcessor {

    private final String NEW_LINE_MACRO = "\\codetexNewline";

    private int nestedBrackets = 0;

    @Override
    public String processLine(String line, LanguageSettings language) {
        line = line.trim();
        line = addSpaces(line);
        return line;
    }


    private String addSpaces(String line) {
        String spaces = "\\codetexSpace{" + nestedBrackets*Settings.getTabularSpaceCount() + "} ";

        if(line.contains("{")) {
            nestedBrackets++;
        }
        if (line.contains("}")) {
            nestedBrackets--;
        }

        return spaces + line + NEW_LINE_MACRO;
    }
}
