
# Tic-Tac-Toe Game in Java

This is a console-based implementation of the classic **Tic-Tac-Toe** game in Java. It supports two players playing alternately, with options for resetting the game and quitting at any time.

## Features:
- **Two Players**: Player 1 uses "X" and Player 2 uses "O".
- **Move Input**: Players enter their move in a "LetterNumber" format (e.g., "A1", "B3").
- **Game Reset**: Players can reset the game at any time by typing `RESET`.
- **Quit Game**: Players can quit the game at any time by typing `QUIT`.
- **Winner Detection**: The game checks for winning conditions after each move.
- **Draw Detection**: The game detects a draw if all spaces are filled with no winner.

## How to Run:
1. **Compile the Program**: 
   Open your terminal/command prompt and navigate to the directory containing the file. Run the following command to compile:

   javac TicTacToe.java


2. **Run the Program**:
   Once compiled, run the program using:
 
   java TicTacToe
  

## How to Play:
- After starting the game, Player 1 (X) will be prompted to make a move.
- Enter your move in the format of "LetterNumber" (e.g., A1, B3).
- The board will be displayed after each move.
- Player 1 and Player 2 take turns until a winner is found or a draw occurs.
- To reset the game at any time, type `RESET`.
- To quit the game, type `QUIT`.

## Example Game Flow:
```plaintext
*** Welcome to TicTacToe! ***
*** Type RESET to restart! ***
*** Type QUIT to exit! ***

Your move, Player1: A1
 1 2 3
A [X][-][-]
B [-][-][-]
C [-][-][-]

Your move, Player2: B2
 1 2 3
A [X][-][-]
B [-][O][-]
C [-][-][-]

Your move, Player1: C3
 1 2 3
A [X][-][-]
B [-][O][-]
C [-][-][X]

...
```

## Game Rules:
- The game is played on a 3x3 grid.
- Players take turns marking one of the 9 spaces on the grid with their respective symbols (X or O).
- The first player to align three of their symbols horizontally, vertically, or diagonally wins the game.
- If all spaces are filled without a winner, the game ends in a draw.

## Dependencies:
- **Java 8 or higher** is required to run the game.

## License:
This project is open source and is available for use and modification. Feel free to contribute or fork the project.

---
