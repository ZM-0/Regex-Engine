# RegexEngine

A Java regular expression engine.

## Features

- Single character matching: ‘a’, ‘q’, etc.
- Concatenation: ‘aef’
- Alternation: ‘center|centre’
- Grouping: ‘(ab|c)d’
- Quantifiers: ‘a+’, ‘g*’ ‘fg?’
- Escaping: ‘\\’, ‘\+’, ‘\(‘, etc.

### Operator Precedence

1. Escaping
2. Grouping
3. Quantifiers
4. Concatenation
5. Alternation

## Grammar

```
Expression = Term | Term “|” Expression

Term = Factor | Factor Term

Factor = Item | Item Quantifier

Item = Character | “(“ Expression “)”

Quantifier = “?” | “+” | “*”

Character = NormalCharacter | “\” MetaCharacter

NormalCharacter = “a” | “b” | “c” | “d” | “e” | “f” | “g” | “h” | “i” | “j” | “k” | “l” | “m” | “n” | “o” | “p” | “q” |
                “r” | “s” | “t” | “u” | “v” | “w” | “x” | “y” | “z” | “A” | “B” | “C” | “D” | “E” | “F” | “G” | “H” |
                “I” | “J” | “K” | “L” | “M” | “N” | “O” | “P” | “Q” | “R” | “S” | “T” | “U” | “V” | “W” | “X” | “Y” |
                “Z” | “0” | “1” | “2” | “3” | “4” | “5” | “6” | “7” | “8” | “9”

MetaCharacter = “|” | “(“ | “)” | “?” | “+” | “*” | “\”
```

## Engine Stages

The engine works in the following stages:

1. Use recursive-descent parsing to validate that the regex is valid.
2. Build an NFA during parsing.
3. Use Thompson's algorithm to traverse the NFA for matching.
