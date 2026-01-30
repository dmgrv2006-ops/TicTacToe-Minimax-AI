public class Game {
    private final char X_CHAR = 'x';
    private final char O_CHAR = 'o';
    private final int O = -1;
    private final int X = 1;
    private final int aiMark;
    private final int playerMark;
    private final int EMPTY = 0;
    private final int BOARD_ROWS = 3;
    private final int BOARD_COLS = 3;
    private final int[] board = new int[BOARD_ROWS*BOARD_COLS];
    private boolean isAiTurn;
    public Game(char playerMark){
        isAiTurn = playerMark == O_CHAR;
        aiMark = playerMark == X_CHAR ? O:X;
        this.playerMark = aiMark == X ? O:X;
        for(int i = 0; i<BOARD_ROWS*BOARD_COLS;i++)
                board[i] = EMPTY;
    }
    public boolean isGameOngoing() {
        if(isWinner(playerMark) || isWinner(aiMark))
            return false;
        return !isBoardFull();
    }
    private int boardMatrix(int i, int j){
        return board[i*BOARD_ROWS+j];
    }
    public boolean isBoardFull() {
        int i = 0;
        while(i<BOARD_ROWS*BOARD_COLS && board[i] != EMPTY){i++;}
        return i == BOARD_ROWS*BOARD_COLS;
    }

    public boolean isEmpty(int index) {
        return board[index] == EMPTY;
    }

    public void placeMark(int index) {
        int mark = isAiTurn ? aiMark: playerMark;
        board[index] = mark;
        isAiTurn = !isAiTurn;
    }
    public void undoMark(int index){
        board[index] = EMPTY;
        isAiTurn = !isAiTurn;
    }
    public int[] getBoard(){
        return board;
    }
    public boolean isWinner(int mark){
        boolean isWinner = false;
        int marksInARow = 0;
        for(int i = 0;i<BOARD_ROWS && !isWinner;i++){
            marksInARow = 0;
            for(int j = 0;j<BOARD_COLS;j++){
                if(boardMatrix(i,j) == mark)
                    marksInARow++;
            }
            if(marksInARow==3)
                isWinner = true;
        }
        if(isWinner)
            return true;
        for(int j = 0;j<BOARD_COLS && !isWinner;j++){
            marksInARow = 0;
            for(int i = 0;i<BOARD_ROWS;i++){
                if(boardMatrix(i,j) == mark)
                    marksInARow++;
            }
            if(marksInARow==3)
                isWinner = true;
        }
        if(isWinner)
            return true;
        if(boardMatrix(0,0) == mark && boardMatrix(1,1)== mark && boardMatrix(2,2)== mark)
            return true;
        return boardMatrix(0, 2) == mark && boardMatrix(1, 1) == mark && boardMatrix(2, 0) == mark;
    }

    public void makeAiPlay () {
        int bestValue = -12;
        int bestPosition = -1;
        for(int i=0; i<BOARD_ROWS*BOARD_COLS;i++){
            if(isEmpty(i)) {
                placeMark(i);
                int minMaxValue = minMax(0, bestValue, 12);
                if (minMaxValue > bestValue) {
                    bestValue = minMaxValue;
                    bestPosition = i;
                }
                undoMark(i);
            }
        }
        placeMark(bestPosition);
    }
    /**
     * Minimax algorithm with Alpha-Beta pruning for optimal move evaluation.
     * Recursively explores the game tree to find the best possible outcome for the current player.
     * The algorithm alternates between maximizing and minimizing:
     * - AI's turn (isAiTurn = true): Maximizes score, updates alpha, prunes when value >= beta
     * - Player's turn (isAiTurn = false): Minimizes score, updates beta, prunes when value <= alpha
     * @param depth Current depth in the game tree (used for scoring preference)
     * @param alpha Best score the maximizer (AI) can guarantee so far
     * @param beta Best score the minimizer (player) can guarantee so far
     * @return The evaluation score: +10 to -10 based on game outcome, adjusted by depth
     */
    public int minMax(int depth, int alpha, int beta) {
        if (isWinner(playerMark))
            return -10 + depth;
        if (isWinner(aiMark))
            return 10 - depth;
        if (isBoardFull())
            return 0;

        if (isAiTurn) {
            for (int i = 0; i < BOARD_ROWS * BOARD_COLS; i++) {
                if (isEmpty(i)) {
                    placeMark(i);
                    int value = minMax(depth + 1,alpha,beta);
                    undoMark(i);
                    if(value > alpha)
                        alpha = value;
                    if(value>=beta){
                        break;
                    }

                }
            }
            return alpha;
        } else {
            for (int i = 0; i < BOARD_ROWS * BOARD_COLS; i++) {
                if (isEmpty(i)) {
                    placeMark(i);
                    int value = minMax(depth +1,alpha,beta);
                    undoMark(i);
                    if(value < beta)
                        beta = value;
                    if(value<=alpha ){
                        break;
                    }
                }
            }
            return beta;
        }
    }
}
