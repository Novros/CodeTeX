package cz.novros.tex.codetex.processors.highlighting.state;

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

import cz.novros.tex.codetex.processors.highlighting.HighlightingProcessor;
import cz.novros.tex.codetex.settings.LanguageSettings;
import cz.novros.tex.codetex.settings.Settings;

import java.util.List;

/**
 * State of highlighting line of code..
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-06-01
 */
public class HighlightingProcessorStateNormal implements IHighlightingProcessorState {

    /**
     * It will handle one line of code.
     *
     * @param processor Processor of which i am state.
     * @param line Line of text, which will be handled.
     * @param language Language settings.
     * @return Returns processed line of text.
     */
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

    /**
     * Highlight line of text by language settings.
     *
     * @param line Text which will be highlighted.
     * @param language Language settings.
     * @return Returns highlighted line of text.
     */
    private String highlighting(String line, LanguageSettings language) {
        int position = 0;

        line = line.trim();
        for(int i = 0; i < line.length(); i++) {
            if(isDelimiter(line.charAt(i), language)) {
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

    /**
     * Returns if char is delimiter of language settings or not.
     *
     * @param text Char which will be tested.
     * @param language Language settings.
     * @return Returns True if char is delimiter, otherwise false.
     */
    private boolean isDelimiter(char text, LanguageSettings language) {
        return isInCollection(String.valueOf(text),language.getDelimiters());
    }

    /**
     * Returns if char is string declaration in language.
     *
     * @param text Char which will be tested.
     * @param language Language settings.
     * @return Returns True if char is string declaration, otherwise false.
     */
    private boolean isString(char text, LanguageSettings language) {
        return isInCollection(String.valueOf(text),language.getStringDeclarations());
    }

    /**
     * Tests if text is in collection.
     *
     * @param text Text which will be checked.
     * @param collection
     * @return Returns true if
     */
    private boolean isInCollection(String text, List<String> collection) {
        return collection.contains(text);
        /*for( String compare : collection) {
            if (String.valueOf(text).equals(compare)) {
                return true;
            }
        }
        return false;*/
    }

    /**
     * Process word for highlighting.
     *
     * @param word Word, which will be processed.
     * @param language Language settings.
     * @return Returns processed word.
     */
    private String processWord(String word, LanguageSettings language) {
        return processKeyword(word, language);
    }

    /**
     * Highlight keyword if word is.
     *
     * @param word Word which can be highlighted.
     * @param language Language settings.
     * @return Returns processed word.
     */
    private String processKeyword(String word, LanguageSettings language) {
        for (String keyword : language.getKeywords()) {
            if (word.equals(keyword)) {
                return HighlightingProcessor.applyMacro(Settings.getMacro(language.getMapping("keywords")), word);
            }
        }

        return word;
    }

    /**
     * Highlight line of text as string.
     *
     * @param line Line of text, which will be highlighted.
     * @param language Language settings.
     * @return  Returns processed line.
     */
    private String processString(String line, LanguageSettings language) {
        return HighlightingProcessor.applyMacro(Settings.getMacro(language.getMapping("string")), line);
    }
}
