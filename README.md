# RegexEngine

A Java regular expression engine.

## Stages

The engine works in the following stages:

1. Use recursive-descent parsing to validate that the regex is valid.
2. Preprocess the regex to add implicit concatenation operators.
3. Use the shunting-yard algorithm to convert the regex from infix to postfix notation.
4. Use Thompson's construction to build an epsilon-NFA from the postfix regex.
5. Use Thompson's algorithm to traverse the NFA for matching.

## Regex Grammar

The grammar for the regex language is shown below. This is a regex supports basic features.

```
regex = expression

expression = subexpression { “|” subexpression }

subexpression = ( match | group ) { match | group }

group = “(“ expression “)” [ quantifier ]

match = ( character | character class ) [ quantifier ]

quantifier = “+” | “*” | “?” | “{“ [ digit { digit } ] “,” digit { digit } “}” | “{“ digit { digit } “,” “}”

character class = “[“ character { character } “]”

character = letter | digit | whitespace | wildcard

wildcard = “.”

letter = “a” | “b” | “c” | “d” | “e” | “f” | “g” | “h” | “i” | “j” | “k” | “l” | “m” | “n” | “o” | “p” | “q” | “r” | “s” | “t” | “u” | “v” | “w” | “x” | “y” | “z” | “A” | “B” | “C” | “D” | “E” | “F” | “G” | “H” | “I” | “J” | “K” | “L” | “M” | “N” | “O” | “P” | “Q” | “R” | “S” | “T” | “U” | “V” | “X” | “X” | “Y” | “Z”

digit = “0” | “1” | “2” | “3” | “4” | “5” | “6” | “7” | “8” | “9”

whitespace = “ “ | “\t” | “\n”
```
