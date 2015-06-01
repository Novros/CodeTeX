package cz.novros.tex.codetex.processors.highlighting.state;

import cz.novros.tex.codetex.processors.highlighting.HighlightingProcessor;
import cz.novros.tex.codetex.settings.LanguageSettings;
import cz.novros.tex.codetex.settings.Settings;

/**
 * Created by rostislav on 1.6.15.
 */
public class HighlightingProcessorStateComment implements IHighlightingProcessorState {

    @Override
    public String handle(HighlightingProcessor processor, String line, LanguageSettings language) {
        int position = 0;

        if (line.contains(language.getCommentEnd())) {
            position = line.indexOf(language.getCommentEnd());
            int positionWithComment = position+language.getCommentEnd().length();
            line = HighlightingProcessor.applyMacro(Settings.getMacro(language.getMapping("comment")), line.substring(0, positionWithComment))
                    + line.substring(positionWithComment, line.length());

            processor.setState(new HighlightingProcessorStateNormal());

        } else {
            line = HighlightingProcessor.applyMacro(Settings.getMacro(language.getMapping("comment")), line);

        }

        return line;
    }

    public static String commentLine(String line, LanguageSettings language) {
        return HighlightingProcessor.applyMacro(Settings.getMacro(language.getMapping("comment")), line);
    }
}
