import java.util.ArrayList;
import java.util.List;

/**
 * A state in the epsilon-NFA.
 */
public class NFAState {
    /**
     * Indicates whether the state is accepting.
     */
    private final boolean isAccepting;

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
     * Adds an outgoing transition.
     * @param transition The transition.
     */
    public void addTransition(NFATransition transition) {
        this.transitions.add(transition);
    }
}
