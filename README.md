# Match Game - two player logic game [![Java CI with Gradle](https://github.com/michal-baran/MatchGame/actions/workflows/gradle.yml/badge.svg)](https://github.com/michal-baran/MatchGame/actions/workflows/gradle.yml)

<!-- TABLE OF CONTENTS -->
<h2 id="about-the-project">Table of contents</h2>
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#overview">Overview</a></li>
    <li><a href="#game-rules">Game rules</a></li>
    <li><a href="#commands">Commands</a></li>
  </ol>
</details>
<!-- ABOUT THE PROJECT -->
<h2 id="about-the-project"> :pencil: About The Project</h2>
This project is inspired by the real game "Match" made by Top Trumps.

<!-- OVERVIEW -->
<h2 id="overview"> :point_right: Overview</h2>

The game has the form of double-sided board with 25 square fields in 5x5 layout. There is a cube in each square, and each cube consists of 6 walls with symbols (in this particular case, characters from the Harry Potter series).

Final game should look like this:
<table>
  <tr>
    <td><img src="/src/main/resources/images/gry.png"></td>
    <td><img src="/src/main/resources/images/hed.png"></td>
    <td><img src="/src/main/resources/images/hag.png"></td>
    <td><img src="/src/main/resources/images/fle.png"></td>
    <td><img src="/src/main/resources/images/her.png"></td>
  </tr>
  <tr>
    <td><img src="/src/main/resources/images/zgr.png"></td>
    <td><img src="/src/main/resources/images/gry.png"></td>
    <td><img src="/src/main/resources/images/dra.png"></td>
    <td><img src="/src/main/resources/images/lun.png"></td>
    <td><img src="/src/main/resources/images/hag.png"></td>
  </tr>
  <tr>
    <td><img src="/src/main/resources/images/syr.png"></td>
    <td><img src="/src/main/resources/images/dum.png"></td>
    <td><img src="/src/main/resources/images/gry.png"></td>
    <td><img src="/src/main/resources/images/har.png"></td>
    <td><img src="/src/main/resources/images/ron.png"></td>
  </tr>
    <tr>
    <td><img src="/src/main/resources/images/tre.png"></td>
    <td><img src="/src/main/resources/images/mcg.png"></td>
    <td><img src="/src/main/resources/images/lun.png"></td>
    <td><img src="/src/main/resources/images/gry.png"></td>
    <td><img src="/src/main/resources/images/hed.png"></td>
  </tr>
      <tr>
    <td><img src="/src/main/resources/images/ton.png"></td>
    <td><img src="/src/main/resources/images/ron.png"></td>
    <td><img src="/src/main/resources/images/tre.png"></td>
    <td><img src="/src/main/resources/images/zgr.png"></td>
    <td><img src="/src/main/resources/images/gry.png"></td>
  </tr>
</table>

Version 1.0 is prepared for playing in the console. The boards will look like below:
<br>
  <img src="/src/main/resources/images/1Pboard.png">
  <img src="/src/main/resources/images/2Pboard.png">


The game comes with fifteen cards with the same symbols as on the dice.

In general:
- there are 15 symbols/characters
- there are 25 cubes with symbols, where the symbol combination on each cube is unique (there isn't two equal cubes),
- cube walls in total: 150, so maximum number of occurrences for each symbol/character is 10,

<!-- GAME RULES -->
<h2 id="game-rules"> :grey_question: Game rules</h2>

1. At the beginning, players draw two cards with the characters symbols.
2. The game starts: first player pushes cube on the top-left field - the pushed cube is now on the second player side.
3. The second player can choose a symbol from the cube in his hand, and push it into almost any field on the board (except the one, from which cube comes from). The pushed cube lands on the first player side.
4. The first player does the same as the second player in step 3.
5. The game continues until one of the players has a "match".

Goal:
The player who scores 5 points wins.

<!-- Commands -->
<h2 id="commands"> 💡 Commands</h2>
There are several commands available throughout the game:

- <b>stats</b> - prints players points,
- <b>cards</b> - prints cards of actual player,
- <b>exit</b> - exits game