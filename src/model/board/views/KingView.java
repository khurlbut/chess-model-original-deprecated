package model.board.views;

import java.util.ArrayList;
import java.util.List;

import model.board.BoardPosition;
import model.board.ChessBoard;
import model.board.Square;
import model.enums.Color;
import model.enums.ViewDirection;
import model.enums.ViewDistance;
import model.piece.Piece;

public final class KingView extends RadiatingView {

    private static final ViewDirection[] KING_MOVES = { ViewDirection.UP, ViewDirection.RIGHT_UP, ViewDirection.RIGHT,
            ViewDirection.RIGHT_DOWN, ViewDirection.DOWN, ViewDirection.LEFT_DOWN, ViewDirection.LEFT, ViewDirection.LEFT_UP };
    private Color color;
    private ChessBoard chessBoard;

    KingView(BoardPosition boardPosition, Color color) {
        super(boardPosition, color, KING_MOVES, ViewDistance.SINGLE_UNIT);

        this.color = color;
        this.chessBoard = boardPosition.chessBoard();
    }

    @Override
    public List<Square> moveToSquares() {
        List<Square> moveToSquares = new ArrayList<Square>();
        List<Square> availableSquares = super.moveToSquares();

        for (Square availableSquare : availableSquares) {
            if (piecesAttackingAt(availableSquare).size() == 0) {
                moveToSquares.add(availableSquare);
            }
        }

        return moveToSquares;
    }

    private List<Piece> piecesAttackingAt(Square s) {
        List<Piece> attackingPieces = new ArrayList<Piece>();
        List<Piece> opponentPieces = chessBoard.piecesFor(color.opponentColor());
        for (Piece opponentPiece : opponentPieces) {
            if (opponentPiece.threatenedSquares(chessBoard).contains(s)) {
                attackingPieces.add(opponentPiece);
            }
        }
        return attackingPieces;
    }

}