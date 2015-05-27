package cz.novros.tex.semestral_work.automat;

import cz.novros.tex.semestral_work.processing.IProcessor;

/**
 * Created by rostislav on 27.5.15.
 */
public class AutomatStateProcess implements IAutomatState {

    @Override
    public String handle(Automat automat, String line) {
        if(line.contains(Automat.CODE_BLOCK_END)) {
            automat.setState(new AutomatStateOnlyRead());
            System.out.println("LoL");
            return line;
        }
        for (IProcessor processor : automat.getProessors()) {
            line = processor.processLine(line);
        }
        return line;
    }
}
