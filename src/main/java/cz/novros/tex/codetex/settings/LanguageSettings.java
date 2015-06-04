package cz.novros.tex.codetex.settings;

/**
 * LICENSE This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details at
 * http://www.gnu.org/copyleft/gpl.html
 **/

import cz.novros.tex.codetex.io.InputFile;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Class which hold language settings for editting state.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-28
 */
public class LanguageSettings {

    private static Logger logger = Logger.getLogger(LanguageSettings.class);

    private String[] keywords;

    private String blockStart;
    private String blockEnd;

    private String commentLine;
    private String commentBegin;
    private String commentEnd;

    private List<String> stringDeclarations = new ArrayList<String>();

    private String classMethodRegex;
    private String classDeclarationRegex;
    private String callingRegex;

    private List<String> delimiters = new ArrayList<String>();

    private Map<String,String> macroMappings = new HashMap<String,String>();

    /**
     * Creates language settings from passed language.
     *
     * @param language Language which will be loaded. It must be prefix of settings files.
     */
    public LanguageSettings(String language) {
        logger.info("Loading language settings of language: " + language + "." );
        InputStream input = null;
        InputFile texMacrosInput = null;

        try {
            logger.debug("Loading language match settings.");
            input = new FileInputStream(Settings.LANGUAGE_SETTINGS_DIR + language + "_match.properties");

            Properties properties = new Properties();
            properties.load(input);

            keywords = properties.getProperty("keywords").split(",");
            commentLine = properties.getProperty("comment.line");
            commentBegin = properties.getProperty("comment.begin");
            commentEnd = properties.getProperty("comment.end");
            classMethodRegex = properties.getProperty("class.method");
            classDeclarationRegex = properties.getProperty("class.declaration");
            callingRegex = properties.getProperty("class.calling");
            blockStart = properties.getProperty("block.start");
            blockEnd = properties.getProperty("block.end");

            String temp = properties.getProperty("delimiters");
            for (int i = 0; i < temp.length(); i++) {
                delimiters.add(String.valueOf(temp.charAt(i)));
            }
            delimiters.add(" ");

            for (String s : properties.getProperty("string").split(",")) {
                stringDeclarations.add(s);
            }

            logger.debug("Loading language highlighting settings.");
            texMacrosInput = new InputFile(Settings.LANGUAGE_SETTINGS_DIR + language + "_highlighting.properties");
            String line;
            while (!texMacrosInput.isEnd()) {
                line = texMacrosInput.readLine();
                if (line != null && line.split("=").length > 1) {
                    macroMappings.put(line.split("=")[0], line.split("=")[1]);
                }
            }
        } catch (IOException e) {
            logger.error("There was problem with loading language settings files! Exception: {}", e);
        } finally {
            try {
                input.close();
                texMacrosInput.close();
            } catch (IOException e) {
                logger.error("Could not close language settings file! Exception: {}", e);
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

    public String getClassMethodRegex() {
        return classMethodRegex;
    }

    public String getClassDeclarationRegex() {
        return classDeclarationRegex;
    }

    public String getCallingRegex() {
        return callingRegex;
    }

    public String getMapping(String key) {
        return macroMappings.get(key);
    }

    public List<String> getDelimiters() {
        return delimiters;
    }

    public List<String> getStringDeclarations() {
        return stringDeclarations;
    }

    public String getBlockStart() {
        return blockStart;
    }

    public String getBlockEnd() {
        return blockEnd;
    }
}
