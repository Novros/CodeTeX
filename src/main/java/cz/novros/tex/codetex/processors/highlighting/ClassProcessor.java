package cz.novros.tex.codetex.processors.highlighting;

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

import cz.novros.tex.codetex.processors.IProcessor;
import cz.novros.tex.codetex.settings.LanguageSettings;

/**
 * Created by rostislav on 29.5.15.
 */
public class ClassProcessor implements IProcessor {
    @Override
    public String processLine(String line, LanguageSettings language) {
        //line = line.replaceAll(language.getClassDeclarationRegex(), "class ");
        /*String[] temp = line.split(language.getClassDeclarationRegex());
        for(String s : temp) {
            System.out.println(s);
        }*/
        return line;
    }
}
