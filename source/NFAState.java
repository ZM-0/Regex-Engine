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
}
