package cz.novros.tex.codetex.processing;

import cz.novros.tex.codetex.settings.LanguageSettings;

/**
 * Created by rostislav on 29.5.15.
 */
public class CommentProcessor implements IProcessor {

    @Override
    public String processLine(String line, LanguageSettings language) {
        return line;
    }
}
