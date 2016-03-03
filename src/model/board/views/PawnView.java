package model.board.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.board.BoardPosition;
import model.board.ChessBoard;
import model.board.Square;
import model.enums.Color;
import model.enums.ViewDirection;
import model.piece.Piece;

public class PawnView implements RankView {

    private static final ViewDirection[] UP_ATTACKS = { ViewDirection.RIGHT_UP, ViewDirection.LEFT_UP };
    private static final ViewDirection[] DOWN_ATTACKS = { ViewDirection.RIGHT_DOWN, ViewDirection.LEFT_DOWN };
    private final Color viewColor;
    private final ViewDirection pawnDirection;

    private final List<Square> moveToSquares;
    private final List<Square> squaresHoldingPiecesAttacked;
    private final List<Square> squaresHoldingPiecesDefended;
    private final ChessBoard chessBoard;
    private Square viewPoint;

    public PawnView(BoardPosition boardPosition, Color viewColor) {

        this.chessBoard = boardPosition.chessBoard();
        this.viewPoint = boardPosition.square();
        this.viewColor = viewColor;

        this.pawnDirection = pawnDirection();

        // Initializing these here is a bit ugly, but making these guys final requires it.
        moveToSquares = new ArrayList<Square>();
        squaresHoldingPiecesAttacked = new ArrayList<Square>();
        squaresHoldingPiecesDefended = new ArrayList<Square>();

        addSquaresToLists();
    }

    private ViewDirection pawnDirection() {
        return viewColor.equals(Color.WHITE) ? ViewDirection.UP : ViewDirection.DOWN;
    }

    @Override
    public List<Square> moveToSquares() {
        return moveToSquares;
    }

    @Override
    public List<Square> squaresHoldingPiecesAttacked() {
        return squaresHoldingPiecesAttacked;
    }

    @Override
    public List<Square> squaresHoldingPiecesDefended() {
        return squaresHoldingPiecesDefended;
    }

    @Override
    public List<Square> threatenedSquares() {
        Square leftAttack = viewPoint.neighbor(pawnAttacks()[0]);
        Square rightAttack = viewPoint.neighbor(pawnAttacks()[1]);

        if (leftAttack != null && rightAttack != null) {
            return Arrays.asList(leftAttack, rightAttack);
        }
        if (leftAttack != null) {
            return Arrays.asList(leftAttack);
        }
        if (rightAttack != null) {
            return Arrays.asList(rightAttack);
        }

        return Collections.emptyList();
    }

    private void addSquaresToLists() {
        addMoveToSquares();

        List<Square> threatenedSquares = threatenedSquares();
        for (Square threatenedSquare : threatenedSquares) {
            Piece piece = chessBoard.pieceAt(threatenedSquare);
            Color opponentColor = viewColor.opponentColor();

            if (piece != null && piece.color().equals(opponentColor)) {
                squaresHoldingPiecesAttacked.add(threatenedSquare);
            } else {
                squaresHoldingPiecesDefended.add(threatenedSquare);
            }
        }
    }

    private void addMoveToSquares() {
        Square forwardStep = viewPoint.neighbor(pawnDirection);
        if (chessBoard.pieceAt(forwardStep) == null) {
            moveToSquares.add(forwardStep);

            Piece thisPawn = chessBoard.pieceAt(viewPoint);
            if (thisPawn.homeSquare().equals(viewPoint)) {
                Square nextStep = forwardStep.neighbor(pawnDirection);
                if (chessBoard.pieceAt(nextStep) == null) {
                    moveToSquares.add(nextStep);
                }
            }
        }
    }

    private ViewDirection[] pawnAttacks() {
        if (ViewDirection.UP.equals(pawnDirection)) {
            return UP_ATTACKS;
        } else {
            return DOWN_ATTACKS;
        }
    }

}