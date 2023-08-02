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

    /**
     * Combines this NFA with another to recognise the concatenation of two NFAs. Only this NFA is updated.
     * @param other The other NFA to be concatenated.
     */
    public void concatenate(NFA other) {
        this.end.setAccepting(false);
        this.end.addTransition(new NFATransition(this.end, other.start));
        this.end = other.end;
    }

    /**
     * Combines this NFA with another to recognise the alternation/union of two NFAs. Only this NFA is updated.
     * @param other The other NFA to be alternated with.
     */
    public void alternate(NFA other) {
        // Link the new start to the NFAs with epsilon-transitions
        NFAState newStart = new NFAState(false);

        newStart.addTransition(new NFATransition(newStart, this.start));
        newStart.addTransition(new NFATransition(newStart, other.start));

        // Link the NFAs to the new end with epsilon-transitions
        NFAState newEnd = new NFAState(true);

        this.end.setAccepting(false);
        this.end.addTransition(new NFATransition(this.end, newEnd));

        other.end.setAccepting(false);
        other.end.addTransition(new NFATransition(other.end, newEnd));

        this.start = newStart;
        this.end = newEnd;
    }

    /**
     * Converts this NFA to a zero-or-many repetition of itself.
     */
    public void repetition() {
        NFAState newStart = new NFAState(false);
        NFAState newEnd = new NFAState(true);

        newStart.addTransition(new NFATransition(newStart, newEnd));
        newStart.addTransition(new NFATransition(newStart, this.start));

        this.end.setAccepting(false);

        this.end.addTransition(new NFATransition(this.end, this.start));
        this.end.addTransition(new NFATransition(this.end, newEnd));

        this.start = newStart;
        this.end = newEnd;
    }
}
