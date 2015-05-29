package cz.novros.tex.codetex.automaton.state;

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

import cz.novros.tex.codetex.automaton.Automaton;

/**
 * State of automaton.
 *
 * @author Rostislav Novak <rostislav.novak92@gmail.com>
 * @version 1.0
 * @since 2015-05-27
 */
public interface IAutomatonState {

    /**
     * Handle line from automaton.
     *
     * @param automaton Automaton, which i am state.
     * @param line Line, which will be handled.
     * @return Return handled (processed) line.
     */
    public String handle(Automaton automaton, String line);
}
