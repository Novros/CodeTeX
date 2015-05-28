package cz.novros.tex.codetex.automaton;

/**
 * Created by rostislav on 27.5.15.
 */
public class AutomatStateOnlyRead implements IAutomatState {

    @Override
    public String handle(Automaton automaton, String line) {
        if(line.contains(Automaton.CODE_BLOCK_START)) {
            automaton.setState(new AutomatStateProcess());
        }
        return line + "\n";
    }
}
