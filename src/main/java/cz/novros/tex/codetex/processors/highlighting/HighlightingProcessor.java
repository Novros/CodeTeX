package cz.novros.tex.codetex.processors.highlighting;

import cz.novros.tex.codetex.processors.IProcessor;
import cz.novros.tex.codetex.processors.highlighting.state.HighlightingProcessorStateNormal;
import cz.novros.tex.codetex.processors.highlighting.state.IHighlightingProcessorState;
import cz.novros.tex.codetex.settings.LanguageSettings;

/**
 * Created by rostislav on 1.6.15.
 */
public class HighlightingProcessor implements IProcessor {

    private IHighlightingProcessorState state;

    public HighlightingProcessor() {
        state = new HighlightingProcessorStateNormal();
    }

    @Override
    public String processLine(String line, LanguageSettings language) {

        line = line.trim();
        line = state.handle(this, line, language);

        return line;
    }

    public void setState(IHighlightingProcessorState state) {
        this.state = state;
    }

    public static String applyMacro(String macro, String text) {
        int position = macro.indexOf("#T");
        return macro.substring(0,position) + text + macro.substring(position+2, macro.length());
    }
}
