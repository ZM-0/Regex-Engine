/**
 * A symbol or epsilon transition between NFA states.
 */
public class NFATransition {
    /**
     * The start state.
     */
    private final NFAState start;

    /**
     * The end state.
     */
    private final NFAState end;

    /**
     * The transition symbol.
     */
    private final char symbol;

    /**
     * Creates an epsilon-transition.
     * @param start The start state.
     * @param end The end state.
     */
    public NFATransition(NFAState start, NFAState end) {
        this.start = start;
        this.end = end;
        this.symbol = 'ε';
    }

    /**
     * Creates a symbol transition.
     * @param start The start state.
     * @param end The end state.
     * @param symbol The transition symbol.
     */
    public NFATransition(NFAState start, NFAState end, char symbol) {
        this.start = start;
        this.end = end;
        this.symbol = symbol;
    }

    /**
     * Gets the end of the transition.
     * @return The state at the end of the transition.
     */
    public NFAState getEnd() {
        return this.end;
    }

    /**
     * Gets the transition symbol.
     * @return The transition symbol.
     */
    public char getSymbol() {
        return this.symbol;
    }

    /**
     * Checks if this transition is an epsilon-transition.
     * @return True if this is an epsilon-transition, false otheriwise.
     */
    public boolean isEpsilon() {
        return this.symbol == 'ε';
    }
}
