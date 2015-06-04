package cz.novros.tex.codetex.processors.highlighting.state;

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

import cz.novros.tex.codetex.processors.highlighting.HighlightingProcessor;
import cz.novros.tex.codetex.settings.LanguageSettings;
import cz.novros.tex.codetex.settings.Settings;

/**
 * State of highlighting comments.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-06-01
 */
public class HighlightingProcessorStateComment implements IHighlightingProcessorState {

    /**
     * It will handle one line of code.
     *
     * @param processor Processor of which i am state.
     * @param line Line of text, which will be handled.
     * @param language Language settings.
     * @return Returns processed line of text.
     */
    @Override
    public String handle(HighlightingProcessor processor, String line, LanguageSettings language) {
        int position = 0;

        if (line.contains(language.getCommentEnd())) {
            position = line.indexOf(language.getCommentEnd());
            int positionWithComment = position+language.getCommentEnd().length();
            line = HighlightingProcessor.applyMacro(Settings.getMacro(language.getMapping("comment")), line.substring(0, positionWithComment))
                    + line.substring(positionWithComment, line.length());

            processor.setState(new HighlightingProcessorStateNormal());

        } else {
            line = HighlightingProcessor.applyMacro(Settings.getMacro(language.getMapping("comment")), line);

        }

        return line;
    }

    /**
     * Highlight text with comment macro.
     *
     * @param text Text, which will be highlighted with comment macro.
     * @param language Language settings.
     * @return Returns highlighted text.
     */
    public static String commentLine(String text, LanguageSettings language) {
        return HighlightingProcessor.applyMacro(Settings.getMacro(language.getMapping("comment")), text);
    }
}
