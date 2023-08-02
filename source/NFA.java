/**
 * A non-deterministic finite-state automaton with epsilon and symbol transitions.
 */
public class NFA {
    /**
     * The starting state.
     */
    private NFAState start;

    /**
     * The ending accepting state.
     */
    private NFAState end;

    /**
     * Creates a two-state NFA to recognise the empty string (i.e. with a single epsilon-transition).
     */
    public NFA() {
        this.start = new NFAState(false);
        this.end = new NFAState(true);
        this.start.addTransition(new NFATransition(this.start, this.end));
    }

    /**
     * Creates a two-state NFA to recognise a single symbol.
     * @param symbol The symbol to be accepted by the NFA.
     */
    public NFA(char symbol) {
        this.start = new NFAState(false);
        this.end = new NFAState(true);
        this.start.addTransition(new NFATransition(this.start, this.end, symbol));
    }
}
