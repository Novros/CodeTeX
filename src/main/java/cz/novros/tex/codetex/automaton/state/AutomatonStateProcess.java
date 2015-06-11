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
import cz.novros.tex.codetex.processors.IProcessor;
import cz.novros.tex.codetex.settings.LanguageSettings;
import cz.novros.tex.codetex.settings.Settings;

import java.io.IOException;

/**
 * Automaton in state of processors lines.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-27
 */
public class AutomatonStateProcess implements IAutomatonState {

    private LanguageSettings language;

    public AutomatonStateProcess(String language) {
        this.language = new LanguageSettings(language);
    }

    /**
     * Handle line from automaton. Process line with all processor in automaton.
     *
     * @param automaton Automaton, which i am state.
     * @param line Line, which will be handled.
     * @return Return handled (processed) line.
     */
    @Override
    public String handle(Automaton automaton, String line) {

        if(line.startsWith(Settings.getCodeBlockEnd())) {
            automaton.setState(new AutomatonStateOnlyRead());
            return "\\endgroup\n" + Settings.getTexBlockEnd() + "\n";
        }

        for (IProcessor processor : automaton.getProessors()) {
            line = processor.processLine(line, language);
        }

        return line + "\n";
    }
}
