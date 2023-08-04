import java.util.ArrayList;
import java.util.List;

/**
 * A state in an ε-NFA.
 */
public class NFAState {
    /**
     * Indicates whether the state is accepting.
     */
    private boolean isAccepting;

    /**
     * The outgoing transitions.
     */
    private final List<NFATransition> transitions;

    /**
     * Creates a new state with no outgoing transitions.
     * @param isAccepting Indicates whether the state is accepting.
     */
    public NFAState(boolean isAccepting) {
        this.isAccepting = isAccepting;
        this.transitions = new ArrayList<>();
    }

    /**
     * Sets whether the state is accepting.
     * @param isAccepting Indicates whether the state should be set as accepting.
     */
    public void setAccepting(boolean isAccepting) {
        this.isAccepting = isAccepting;
    }

    /**
     * Adds an outgoing transition.
     * @param transition The transition.
     */
    public void addTransition(NFATransition transition) {
        this.transitions.add(transition);
    }

    /**
     * Gets the transitions from this state.
     * @return A list of the outward transitions.
     */
    public List<NFATransition> getTransitions() {
        return this.transitions;
    }
}
