package cz.novros.tex.codetex.processors.highlighting;

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
import cz.novros.tex.codetex.processors.highlighting.state.HighlightingProcessorStateNormal;
import cz.novros.tex.codetex.processors.highlighting.state.IHighlightingProcessorState;
import cz.novros.tex.codetex.settings.LanguageSettings;

/**
 * Processor for highlighting of code.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-29
 */
public class HighlightingProcessor implements IProcessor {

    private IHighlightingProcessorState state;

    public HighlightingProcessor() {
        state = new HighlightingProcessorStateNormal();
    }

    /**
     * It will trim and highlight line of code.
     *
     * @param line Line of code, which will be highlighted.
     * @param language Language setting.
     * @return Returns highlighted code.
     */
    @Override
    public String processLine(String line, LanguageSettings language) {

        line = line.trim();
        line = state.handle(this, line, language);

        return line;
    }

    public void setState(IHighlightingProcessorState state) {
        this.state = state;
    }

    /**
     * Apply macro on text. It will look for #T in macro text.
     *
     * @param macro Macro which will be applied on text.
     * @param text Text which will be in macro.
     * @return Returns text with applied macro.
     */
    public static String applyMacro(String macro, String text) {
        int position = macro.indexOf("#T");
        return macro.substring(0,position) + text + macro.substring(position+2, macro.length());
    }
}
