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

import cz.novros.tex.codetex.automaton.state.AutomatonStateOnlyRead;
import cz.novros.tex.codetex.automaton.state.IAutomatonState;
import cz.novros.tex.codetex.io.IOutput;
import cz.novros.tex.codetex.io.InputFile;
import cz.novros.tex.codetex.processors.*;
import cz.novros.tex.codetex.processors.highlighting.*;
import cz.novros.tex.codetex.processors.indenting.EscapingProcessor;
import cz.novros.tex.codetex.processors.indenting.IndentingProcessor;
import cz.novros.tex.codetex.settings.Settings;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Automaton of this program.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-27
 */
public class Automaton {
    private static Logger logger = Logger.getLogger(Automaton.class);

    private InputFile input;
    private IOutput output;

    private List<IProcessor> processors = new ArrayList<IProcessor>();

    private IAutomatonState state;

    public Automaton(String fileName) {
        state = new AutomatonStateOnlyRead();
        try {
            input = new InputFile(fileName);
        } catch (IOException e) {
            logger.error("Could not open file for reading! Exception: {}", e);
        }

        processors.add(new EscapingProcessor());
        processors.add(new HighlightingProcessor());
        processors.add(new IndentingProcessor());
    }

    /**
     * Run automaton.
     */
    public void run() {
        String line;

        output.write(Settings.getCodetexMacros());

        while (!input.isEnd()) {
            line = input.readLine();
            line = state.handle(this,line);
            output.write(line);
        }

        try {
            input.close();
            output.close();
        } catch (Exception e) {
            logger.error("Could not close files! Exception: {}", e);
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

    public IAutomatonState getState() {
        return state;
    }

    public void setState(IAutomatonState state) {
        this.state = state;
    }

    public void setOutput(IOutput output) {
        this.output = output;
    }
}
