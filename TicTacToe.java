import java.util.HashMap;
import java.util.Scanner;

public class TicTacToe {

    public enum Box {
        BAD_PLACEMENT(-2),
        X(-1),
        EMPTY(0),
        O(1),
        SOLVED(2),
        RESET_GAME(3);

        public int value;

        Box(int boxType) {
            this.value = boxType;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }

        public String asString() {
            String rtn;
            switch (value) {
                case -1:
                    rtn = "X";
                    break;
                case 0:
                    rtn = "-";
                    break;
                case 1:
                    rtn = "O";
                    break;
                default:
                    rtn = "Something has gone terribly wrong";
            }
            return rtn;
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public static class Board {

        public class Referee {
            Board currentBoard;

            public Referee(Board b) {
                this.currentBoard = b;
            }

            public Board getBoard() {
                return this.currentBoard;
            }

            public Box checkSolveColumns() {
                for (int x = 0; x <= 2; x++) {
                    int currentSum = 0;
                    for (int y = 0; y <= 2; y++)
                        currentSum += getBoard().getBox(x, y).value;
                    if (currentSum == 3)
                        return Box.X;
                    if (currentSum == -3)
                        return Box.O;
                }
                return Box.EMPTY;
            }

            public Box checkSolveRows() {
                for (int y = 0; y <= 2; y++) {
                    int currentSum = 0;
                    for (int x = 0; x <= 2; x++)
                        currentSum += getBoard().getBox(x, y).value;
                    if (currentSum == 3)
                        return Box.X;
                    if (currentSum == -3)
                        return Box.O;
                }
                return Box.EMPTY;
            }

            public Box checkSolveDiagonals() {
                int topLeftToBottomRightSum = 0;
                int topRightToBottomLeftSum = 0;

                // Top-left to bottom-right diagonal
                topLeftToBottomRightSum += getBoard().getBox(0, 0).value;  // [0][0]
                topLeftToBottomRightSum += getBoard().getBox(1, 1).value;  // [1][1]
                topLeftToBottomRightSum += getBoard().getBox(2, 2).value;  // [2][2]

                // Top-right to bottom-left diagonal
                topRightToBottomLeftSum += getBoard().getBox(2, 0).value;  // [2][0]
                topRightToBottomLeftSum += getBoard().getBox(1, 1).value;  // [1][1]
                topRightToBottomLeftSum += getBoard().getBox(0, 2).value;  // [0][2]

                if (topLeftToBottomRightSum == 3 || topRightToBottomLeftSum == 3)
                    return Box.X;
                if (topLeftToBottomRightSum == -3 || topRightToBottomLeftSum == -3)
                    return Box.O;

                return Box.EMPTY;
            }

            public Box checkSolve() {
                if (isFull())
                    return Box.SOLVED;

                Box checkedSolveRows = checkSolveRows();
                Box checkedSolveColumns = checkSolveColumns();
                Box checkedSolveDiagonals = checkSolveDiagonals();

                if (checkedSolveRows != Box.EMPTY) {
                    return checkedSolveRows;
                }
                if (checkedSolveColumns != Box.EMPTY) {
                    return checkedSolveColumns;
                }
                if (checkedSolveDiagonals != Box.EMPTY) {
                    return checkedSolveDiagonals;
                }

                return Box.EMPTY;
            }

            private boolean isFull() {
                for (int x = 0; x <= 2; x++) {
                    for (int y = 0; y <= 2; y++) {
                        if (getBoard().getBox(x, y) == Box.EMPTY)
                            return false;
                    }
                }
                return true;
            }
        }

        Referee referee;
        Box[][] boardInternal;

        public Board() {
            this.referee = new Referee(this);
            this.boardInternal = new Box[3][3];
            for (int x = 0; x <= 2; x++) {
                for (int y = 0; y <= 2; y++) {
                    this.boardInternal[x][y] = Box.EMPTY; // initialize every slot with an empty box
                }
            }
        }

        public Box getBox(int x, int y) {
            if (x > 2 || y > 2 || x < 0 || y < 0)
                return Box.BAD_PLACEMENT;
            return this.boardInternal[x][y];
        }

        public void resetGame() {
            this.referee = new Referee(this);
            this.boardInternal = new Box[3][3];
            for (int x = 0; x <= 2; x++) {
                for (int y = 0; y <= 2; y++) {
                    this.boardInternal[x][y] = Box.EMPTY; // initialize every slot with an empty box
                }
            }
            System.out.println("*** Game reset ***");
            System.out.println("*** Welcome to TicTacToe! ***");
            System.out.println("*** Type RESET to restart! ***");
            System.out.println("*** Type QUIT to exit! ***");
        }

        public Box setBox(int x, int y, Box player) {
            if (this.referee.checkSolve() == Box.SOLVED || player == Box.RESET_GAME) {
                resetGame();
                return Box.RESET_GAME;
            }

            Box currentBox = getBox(x, y);
            Box rtn = null;
            switch (currentBox) {
                case EMPTY:
                    this.boardInternal[x][y] = player;
                    rtn = this.referee.checkSolve();
                    break;
                case BAD_PLACEMENT:
                    System.out.println("That is an invalid placement option!");
                    rtn = Box.BAD_PLACEMENT;
                    break;
                default:
                    System.out.println("That box is filled!");
                    rtn = Box.BAD_PLACEMENT;
            }
            System.out.println(this.toString());
            return rtn;
        }

        public String toString() {
            StringBuilder boxResult = new StringBuilder();
            boxResult.append(String.format("\n 1 2 3\nA [%s][%s][%s]\nB [%s][%s][%s]\nC [%s][%s][%s]\n",
                    this.boardInternal[0][0].asString(),
                    this.boardInternal[0][1].asString(),
                    this.boardInternal[0][2].asString(),
                    this.boardInternal[1][0].asString(),
                    this.boardInternal[1][1].asString(),
                    this.boardInternal[1][2].asString(),
                    this.boardInternal[2][0].asString(),
                    this.boardInternal[2][1].asString(),
                    this.boardInternal[2][2].asString()
            ));
            return boxResult.toString();
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Board ourBoard = new Board();
        HashMap<String, Box> Players = new HashMap<>();
        Players.put("Player1", Box.X);
        Players.put("Player2", Box.O);
        String currentPlayer = "Player1";

        System.out.println("*** Welcome to TicTacToe! ***");
        System.out.println("*** Type RESET to restart! ***");
        System.out.println("*** Type QUIT to exit! ***");
        System.out.println(ourBoard.toString());

        do {
            System.out.printf("Your move, %s: ", currentPlayer);
            String desiredInput = keyboard.next();
            Box inputResult = null;

            if (desiredInput.toLowerCase().equals("quit"))
                break;
            if (desiredInput.toLowerCase().equals("reset"))
                inputResult = ourBoard.setBox(0, 0, Box.RESET_GAME);
            else if (desiredInput != null) {
                if (desiredInput.length() != 2) {
                    System.out.printf("Hey, %s! Please enter your format in the format of \"LetterNumber\". ex. A2%n%s is in the wrong format!%n",
                            currentPlayer, desiredInput);
                    continue;
                } else {
                    char columnPicked = desiredInput.toLowerCase().charAt(0);
                    String rowPicked = desiredInput.split("")[1];
                    if (isInteger(rowPicked)) {
                        int decidedRow = Integer.parseInt(rowPicked) - 1;
                        switch (columnPicked) {
                            case 'a':
                                inputResult = ourBoard.setBox(0, decidedRow, Players.get(currentPlayer));
                                break;
                            case 'b':
                                inputResult = ourBoard.setBox(1, decidedRow, Players.get(currentPlayer));
                                break;
                            case 'c':
                                inputResult = ourBoard.setBox(2, decidedRow, Players.get(currentPlayer));
                                break;
                            default:
                                System.out.println("Could not decipher your column (A,B,C allowed.)");
                                continue;
                        }
                    } else {
                        System.out.println("Could not decipher your row (Only numbers allowed)");
                        continue;
                    }
                }
            }

            switch (inputResult) {
                case X:
                    System.out.println("Player 2 wins!");
                    currentPlayer = "Player1";
                    ourBoard.resetGame();
                    break;
                case O:
                    System.out.println("Player 1 wins!");
                    currentPlayer = "Player1";
                    ourBoard.resetGame();
                    break;
                case RESET_GAME:
                    System.out.printf("%n%s requested a reset for the previous game.%n", currentPlayer);
                    currentPlayer = "Player1";
                    break;
                case BAD_PLACEMENT:
                    break;
                case SOLVED:
                    System.out.println("Draw game! Better luck next time!");
                    currentPlayer = "Player1";
                    ourBoard.resetGame();
                    break;
                case EMPTY:
                    if (currentPlayer.equals("Player1"))
                        currentPlayer = "Player2";
                    else
                        currentPlayer = "Player1";
                    break;
                default:
                    System.out.println("Something went wrong!");
                    System.out.println("RESULT: " + inputResult);
            }
        } while (true);
        keyboard.close();
    }
}
