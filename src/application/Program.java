package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> capturedPieces = new ArrayList<>();

        while (!chessMatch.isCheckMate()) {
            try {
                UI.clearScreen();

                UI.printMatch(chessMatch, capturedPieces);
                System.out.println();

                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(input);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();

                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(input);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if (capturedPiece != null) {
                    capturedPieces.add(capturedPiece);
                }

                if (chessMatch.isPromoted() != null) {
                    System.out.print("Enter piece for promotion (B/N/R/Q): ");
                    String type = input.nextLine().toUpperCase();

                    while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
                        System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
                        type = input.nextLine().toUpperCase();
                    }
                    chessMatch.replacePromotedPiece(type);
                }
            }
            catch (ChessException | InputMismatchException exception) {
                System.out.println(exception.getMessage());
                input.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, capturedPieces);
    }
}
