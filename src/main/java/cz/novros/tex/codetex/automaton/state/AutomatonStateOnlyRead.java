package cz.novros.tex.codetex.automaton.state;

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

import cz.novros.tex.codetex.automaton.Automaton;
import cz.novros.tex.codetex.settings.Settings;

/**
 * Automaton in state of reading.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-27
 */
public class AutomatonStateOnlyRead implements IAutomatonState {

    private String languageRegex = "\\codetexLangauge( )*\\{.*\\}";

    /**
     * Handle line from automaton. Only check if line contains start block code.
     *
     * @param automaton Automaton, which i am state.
     * @param line Line, which will be handled.
     * @return Return handled (processed) line.
     */
    @Override
    public String handle(Automaton automaton, String line) {

        if(line.endsWith(Settings.getCodeBlockStart())) {
            automaton.setState(new AutomatonStateProcess(getLanguage(line)));
            return Settings.getTexBlockStart() + "\n\\begingroup\\typoscale["
                + Settings.getBlockFontSize() + "0/"
                + Settings.getBlockParSize() + "0]\n"
                + Settings.getCodeBlockInnerStart() + "\n";
        }

        return line + "\n";
    }

    private String getLanguage(String line) {
        String splitString = (line.split(languageRegex))[0];
        return splitString.substring(splitString.indexOf("{")+1,splitString.indexOf("}"));
    }
}
