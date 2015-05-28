package cz.novros.tex.codetex.processing;

import cz.novros.tex.codetex.settings.LanguageSettings;

/**
 * Created by rostislav on 27.5.15.
 */
public interface IProcessor {

    public String processLine(String line, LanguageSettings language);
}
