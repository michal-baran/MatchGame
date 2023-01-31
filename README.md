# MatchGame - logic game for two players.

This project is inspired by the real game "Match" from Top Trumps.

The game has the form of double-sided board with 25 square fields in 5x5 layout. There is a cube in each square, and each cube consists of 6 walls with symbols (in this particular case, characters from the Harry Potter series).

The game comes with fifteen cards with the same symbols as on the dice.

In general:
- there are 15 symbols/characters
- there are 25 cubes with symbols, where the symbol combination on each cube is unique (there isn't two equal cubes),
- cube walls in total: 150, so maximum number of occurences for each symbol/character is 10,

Game rules:
1. An the beggining, players draw two cards with characters symbols.
2. The game starts: first player pushes cube on the top-left field - the pushed cube is now on the second player side.
3. The second player can choose a symbol from cube in his hand, a push it into almost any field on the board (except the one, from which cube comes from).
