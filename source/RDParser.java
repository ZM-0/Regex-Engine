/**
 * A parser to convert a regex string to an NFA using recursive-descent parsing.
 */
public class RDParser {
    /**
     * The regex being parsed.
     */
    private final String input;

    /**
     * The current position in the input.
     */
    private int position;

    /**
     * Creates a parser to parse the given regex.
     * @param input The regex string.
     */
    public RDParser(String input) {
        this.input = input;
        this.position = 0;
    }

    public static void main(String[] args) {
        System.out.println(new RDParser("gd|a(y+k)i").parse());
        System.out.println(new RDParser("o+").parse());
        System.out.println(new RDParser("b?q").parse());
        System.out.println(new RDParser("(a|h)k").parse());
        System.out.println(new RDParser("n+m").parse());
    }

    /**
     * Parses the regex.
     * @return A compiled NFA of the regex.
     */
    public NFA parse() {
        return this.expression();
    }

    /**
     * Consumes the given character from the input.
     * @param symbol The symbol expected next in the input.
     * @throws RuntimeException Thrown if the expected symbol doesn't match the current input.
     */
    private void consume(char symbol) throws RuntimeException {
        if (this.input.charAt(this.position) != symbol) {
            throw new RuntimeException("Unexpected symbol '" + this.peek() + "'");
        }

        this.position++;
    }

    /**
     * Gets the current input symbol.
     * @return The current input symbol.
     */
    private char peek() {
        return this.input.charAt(this.position);
    }

    /**
     * Gets the current input symbol and advances to the next.
     * @return The current input symbol.
     */
    private char next() {
        char symbol = this.peek();
        this.consume(symbol);
        return symbol;
    }

    /**
     * Checks if there are more characters in the input.
     * @return True if there are unread input symbols.
     */
    private boolean hasMore() {
        return this.position < this.input.length();
    }

    /**
     * Checks if a symbol is a quantifier.
     * @param symbol The symbol to be checked.
     * @return True if the symbol is a quantifier, false otherwise.
     */
    private boolean isQuantifier(char symbol) {
        return switch (symbol) {
            case '?', '+', '*' -> true;
            default -> false;
        };
    }

    /**
     * Checks if a symbol is a meta character.
     * @param symbol The symbol to be checked.
     * @return True if the symbol is a metacharacter, false otherwise.
     */
    private boolean isMetaCharacter(char symbol) {
        return switch (symbol) {
            case '|', '(', ')', '?', '+', '*', '\\' -> true;
            default -> false;
        };
    }

    /**
     * Parses an expression.
     * @return An NFA for an expression.
     */
    private NFA expression() {
        NFA left = this.term();

        if (this.hasMore() && this.peek() == '|') {
            this.consume('|');
            NFA right = this.expression();
            return NFA.alternate(left, right);
        }

        return left;
    }

    /**
     * Parses a term.
     * @return An NFA for a term.
     */
    private NFA term() {
        NFA left = this.factor();

        if (this.hasMore() && this.peek() != ')' && this.peek() != '|') {
            NFA right = this.term();
            return NFA.concatenate(left, right);
        }

        return left;
    }

    /**
     * Parses a factor.
     * @return An NFA for a factor.
     */
    private NFA factor() {
        NFA inner = this.item();

        if (this.hasMore() && this.isQuantifier(this.peek())) {
            return switch (this.next()) {
                case '?' -> NFA.optional(inner);
                case '+' -> NFA.plus(inner);
                default -> NFA.star(inner);
            };
        }

        return inner;
    }

    /**
     * Parses an item.
     * @return An NFA for an item.
     */
    private NFA item() {
        if (this.hasMore() && this.peek() == '(') {
            this.consume('(');
            NFA inner = this.expression();
            this.consume(')');
            return inner;
        } else {
            return this.character();
        }
    }

    /**
     * Parses a character.
     * @return An NFA for a character.
     */
    private NFA character() {
        if (this.hasMore() && this.peek() == '\\') {
            this.consume('\\');
            return this.metaCharacter();
        } else {
            return this.normalCharacter();
        }
    }

    /**
     * Parses a normal character.
     * @return An NFA for a normal character.
     */
    private NFA normalCharacter() {
        if (this.hasMore() && Character.isLetterOrDigit(this.peek())) {
            return new NFA(this.next());
        } else {
            throw new RuntimeException("Unexpected symbol '" + this.peek() + "'");
        }
    }

    /**
     * Parses a meta character.
     * @return An NFA for a metacharacter.
     */
    private NFA metaCharacter() {
        if (this.hasMore() && this.isMetaCharacter(this.peek())) {
            return new NFA(this.next());
        } else {
            throw new RuntimeException("Unexpected symbol '" + this.peek() + "'");
        }
    }
}
