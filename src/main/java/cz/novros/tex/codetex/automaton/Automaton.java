package cz.novros.tex.codetex.automaton;

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

import cz.novros.tex.codetex.file.IOutput;
import cz.novros.tex.codetex.file.InputFile;
import cz.novros.tex.codetex.processing.*;
import cz.novros.tex.codetex.settings.Settings;

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
public class Automaton {
    private InputFile input;
    private IOutput output;

    private List<IProcessor> processors = new ArrayList<>();

    private IAutomatState state;

    public Automaton(String fileName) {
        state = new AutomatStateOnlyRead();
        try {
            input = new InputFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        processors.add(new VerbatimProcessor());
        processors.add(new TabularProcessor());
        processors.add(new CommentProcessor());
        //processors.add(new KeywordProcessor());
    }

    public void run() {
        String line;

        output.writeLine(Settings.getBeginText());
        output.write(Settings.getCodetexMacros());

        while (!input.isEnd()) {
            line = input.readLine();
            line = state.handle(this,line);
            output.write(line);
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

    public void setOutput(IOutput output) {
        this.output = output;
    }
}
