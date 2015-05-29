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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Class which hold application settings for processors.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-28
 */
public class Settings {
    public static String SETTINGS_DIR = ".." + File.separator + "settings" + File.separator;
    public static String LANGUAGE_SETTINGS_DIR = SETTINGS_DIR + "language_settings" + File.separator;

    private static String configFile = SETTINGS_DIR + "settings.properties";
    private static String texMacrosFile = SETTINGS_DIR + "tex_macros.properties";
    private static String codetexMacrosFile = SETTINGS_DIR + "codetex_macros";

    private static String codetexMacros;
    private static String codeBlockStart;
    private static String codeBlockEnd;
    private static String beginText;
    private static int spaceSize;
    private static int tabularSpaceCount;
    private static Map<String,String> texMacros = new HashMap<>();

    public static void loadSettings() throws IOException {
        InputStream input = new FileInputStream(configFile);

        Properties properties = new Properties();
        properties.load(input);

        codeBlockStart = properties.getProperty("verbatim.start");
        codeBlockEnd = properties.getProperty("verbatim.end");
        beginText = properties.getProperty("begin_text");
        spaceSize = Integer.parseInt(properties.getProperty("space.size"));
        tabularSpaceCount = Integer.parseInt(properties.getProperty("space.tabular"));

        InputFile texMacrosInput = new InputFile(texMacrosFile);
        String line;
        while (!texMacrosInput.isEnd()) {
            line = texMacrosInput.readLine();
            if(line != null && line.split("=").length > 1) {
                texMacros.put(line.split("=")[0], line.split("=")[1]);
            }
        }

        texMacrosInput = new InputFile(codetexMacrosFile);
        codetexMacros = texMacrosInput.readFile();
    }

    public static String getCodeBlockStart() {
        return codeBlockStart;
    }

    public static String getCodeBlockEnd() {
        return codeBlockEnd;
    }

    public static String getBeginText() {
        return beginText;
    }

    public static String getMacro(String macro) {
        return texMacros.get(macro);
    }

    public static int getTabularSpaceCount() {
        return tabularSpaceCount;
    }

    public static int getSpaceSize() {
        return spaceSize;
    }

    public static String getCodetexMacros() {
        return codetexMacros;
    }
}
