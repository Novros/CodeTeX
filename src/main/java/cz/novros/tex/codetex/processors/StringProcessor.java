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

/* Processor for strings in code.
*
* @author Rostislav Novak <rostislav.novak92@gmail.com>
* @version 1.0
* @since 2015-05-29
*/
public class StringProcessor implements IProcessor {

    @Override
    public String processLine(String line, LanguageSettings language) {
        for(String keyword : language.getStringDeclarations()) {
            line = highlightString(line, keyword, language);
        }
        return line;
    }

    private String highlightString(String line, String keyword, LanguageSettings language) {
        if(line.contains(keyword)) {
            int position = line.indexOf(keyword);
            int positionOfNext = position + line.substring(position+1,line.length()).indexOf(keyword) + 2;
            line = line.substring(0, position) + getTextFromMacro( Settings.getMacro(language.getMapping("string")), line.substring(position,positionOfNext))
                    + highlightString(line.substring(positionOfNext, line.length()), keyword, language);
        }
        return line;
    }

    private String getTextFromMacro(String macro, String text) {
        int position = macro.indexOf("#T");
        return macro.substring(0, position) + text + macro.substring(position+2, macro.length());
    }
}
