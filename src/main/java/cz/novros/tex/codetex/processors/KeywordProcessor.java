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

/* Processor for keywords in code.
*
* @author Rostislav Novak <rostislav.novak92@gmail.com>
* @version 1.0
* @since 2015-05-29
*/
public class KeywordProcessor implements IProcessor {

    @Override
    public String processLine(String line, LanguageSettings language) {
        for(String keyword : language.getKeywords()) {
            line = highlightKeywords(line,keyword,language);
        }
        return line;
    }

    private String highlightKeywords(String line, String keyword, LanguageSettings language) {
        if(contains(line,keyword,language)) {
            int position = line.indexOf(keyword);
            int positionWithKeyword = position + keyword.length();
            line = line.substring(0, position) + getTextFromMacro( Settings.getMacro(language.getMapping("keywords")), keyword)
                    + highlightKeywords(line.substring(positionWithKeyword, line.length()), keyword, language);
        }
        return line;
    }

    private boolean contains(String line, String text, LanguageSettings language) {
        if(line.contains(text)) {
            int position = line.indexOf(text);
            for( String delimetr : language.getDelimetrs()) {
                if( line.substring(position+text.length(),line.length()).startsWith(delimetr)){
                    return true;
                }
            }
        }
        return false;
    }

    private String getTextFromMacro(String macro, String text) {
        int position = macro.indexOf("#T");
        return macro.substring(0, position) + text + macro.substring(position+2, macro.length());
    }
}
