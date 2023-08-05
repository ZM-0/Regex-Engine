import java.util.ArrayList;
import java.util.List;

/**
 * A regular expression to match patterns in text.
 */
public class Regex {
    /**
     * The regex compiled to an NFA.
     */
    private final NFA nfa;

    /**
     * The raw regex string.
     */
    private final String regex;

    /**
     * Compiles the given regex for matching.
     * @param regex The regex string to be compiled.
     */
    public Regex(String regex) {
        this.nfa = new RDParser(regex).parse();
        this.regex = regex;
    }

    /**
     * Entry-point method to use regex from command-line.
     * @param arguments Command-line arguments.
     */
    public static void main(String[] arguments) {
        if (arguments.length != 2) {
            System.out.println("Invalid usage. Provide a regex and a word to match.");
            return;
        }

        if (new Regex(arguments[0]).match(arguments[1]))
            System.out.println("Regex: \"" + arguments[0] + "\" matches \"" + arguments[1] + "\"");
        else
            System.out.println("Regex: \"" + arguments[0] + "\" doesn't match \"" + arguments[1] + "\"");
    }

    /**
     * Checks if the given string matches the regex.
     * @param word The string to be matched.
     * @return True for a match, false otherwise.
     */
    public boolean match(String word) {
        // Get the starting states
        List<NFAState> currentStates = new ArrayList<>();
        this.getProperStates(this.nfa.getStart(), currentStates, new ArrayList<>());

        // Match characters in the word with the NFA
        for (int index = 0; index < word.length(); index++) {
            char symbol = word.charAt(index);

            List<NFAState> nextStates = new ArrayList<>();

            // Find the next states
            for (NFAState state : currentStates) {
                NFAState next = state.getLinked(symbol);

                if (next != null) {
                    this.getProperStates(next, nextStates, new ArrayList<>());
                }
            }

            currentStates = nextStates;
        }

        // Check if the word is accepted by the NFA
        for (NFAState state : currentStates)
            if (state.isAccepting()) return true;

        return false;
    }

    /**
     * Uses a depth-first search to add all states without outgoing epsilon-transitions to the states list.
     * @param current The current state in the search.
     * @param states The list of states without outgoing epsilon-transitions.
     * @param visited The list of states visited in the search.
     */
    private void getProperStates(NFAState current, List<NFAState> states, List<NFAState> visited) {
        if (current.countEpsilons() == 0) {
            states.add(current);
            return;
        }

        // DFS any states linked on epsilon-transitions until no more outgoing epsilon-transitions
        for (NFATransition transition : current.getTransitions()) {
            if (!transition.isEpsilon()) continue;

            NFAState next = transition.getEnd();

            if (!visited.contains(next)) {
                visited.add(next);
                this.getProperStates(next, states, visited);
            }
        }
    }
}
