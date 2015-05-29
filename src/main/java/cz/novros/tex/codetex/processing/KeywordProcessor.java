package cz.novros.tex.codetex.processing;

import cz.novros.tex.codetex.settings.LanguageSettings;
import cz.novros.tex.codetex.settings.Settings;

/**
 * Created by rostislav on 29.5.15.
 */
public class KeywordProcessor implements IProcessor {

    @Override
    public String processLine(String line, LanguageSettings language) {
        for(String keyword : language.getKeywords()) {
            if(line.contains(keyword)) {
                int index = line.indexOf(keyword);
                line = line.substring(0,index) + getTextFromMacro( Settings.getMacro(language.getMapping("keywords")), keyword) + line.substring(index + keyword.length(), line.length());

            }
        }
        return line;
    }

    public String getTextFromMacro(String macro, String text) {
        int position = macro.indexOf("#T");
        return macro.substring(0,position) + text + macro.substring(position+2, macro.length());
    }
}
