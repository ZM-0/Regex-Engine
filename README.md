# RegexEngine

A Java regular expression engine.

## Stages

The engine works in the following stages:

1. Use recursive-descent parsing to validate that the regex is valid.
2. Build an NFA during parsing.
3. Use Thompson's algorithm to traverse the NFA for matching.

## Features

- Alternation: "|"
- Grouping: "(" ")"
- Quantifiers: "+", "*", "?", "{n}", "{n,}", "{,m}", "{n,m}"
- Wildcard: "."
- Character classes: "[" "]"

### Operator Precedence

1. Escaping: \
2. Grouping: (), [], . (wildcard)
3. Quantifiers: +, *, ?, {n}, {n,}, {,m}, {n,m}
4. Concatenation: .
5. Alternation: |

## Regex Grammar

The grammar for the regex language is shown below.

```
Expression = Subexpression { “|” Subexpression }

Subexpression = Item { Item }

Item = Match [ Quantifier ]

Match = Match Character | “(“ Expression “)” | “[“ Character Class “]” | “.”

Quantifier = “+” | “*” | “?” | “{“ Number “,” [ Number ] “}” | “{“ [ Number ] “,” Number “}”

Match Character = Non Meta Character | “\” Meta Character

Character Class = Character { Character }

Character = Non Meta Character | Meta Character

Non Meta Character = “a” | “b” | “c” | “d” | “e” | “f” | “g” | “h” | “i” | “j” | “k” | “l” | “m” | “n” | “o” | “p”
                    | “q” | “r” | “s” | “t” | “u” | “v” | “w” | “x” | “y” | “z” | “A” | “B” | “C” | “D” | “E” | “F”
                    | “G” | “H” | “I” | “J” | “K” | “L” | “M” | “N” | “O” | “P” | “Q” | “R” | “S” | “T” | “U” | “V”
                    | “W” | “X” | “Y” | “Z”

Meta Character = “.” | “?” | “+” | “*” | “\” | “(“ | “)” | “[“ | “]” | “{“ | “}” | “|”

Number = Digit { Digit }

Digit = “0” | “1” | “2” | “3” | “4” | “5” | “6” | “7” | “8” | “9”
```
