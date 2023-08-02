import java.util.Objects;
import java.util.Stack;

/**
 * A preprocessor for regex strings before compilation to an NFA.
 */
public class RegexPreprocessor {
    public RegexPreprocessor() {

    }

    /**
     * Builds an NFA for the given preprocessed regex in postfix notation.
     * @param processedRegex A preprocessed regex in postfix notation.
     * @return The corresponding NFA.
     */
    public NFA buildNFA(String processedRegex) {
        if (Objects.equals(processedRegex, "")) {
            return new NFA();
        }

        Stack<NFA> builderStack = new Stack<>();

        for (int index = 0; index < processedRegex.length(); index++) {
            char symbol = processedRegex.charAt(index);

            if (symbol == '.') {
                NFA second = builderStack.pop();
                NFA first = builderStack.pop();
                first.concatenate(second);
                builderStack.push(first);
            } else if (symbol == '|') {
                NFA second = builderStack.pop();
                NFA first = builderStack.pop();
                first.alternate(second);
                builderStack.push(first);
            } else if (symbol == '*') {
                NFA internal = builderStack.pop();
                internal.repetition();
                builderStack.push(internal);
            } else {
                builderStack.push(new NFA(symbol));
            }
        }

        return builderStack.pop();
    }
}
