package cz.novros.tex.codetex.processing;

import cz.novros.tex.codetex.settings.LanguageSettings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rostislav on 29.5.15.
 */
public class VerbatimProcessor implements IProcessor {

    private Map<String,String> specialCharactersMapping = new HashMap<>();

    public VerbatimProcessor() {
        specialCharactersMapping.put("#","\\#");
        specialCharactersMapping.put("$","\\$");
        specialCharactersMapping.put("%","\\%");
        specialCharactersMapping.put("&","\\&");
        specialCharactersMapping.put("\\","\\textbackslash");
        specialCharactersMapping.put("^","\\^");
        specialCharactersMapping.put("_","\\_");
        specialCharactersMapping.put("{","$\\{$");
        specialCharactersMapping.put("}","$\\}$");
        specialCharactersMapping.put("~","\\~");
        specialCharactersMapping.put("<","\\textless");
        specialCharactersMapping.put(">","\\textgreater");
    }


    @Override
    public String processLine(String line, LanguageSettings language) {
        int position = 0;
        for (String character : specialCharactersMapping.keySet()) {
            line = escapeSpecialCharacters(line, character);
        }
        return line;
    }

    public String escapeSpecialCharacters(String line, String character) {
        if(line.contains(character)) {
            int position = line.indexOf(character);
            line = line.substring(0,position) + specialCharactersMapping.get(character)
                    + escapeSpecialCharacters(line.substring(position+1, line.length()), character);
        }
        return line;
    }
}