package cz.novros.tex.codetex.automaton;

import cz.novros.tex.codetex.processing.IProcessor;
import cz.novros.tex.codetex.settings.LanguageSettings;
import cz.novros.tex.codetex.settings.Settings;

import java.io.IOException;

/**
 * Created by rostislav on 27.5.15.
 */
public class AutomatStateProcess implements IAutomatState {

    private LanguageSettings language;

    public AutomatStateProcess(String language) {
        try {
            this.language = new LanguageSettings(language);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String handle(Automaton automaton, String line) {

        if(line.startsWith(Settings.getCodeBlockEnd())) {
            automaton.setState(new AutomatStateOnlyRead());
            return "";
        }

        for (IProcessor processor : automaton.getProessors()) {
            line = processor.processLine(line, language);
        }
        return line + "\n";
    }
}
