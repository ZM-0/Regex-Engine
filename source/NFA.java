/**
 * A non-deterministic finite-state automaton with epsilon and symbol transitions.
 */
public class NFA {
    /**
     * The start state of the NFA.
     */
    private final NFAState start;

    /**
     * The accepting end state of the NFA.
     */
    private final NFAState end;

    /**
     * Creates an NFA with a single epsilon transition.
     */
    public NFA() {
        this.start = new NFAState(false);
        this.end = new NFAState(true);
        this.start.addTransition(new NFATransition(this.start, this.end));
    }

    /**
     * Creates an NFA to accept a single symbol.
     * @param symbol The symbol to be accepted.
     */
    public NFA(char symbol) {
        this.start = new NFAState(false);
        this.end = new NFAState(true);
        this.start.addTransition(new NFATransition(this.start, this.end, symbol));
    }

    /**
     * Creates an NFA with the given start and end states.
     * @param start The NFA start state.
     * @param end The accepting end state.
     */
    private NFA(NFAState start, NFAState end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the start state of the NFA.
     * @return The start state.
     */
    public NFAState getStart() {
        return this.start;
    }

    /**
     * Combines two NFAs by alternation.
     * @param left The left alternative.
     * @param right The right alternative.
     * @return The combined NFA.
     */
    public static NFA alternate(NFA left, NFA right) {
        NFAState start = new NFAState(false);
        NFAState end = new NFAState(true);

        // Link the new start to the alternatives
        start.addTransition(new NFATransition(start, left.start));
        start.addTransition(new NFATransition(start, right.start));

        // Link the left alternative to the end
        left.end.addTransition(new NFATransition(left.end, end));
        left.end.setAccepting(false);

        // Link the right alternative to the end
        right.end.addTransition(new NFATransition(right.end, end));
        right.end.setAccepting(false);

        return new NFA(start, end);
    }

    /**
     * Combines two NFAs by concatenation.
     * @param left The left NFA.
     * @param right The right NFA.
     * @return The combined NFA.
     */
    public static NFA concatenate(NFA left, NFA right) {
        left.end.addTransition(new NFATransition(left.end, right.start));
        left.end.setAccepting(false);

        return new NFA(left.start, right.end);
    }

    /**
     * Makes an NFA optional.
     * @param inner The NFA to be made optional.
     * @return The optional NFA.
     */
    public static NFA optional(NFA inner) {
        NFAState start = new NFAState(false);
        NFAState end = new NFAState(true);

        // Transition for option not taken
        start.addTransition(new NFATransition(start, end));

        // Transitions for option taken
        start.addTransition(new NFATransition(start, inner.start));
        inner.end.addTransition(new NFATransition(inner.end, end));
        inner.end.setAccepting(false);

        return new NFA(start, end);
    }

    /**
     * Repeats an NFA at least once.
     * @param inner The NFA to be repeated.
     * @return The repeated NFA.
     */
    public static NFA plus(NFA inner) {
        NFAState start = new NFAState(false);
        NFAState end = new NFAState(true);

        // Transitions for at least one occurrence
        start.addTransition(new NFATransition(start, inner.start));
        inner.end.addTransition(new NFATransition(inner.end, end));
        inner.end.setAccepting(false);

        // Transition for optional repetition
        inner.end.addTransition(new NFATransition(inner.end, inner.start));

        return new NFA(start, end);
    }

    /**
     * Repeats an NFA zero or many times.
     * @param inner The NFA to be repeated.
     * @return The repeated NFA.
     */
    public static NFA star(NFA inner) {
        NFAState start = new NFAState(false);
        NFAState end = new NFAState(true);

        // Transition for zero repetitions
        start.addTransition(new NFATransition(start, end));

        // Transitions for one occurrence
        start.addTransition(new NFATransition(start, inner.start));
        inner.end.addTransition(new NFATransition(inner.end, end));
        inner.end.setAccepting(false);

        // Transition for loop
        inner.end.addTransition(new NFATransition(inner.end, inner.start));

        return new NFA(start, end);
    }
}
