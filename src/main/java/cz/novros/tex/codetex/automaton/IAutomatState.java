package cz.novros.tex.codetex.automaton;

/**
 * Created by rostislav on 27.5.15.
 */
public interface IAutomatState {
    public String handle(Automaton automaton, String line);
}
