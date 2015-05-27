package cz.novros.tex.semestral_work.automat;

/**
 * Created by rostislav on 27.5.15.
 */
public interface IAutomatState {
    public String handle(Automat automat, String line);
}
