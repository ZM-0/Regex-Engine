import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A state in the epsilon-NFA.
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
     * A unique integer ID for the state.
     */
    private final int id;

    /**
     * Creates a new state with no outgoing transitions.
     * @param isAccepting Indicates whether the state is accepting.
     */
    public NFAState(boolean isAccepting) {
        this.isAccepting = isAccepting;
        this.transitions = new ArrayList<>();
        this.id = new Random().nextInt(100);
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
