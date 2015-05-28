package cz.novros.tex.codetex.automaton;

/**
 * Created by rostislav on 28.5.15.
 */
public class AutomatStateComment implements IAutomatState {

    @Override
    public String handle(Automaton automaton, String line) {
        return line;
    }
}
