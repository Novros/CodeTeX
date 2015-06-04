package cz.novros.tex.codetex.processors.indenting;

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
import cz.novros.tex.codetex.settings.Settings;

/**
 * Processor for indenting of code.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-27
 */
public class IndentingProcessor implements IProcessor {

    private final String NEW_LINE_MACRO = "\\codetexNewline";

    private int nestedBrackets = 0;

    /**
     * It will indents code by nested brackets.
     *
     * @param line Line, which will be indented.
     * @param language Language setting.
     * @return Returns indented line.
     */
    @Override
    public String processLine(String line, LanguageSettings language) {
        line = addSpaces(line);
        setNestedBracketsNumber(line, language);
        return line;
    }

    /**
     * Add indenting macro to begin of line.
     *
     * @param line Line which will be indented.
     * @return Line which will be indented..
     */
    private String addSpaces(String line) {
        String spaces = "\\codetexSpace{" + nestedBrackets*Settings.getTabularSpaceCount() + "} ";
        return spaces + line + NEW_LINE_MACRO;
    }

    /**
     * Set actual nesting in all block of code.
     *
     * @param line Line, which will be read for brackets.
     * @param language Language setting.
     */
    private void setNestedBracketsNumber(String line, LanguageSettings language) {
        for(int i = 0; i < line.length(); i++) {
            if(String.valueOf(line.charAt(i)).equals(language.getBlockStart())) {
                nestedBrackets++;
            } else if (String.valueOf(line.charAt(i)).equals(language.getBlockEnd())) {
                nestedBrackets--;
            }
        }
    }
}
