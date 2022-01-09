package boardgame;

public abstract class Piece {
    protected Position position;
    private final Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {
        boolean[][] auxMatrix = possibleMoves();

        for (boolean[] matrix : auxMatrix) {
            for (int j = 0; j < auxMatrix.length; j++) {
                if (matrix[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
