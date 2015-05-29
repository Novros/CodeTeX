package cz.novros.tex.codetex.settings;

import cz.novros.tex.codetex.file.InputFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by rostislav on 28.5.15.
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
