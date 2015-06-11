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

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
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

    private static Logger logger = Logger.getLogger(Settings.class);

    public static String SETTINGS_DIR = getJarLocation() + "settings" + File.separator;
    public static String LANGUAGE_SETTINGS_DIR = SETTINGS_DIR + "language_settings" + File.separator;

    private static String configFile = SETTINGS_DIR + "settings.properties";
    private static String texMacrosFile = SETTINGS_DIR + "tex_macros.properties";
    private static String codetexMacrosFile = SETTINGS_DIR + "codetex_macros";

    private static String codetexMacros;
    private static String codeBlockStart;
    private static String codeBlockEnd;
    private static String texBlockStart;
    private static String texBlockEnd;
    private static String codeBlockInnerStart;
    private static int blockFontSize;
    private static int blockParSize;
    private static int tabularSpaceCount;
    private static Map<String,String> texMacros = new HashMap<String,String>();

    /**
     * Load settings from settings files in settings folder.
     */
    public static void loadSettings() {
        logger.info("Loading application settings.");
        InputStream input = null;
        InputFile texMacrosInput = null;
        InputFile codetexMacrosInput = null;

        try {
            logger.debug("Loading settings.properties file.");
            input = new FileInputStream(configFile);

            Properties properties = new Properties();
            properties.load(input);

            codeBlockStart = properties.getProperty("verbatim.start");
            codeBlockEnd = properties.getProperty("verbatim.end");
            texBlockStart = properties.getProperty("block.start");
            texBlockEnd = properties.getProperty("block.end");
            codeBlockInnerStart = properties.getProperty("block.inner.start");
            tabularSpaceCount = Integer.parseInt(properties.getProperty("space.tabular"));
            blockFontSize = Integer.parseInt(properties.getProperty("font.size"));
            blockParSize = Integer.parseInt(properties.getProperty("font.par.size"));

            logger.debug("Loading tex_macros.properties file.");
            texMacrosInput = new InputFile(texMacrosFile);
            String line;
            while (!texMacrosInput.isEnd()) {
                line = texMacrosInput.readLine();
                if (line != null && line.split("=").length > 1) {
                    texMacros.put(line.split("=")[0], line.split("=")[1]);
                }
            }

            logger.debug("Loading codetex_macros file.");
            codetexMacrosInput = new InputFile(codetexMacrosFile);
            codetexMacros = codetexMacrosInput.readFile();

        } catch (IOException e) {
            logger.error("There was problem with loading application settings! Exception: {}", e);

        } finally {
            try {
                input.close();
                texMacrosInput.close();
                codetexMacrosInput.close();
            } catch (IOException e) {
                logger.error("There was problem with closing files! Exception: {}", e);
            }
        }
    }

    /**
     * Returns location of jar.
     *
     * @return String filled with jar location.
     */
    public static String getJarLocation() {
        logger.debug("Getting jar file location.");
        URL url = Settings.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath = null;
        try {
            jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Could not get jar file location! Exception: {}", e);
        }

        String parentPath = new File(jarPath).getParentFile().getPath(); //Path of the jar
        parentPath = parentPath + File.separator;

        return parentPath;
    }

    public static String getCodeBlockStart() {
        return codeBlockStart;
    }

    public static String getCodeBlockEnd() {
        return codeBlockEnd;
    }

    public static String getMacro(String macro) {
        return texMacros.get(macro);
    }

    public static int getTabularSpaceCount() {
        return tabularSpaceCount;
    }

    public static String getCodetexMacros() {
        return codetexMacros;
    }

    public static int getBlockFontSize() {
        return blockFontSize;
    }

    public static String getTexBlockStart() {
        return texBlockStart;
    }

    public static String getTexBlockEnd() {
        return texBlockEnd;
    }

    public static int getBlockParSize() {
        return blockParSize;
    }

    public static String getCodeBlockInnerStart() {
        return codeBlockInnerStart;
    }
}
