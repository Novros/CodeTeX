package cz.novros.tex.codetex.automaton;

import cz.novros.tex.codetex.settings.Settings;

/**
 * Created by rostislav on 27.5.15.
 */
public class AutomatStateOnlyRead implements IAutomatState {

    @Override
    public String handle(Automaton automaton, String line) {
        if(line.startsWith(Settings.getCodeBlockStart())) {
            automaton.setState(new AutomatStateProcess(line.split(" ")[1]));
            return "";
        }
        return line + "\n";
    }
}
