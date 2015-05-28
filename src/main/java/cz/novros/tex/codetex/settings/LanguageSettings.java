package cz.novros.tex.codetex.settings;

import cz.novros.tex.codetex.file.InputFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by rostislav on 28.5.15.
 */
public class LanguageSettings {

    private String[] keywords;
    private String commentLine;
    private String commentBegin;
    private String commentEnd;
    private String string;
    private String classMethodRegex;
    private String classMethodDeclarationRegex;
    private String callingRegex;

    private Map<String,String> macroMappings = new HashMap<>();

    public LanguageSettings(String language) throws IOException {
        InputStream input = new FileInputStream(Settings.LANGUAGE_SETTINGS_DIR + language + "_match.properties");

        Properties properties = new Properties();
        properties.load(input);

        keywords = properties.getProperty("keywords").split(",");
        commentLine = properties.getProperty("comment.line");
        commentBegin = properties.getProperty("comment.begin");
        commentEnd = properties.getProperty("comment.end");
        string = properties.getProperty("string");
        classMethodRegex = properties.getProperty("class.method");
        classMethodDeclarationRegex = properties.getProperty("class.declaration");
        callingRegex = properties.getProperty("class.calling");

        InputFile texMacrosInput = new InputFile(Settings.LANGUAGE_SETTINGS_DIR + language + "_highlighting.properties");
        String line;
        while (!texMacrosInput.isEnd()) {
            line = texMacrosInput.readLine();
            if(line != null && line.split("=").length > 1) {
                macroMappings.put(line.split("=")[0], line.split("=")[1]);
            }
        }
    }

    public String[] getKeywords() {
        return keywords;
    }

    public String getCommentLine() {
        return commentLine;
    }

    public String getCommentBegin() {
        return commentBegin;
    }

    public String getCommentEnd() {
        return commentEnd;
    }

    public String getString() {
        return string;
    }

    public String getClassMethodRegex() {
        return classMethodRegex;
    }

    public String getClassMethodDeclarationRegex() {
        return classMethodDeclarationRegex;
    }

    public String getCallingRegex() {
        return callingRegex;
    }

    public String getMapping(String key) {
        return macroMappings.get(key);
    }
}
