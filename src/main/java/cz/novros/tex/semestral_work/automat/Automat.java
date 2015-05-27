package cz.novros.tex.semestral_work.automat;

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

import cz.novros.tex.semestral_work.file.InputFile;
import cz.novros.tex.semestral_work.file.OutputFile;
import cz.novros.tex.semestral_work.processing.IProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for file processing.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-27
 */
public class Automat {
    public final static String CODE_BLOCK_START = "\\begtt";
    public final static String CODE_BLOCK_END = "\\endtt";
    public final static String FILE_ADDITION_NAME = "Processed";

    private InputFile input;
    private OutputFile output;

    private List<IProcessor> processors = new ArrayList<>();

    private IAutomatState state;

    public Automat(String fileName) {
        state = new AutomatStateOnlyRead();
        try {
            input = new InputFile(fileName);
            String newFileName = fileName.substring(0,fileName.lastIndexOf(".")) + "_" + FILE_ADDITION_NAME + ".tex";
            output = new OutputFile(newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String line;
        while (!input.isEnd()) {
            line = input.readLine();
            line = state.handle(this,line);
            output.writeLine(line);
        }
    }

    public void addProcessor(IProcessor processor) {
        this.processors.add(processor);
    }

    public void deleteProcessor(int index) {
        processors.remove(index);
    }

    public List<IProcessor> getProessors() {
        return processors;
    }

    public IAutomatState getState() {
        return state;
    }

    public void setState(IAutomatState state) {
        this.state = state;
    }
}
