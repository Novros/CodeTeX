package cz.novros.tex.codetex.processing;

import cz.novros.tex.codetex.settings.LanguageSettings;
import cz.novros.tex.codetex.settings.Settings;

/**
 * Created by rostislav on 29.5.15.
 */
public class TabularProcessor implements IProcessor {

    private String newlineMacro = "\\codetexNewline";

    private int nestedBrackets = 0;

    @Override
    public String processLine(String line, LanguageSettings language) {
        line = line.trim();
        line = addSpaces(line);
        return line;
    }


    private String addSpaces(String line) {
        String spaces = "\\codetexSpace{" + nestedBrackets*Settings.getTabularSpaceCount() + "} ";

        if(line.contains("{")) {
            nestedBrackets++;
        }
        if (line.contains("}")) {
            nestedBrackets--;
        }

        return spaces + line + newlineMacro;
    }
}
