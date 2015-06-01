package cz.novros.tex.codetex.processors.highlighting.state;

import cz.novros.tex.codetex.processors.highlighting.HighlightingProcessor;
import cz.novros.tex.codetex.settings.LanguageSettings;

/**
 * Created by rostislav on 1.6.15.
 */
public interface IHighlightingProcessorState {

    public String handle(HighlightingProcessor processor, String line, LanguageSettings language);
}
