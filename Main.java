import java.util.Scanner;
public class Main {
    private static final String PICK_MSG = "Pick a mark: X or O";
    private static final String INVALID_MARK_MSG = "Invalid mark";
    private static final String INVALID_INDEX_MSG = "Invalid index";
    private static final String MARK_THERE_MSG = "Mark already there";
    private static final String PLAYER_WIN_MSG = "Player wins.";
    private static final String AI_WIN_MSG = "Ai wins.";
    private static final String DRAW_MSG = "It's a draw.";
    private static final char X = 'x';
    private static final char O = 'o';
    private static final int O_INT = -1;
    private static final int X_INT = 1;
    private static final int BOARD_ROWS = 3;
    private static final int BOARD_COLS = 3;

    private static void showBoard(Game ticTacToe){
        int[] board = ticTacToe.getBoard();
        int index;
        for(int i = 0;i<BOARD_ROWS;i++){
            for(int j = 0;j<BOARD_COLS;j++){
                index = i*BOARD_ROWS+j;
                if(board[index]==O_INT)
                    System.out.print("O");
                else if(board[index]==X_INT)
                    System.out.print("X");
                else
                    System.out.print(index+1);
                if(j!=2)
                    System.out.print(" | ");
            }
            if(i!=2)
                System.out.print("\n---------\n");
            else
                System.out.println();
        }
    }
    private static boolean moveIsValid(int i) {
        return i>=0 && i<=8;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        char mark;
        do {
            System.out.println(PICK_MSG);
            String markString = in.next().trim().toLowerCase();
            in.nextLine();
            char[] markCharArray = markString.toCharArray();
            mark = markCharArray[0];
            if(mark != X && mark != O)
                System.out.println(INVALID_MARK_MSG);
        }while (mark != X && mark != O);
        int playerMark = mark == X ? X_INT: O_INT ;
        int aiMark = mark == X ? O_INT: X_INT ;
        Game ticTacToe = new Game(mark);
        if(mark == O) {
            ticTacToe.makeAiPlay();
        }
        do{
            showBoard(ticTacToe);
            int index = in.nextInt() - 1;
            in.nextLine();
            if (!moveIsValid(index))
                System.out.println(INVALID_INDEX_MSG);
            else if (ticTacToe.isEmpty(index)) {
                    ticTacToe.placeMark(index);
                if (ticTacToe.isGameOngoing())
                    ticTacToe.makeAiPlay();

            } else {
                System.out.println(MARK_THERE_MSG);
            }
        }while(ticTacToe.isGameOngoing());
        showBoard(ticTacToe);
        if(ticTacToe.isWinner(playerMark))
            System.out.println(PLAYER_WIN_MSG);
        else if(ticTacToe.isWinner(aiMark))
            System.out.println(AI_WIN_MSG);
        else
            System.out.println(DRAW_MSG);
    }

}
