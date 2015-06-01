package cz.novros.tex.codetex.processors.highlighting.state;

import cz.novros.tex.codetex.processors.highlighting.HighlightingProcessor;
import cz.novros.tex.codetex.settings.LanguageSettings;
import cz.novros.tex.codetex.settings.Settings;

/**
 * Created by rostislav on 1.6.15.
 */
public class HighlightingProcessorStateNormal implements IHighlightingProcessorState {

    @Override
    public String handle(HighlightingProcessor processor, String line, LanguageSettings language) {

        if(line.contains(language.getCommentLine())) {
            int position = line.indexOf(language.getCommentLine());
            return highlighting(line.substring(0,position), language)
                    + HighlightingProcessorStateComment.commentLine(line.substring(position,line.length()), language);

        } else if(line.contains(language.getCommentBegin()) && line.contains(language.getCommentEnd())) {
            int positionBegin = line.indexOf(language.getCommentBegin());
            int positionEnd = line.indexOf(language.getCommentEnd());
            return highlighting(line.substring(0,positionBegin),language)
                    + HighlightingProcessorStateComment.commentLine(line.substring(positionBegin,positionBegin),language)
                    + highlighting(line.substring(positionEnd+language.getCommentEnd().length()), language);

        } else if (line.contains(language.getCommentBegin())) {
            int position = line.indexOf(language.getCommentBegin());
            line = highlighting(line.substring(0,position), language)
                    + HighlightingProcessorStateComment.commentLine(line.substring(position,line.length()), language);
            processor.setState(new HighlightingProcessorStateComment());
            return line;

        } else {
            return highlighting(line,language);
        }

    }

    private String highlighting(String line, LanguageSettings language) {
        int position = 0;

        line = line.trim();
        for(int i = 0; i < line.length(); i++) {
            if(isDelimetr(line.charAt(i), language)) {
                line = line.substring(0, position)
                        + processWord(line.substring(position,i), language)
                        + line.substring(i,line.length());
                position = i+1;

            } else if(isString(line.charAt(i),language)) {
                // Find string declaration to find pair.
                for( String compare : language.getStringDeclarations()) {
                    if (String.valueOf(String.valueOf(line.charAt(i))).equals(compare)) {
                        // Find next same string declaration.
                        int positionEnd = line.indexOf(compare,i+1);
                        if( positionEnd != -1) {
                            positionEnd++;
                            // Highligh string
                            String stringPart = processString(line.substring(i, positionEnd), language);
                            line = line.substring(0, i)
                                    + stringPart
                                    + line.substring(positionEnd, line.length());
                            // Edit index
                            i += stringPart.length();
                            break;
                        }
                    }
                }
                position = i+1;
            }
        }

        return line;
    }

    private boolean isDelimetr(char text, LanguageSettings language) {
        return isInCollection(String.valueOf(text),language.getDelimetrs());
    }

    private boolean isString(char text, LanguageSettings language) {
        return isInCollection(String.valueOf(text),language.getStringDeclarations());
    }

    private boolean isInCollection(String text, Iterable<String> collection ) {
        for( String compare : collection) {
            if (String.valueOf(text).equals(compare)) {
                return true;
            }
        }
        return false;
    }

    private String processWord(String word, LanguageSettings language) {
        return processKeyword(word, language);
    }

    private String processKeyword(String word, LanguageSettings language) {
        for (String keyword : language.getKeywords()) {
            if (word.equals(keyword)) {
                return HighlightingProcessor.applyMacro(Settings.getMacro(language.getMapping("keywords")), word);
            }
        }

        return word;
    }

    private String processString(String line, LanguageSettings language) {
        return HighlightingProcessor.applyMacro(Settings.getMacro(language.getMapping("string")), line);
    }
}
