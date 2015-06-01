package cz.novros.tex.codetex.processors.indent;

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

import cz.novros.tex.codetex.other.Pair;
import cz.novros.tex.codetex.processors.IProcessor;
import cz.novros.tex.codetex.settings.LanguageSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Processor for escaping TeX reserved chars.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-29
 */
public class EscapingProcessor implements IProcessor {

    private List<Pair<String,String>> specialCharactersMapping = new ArrayList<>();

    public EscapingProcessor() {
        specialCharactersMapping.add(new Pair<>("\\", "$\\backslash$"));
        specialCharactersMapping.add(new Pair<>("$", "\\$"));
        specialCharactersMapping.add(new Pair<>("#", "\\#"));
        specialCharactersMapping.add(new Pair<>("%", "\\%"));
        specialCharactersMapping.add(new Pair<>("&", "\\&"));
        specialCharactersMapping.add(new Pair<>("^", "\\^"));
        specialCharactersMapping.add(new Pair<>("_", "\\_"));
        specialCharactersMapping.add(new Pair<>("{", "$\\{$"));
        specialCharactersMapping.add(new Pair<>("}", "$\\}$"));
        specialCharactersMapping.add(new Pair<>("~", "\\~"));
        specialCharactersMapping.add(new Pair<>("<", "$<$"));
        specialCharactersMapping.add(new Pair<>(">","$>$"));
    }


    @Override
    public String processLine(String line, LanguageSettings language) {
        for (Pair character : specialCharactersMapping) {
            line = escapeSpecialCharacters(line, character);
        }
        return line;
    }

    private String escapeSpecialCharacters(String line, Pair character) {
        if(line.contains((String)character.getA())) {
            int position = line.indexOf((String)character.getA());
            // Exception for backslash
            if(line.substring(position,line.length()).startsWith("$\\backslash$")) {
                line = line.substring(0, position+13) + escapeSpecialCharacters(line.substring(position + 13, line.length()), character);
            } else {
                line = line.substring(0, position) + character.getB()
                        + escapeSpecialCharacters(line.substring(position + 1, line.length()), character);
            }
        }
        return line;
    }

}