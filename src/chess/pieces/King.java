package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece auxPiece = (ChessPiece) getBoard().piece(position);

        return (auxPiece == null) || (auxPiece.getColor() != getColor());
    }

    private boolean testRookCastling(Position position) {
        ChessPiece auxPiece = (ChessPiece) getBoard().piece(position);

        return (auxPiece instanceof Rook) && (auxPiece.getColor() == getColor()) && (auxPiece.getMoveCount() == 0);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] auxMatrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position auxPosition = new Position(0, 0);

        // move up
        auxPosition.setValues(position.getRow() - 1, position.getColumn());

        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // move down
        auxPosition.setValues(position.getRow() + 1, position.getColumn());

        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // move left
        auxPosition.setValues(position.getRow(), position.getColumn() - 1);

        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // move right
        auxPosition.setValues(position.getRow(), position.getColumn() + 1);

        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // move nw
        auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);

        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // move ne
        auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);

        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // move sw
        auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);

        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // move se
        auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);

        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // castling
        if (getMoveCount() == 0 && !chessMatch.isCheck()) {
            // kingside
            Position positionR1 = new Position(position.getRow(), position.getColumn() + 3);

            if (testRookCastling(positionR1)) {
                Position auxPosition1 = new Position(position.getRow(), position.getColumn() + 1);
                Position auxPosition2 = new Position(position.getRow(), position.getColumn() + 2);

                if (getBoard().piece(auxPosition1) == null && getBoard().piece(auxPosition2) == null) {
                    auxMatrix[position.getRow()][position.getColumn() + 2] = true;
                }
            }

            // queenside
            Position positionR2 = new Position(position.getRow(), position.getColumn() - 4);

            if (testRookCastling(positionR2)) {
                Position auxPosition1 = new Position(position.getRow(), position.getColumn() - 1);
                Position auxPosition2 = new Position(position.getRow(), position.getColumn() - 2);
                Position auxPosition3 = new Position(position.getRow(), position.getColumn() - 3);

                if (getBoard().piece(auxPosition1) == null && getBoard().piece(auxPosition2) == null && getBoard().piece(auxPosition3) == null) {
                    auxMatrix[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }

        return auxMatrix;
    }
}
