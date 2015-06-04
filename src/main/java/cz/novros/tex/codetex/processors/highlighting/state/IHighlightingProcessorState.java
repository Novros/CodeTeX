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

/**
 * Interface for state of highlighting processor.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-29
 */
public interface IHighlightingProcessorState {

    /**
     * It will handle one line of code.
     *
     * @param processor Processor of which i am state.
     * @param line Line of text, which will be handled.
     * @param language Language settings.
     * @return Returns processed line of text.
     */
    public String handle(HighlightingProcessor processor, String line, LanguageSettings language);
}
