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

/* Processor for comments in code.
*
* @author Rostislav Novak <rostislav.novak92@gmail.com>
* @version 1.0
* @since 2015-05-29
*/
public class CommentProcessor implements IProcessor {

    private boolean inComment = false;

    @Override
    public String processLine(String line, LanguageSettings language) {
        int position = 0;

        if (line.contains(language.getCommentEnd())) {
            position = line.indexOf(language.getCommentEnd());
            int positionWithComment = position+language.getCommentEnd().length();
            line = getTextFromMacro(Settings.getMacro(language.getMapping("comment")), line.substring(0,positionWithComment))
                    + line.substring(positionWithComment, line.length());
            inComment = false;

        } else if (inComment) {
            line = getTextFromMacro(Settings.getMacro(language.getMapping("comment")), line);

        } else if (line.contains(language.getCommentLine())) {
            position = line.indexOf(language.getCommentLine());
            line = line.substring(0,position) + getTextFromMacro(Settings.getMacro(language.getMapping("comment")), line.substring(position, line.length()));

        } else if (line.contains(language.getCommentBegin()) && line.contains(language.getCommentEnd())) {
            position = line.indexOf(language.getCommentBegin());
            int position2 = line.indexOf(language.getCommentEnd());
            int position2WithComment = position2+language.getCommentEnd().length();
            line = line.substring(0,position) +
                    getTextFromMacro(Settings.getMacro(language.getMapping("comment")), line.substring(position, position2WithComment)) +
                    line.substring(position2WithComment, line.length());
        } else if(line.contains(language.getCommentBegin())) {
            inComment = true;
            position = line.indexOf(language.getCommentBegin());
            line = line.substring(0,position) + getTextFromMacro(Settings.getMacro(language.getMapping("comment")), line.substring(position, line.length()));

        }
        return line;
    }

    public String getTextFromMacro(String macro, String text) {
        int position = macro.indexOf("#T");
        return macro.substring(0,position) + text + macro.substring(position+2, macro.length());
    }
}
