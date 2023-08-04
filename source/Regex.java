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

    public static void main(String[] args) {
        System.out.println(new Regex("a").match("a"));
        System.out.println(new Regex("a").match("aa"));
        System.out.println(new Regex("a").match("b"));
        System.out.println(new Regex("ok?").match("o"));
        System.out.println(new Regex("ok?").match("ok"));
        System.out.println(new Regex("color|colour").match("color"));
        System.out.println(new Regex("color|colour").match("colour"));
        System.out.println(new Regex("color|colour").match("calaah"));
        System.out.println(new Regex("bh+e").match("bhhhe"));
        System.out.println(new Regex("q(f|e)+j?").match("qffeeefj"));
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
        for (NFAState state : currentStates) {
            if (state.isAccepting()) {
                System.out.println("\"" + this.regex + "\" matches \"" + word + "\"");
                return true;
            }
        }

        System.out.println("\"" + this.regex + "\" doesn't match \"" + word + "\"");
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
