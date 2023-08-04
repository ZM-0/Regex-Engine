import java.util.ArrayList;
import java.util.List;

/**
 * An NFA state.
 */
public class NFAState {
    /**
     * Indicates whether the state is accepting.
     */
    private boolean isAccepting;

    /**
     * The outgoing transitions to other states.
     */
    private final List<NFATransition> transitions;

    /**
     * Creates a state with no outgoing transitions.
     * @param isAccepting Indicates whether the state is accepting.
     */
    public NFAState(boolean isAccepting) {
        this.isAccepting = isAccepting;
        this.transitions = new ArrayList<>();
    }

    /**
     * Checks whether the state is accepting.
     * @return True if the state is accepting, false otherwise.
     */
    public boolean isAccepting() {
        return this.isAccepting;
    }

    /**
     * Sets whether the state is accepting.
     * @param isAccepting Indicates whether the state is accepting.
     */
    public void setAccepting(boolean isAccepting) {
        this.isAccepting = isAccepting;
    }

    /**
     * Adds an outgoing transition.
     * @param transition A transition from this state to another.
     */
    public void addTransition(NFATransition transition) {
        this.transitions.add(transition);
    }

    /**
     * Gets the outgoing transitions.
     * @return A list of the outgoing transitions.
     */
    public List<NFATransition> getTransitions() {
        return this.transitions;
    }

    /**
     * Gets a state linked to this on the given symbol.
     * @param symbol The transition symbol.
     * @return The state linked on the given symbol, or null if there is no such transition.
     */
    public NFAState getLinked(char symbol) {
        for (NFATransition transition : this.transitions) {
            if (transition.getSymbol() == symbol) {
                return transition.getEnd();
            }
        }

        return null;
    }

    /**
     * Counts the number of outgoing epsilon transitions.
     * @return The number of outgoing epsilon transitions.
     */
    public int countEpsilons() {
        int count = 0;

        for (NFATransition transition : this.transitions) {
            if (transition.isEpsilon()) {
                count++;
            }
        }

        return count;
    }
}
