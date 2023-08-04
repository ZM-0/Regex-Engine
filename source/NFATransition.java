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
}
