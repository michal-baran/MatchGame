<h1 align="center">MatchGame - two player logic game</h1>

<!-- TABLE OF CONTENTS -->
<h2 id="about-the-project">Table of contents</h2>
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#overview">Overview</a></li>
    <li><a href="#project-files-description">Game contents</a></li>
    <li><a href="#project-files-description">Game rules</a></li>
  </ol>
</details>
<!-- ABOUT THE PROJECT -->
<h2 id="about-the-project"> :pencil: About The Project</h2>
This project is inspired by the real game "Match" made by Top Trumps.

The game has the form of double-sided board with 25 square fields in 5x5 layout. There is a cube in each square, and each cube consists of 6 walls with symbols (in this particular case, characters from the Harry Potter series).

The game comes with fifteen cards with the same symbols as on the dice.

In general:
- there are 15 symbols/characters
- there are 25 cubes with symbols, where the symbol combination on each cube is unique (there isn't two equal cubes),
- cube walls in total: 150, so maximum number of occurrences for each symbol/character is 10,

Game rules:
1. At the beginning, players draw two cards with characters symbols.
2. The game starts: first player pushes cube on the top-left field - the pushed cube is now on the second player side.
3. The second player can choose a symbol from cube in his hand, and push it into almost any field on the board (except the one, from which cube comes from). The pushed cube lands on the first player side.
4. The first player does the same as the second player in step 3.
5. The game continues until one of the players has a "match".

Goal:
The player who scores 5 points wins.
