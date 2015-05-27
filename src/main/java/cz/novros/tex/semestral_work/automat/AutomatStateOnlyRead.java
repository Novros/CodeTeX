package cz.novros.tex.semestral_work.automat;

/**
 * Created by rostislav on 27.5.15.
 */
public class AutomatStateOnlyRead implements IAutomatState {

    @Override
    public String handle(Automat automat, String line) {
        if(line.contains(Automat.CODE_BLOCK_START)) {
            automat.setState(new AutomatStateProcess());
        }
        return line;
    }
}
