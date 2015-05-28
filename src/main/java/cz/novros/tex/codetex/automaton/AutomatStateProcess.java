package cz.novros.tex.codetex.automaton;

import cz.novros.tex.codetex.processing.IProcessor;

/**
 * Created by rostislav on 27.5.15.
 */
public class AutomatStateProcess implements IAutomatState {

    @Override
    public String handle(Automaton automaton, String line) {

        if(line.contains(Automaton.CODE_BLOCK_END)) {
            automaton.setState(new AutomatStateOnlyRead());
            System.out.println("LoL");
            return line;
        } // else if(line.contains(Automaton.CODE_BLOCK_END)


        for (IProcessor processor : automaton.getProessors()) {
            line = processor.processLine(line);
        }
        return line;
    }
}
