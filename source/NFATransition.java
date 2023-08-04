/**
 * An ε or symbol transition between NFA states.
 */
public class NFATransition {
    /**
     * Indicates whether the transition is a symbol or ε one.
     */
    private final boolean isEpsilon;

    /**
     * The transition start state.
     */
    private final NFAState start;

    /**
     * The transition end state.
     */
    private final NFAState end;

    /**
     * The transition symbol.
     */
    private final char symbol;

    /**
     * Creates a symbol-transition between the given states on the given symbol.
     * @param start The start state.
     * @param end The end state.
     * @param symbol The transition symbol.
     */
    public NFATransition(NFAState start, NFAState end, char symbol) {
        this.isEpsilon = false;
        this.start = start;
        this.end = end;
        this.symbol = symbol;
    }

    /**
     * Creates an ε-transition between the given states.
     * @param start The start state.
     * @param end The end state.
     */
    public NFATransition(NFAState start, NFAState end) {
        this.isEpsilon = true;
        this.start = start;
        this.end = end;
        this.symbol = 'ε';
    }
}
