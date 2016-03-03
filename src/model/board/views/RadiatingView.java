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

public class RadiatingView implements RankView {

    private final Color viewColor;
    private final Square viewPoint;
    private final ViewDistance viewDistance;
    private final ViewDirection[] viewDirections;

    private final ChessBoard chessBoard;

    private final List<Square> moveToSquares;
    private final List<Square> squaresHoldingPiecesAttacked;
    private final List<Square> squaresHoldingPiecesDefended;

    public RadiatingView(BoardPosition boardPosition, Color viewColor, ViewDirection[] viewDirections) {
        this(boardPosition, viewColor, viewDirections, ViewDistance.EDGE_OF_BOARD);
    }

    public RadiatingView(BoardPosition boardPosition, Color viewColor, ViewDirection[] viewDirections,
        ViewDistance viewDistance) {

        if (boardPosition == null || viewColor == null || viewDirections == null || viewDistance == null) {
            throw new IllegalArgumentException("Constructor does not allow null(s)!");
        }

        this.viewPoint = boardPosition.square();
        this.viewDistance = viewDistance;
        this.viewDirections = viewDirections;

        this.viewColor = viewColor;
        this.chessBoard = boardPosition.chessBoard();

        this.moveToSquares = new ArrayList<Square>();
        this.squaresHoldingPiecesDefended = new ArrayList<Square>();
        this.squaresHoldingPiecesAttacked = new ArrayList<Square>();

        addSquaresToLists();
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
        return moveToSquares;
    }

    private void addSquaresToLists() {

        for (ViewDirection viewDirection : viewDirections) {
            Square nextSquare = viewPoint.neighbor(viewDirection);

            while (nextSquare != null) {
                Piece pieceAtNextSquare = chessBoard.pieceAt(nextSquare);
                if (null != pieceAtNextSquare) {
                    if (isCollaborator(pieceAtNextSquare)) {
                        squaresHoldingPiecesDefended.add(nextSquare);
                    } else {
                        squaresHoldingPiecesAttacked.add(nextSquare);
                    }
                    break;
                }
                moveToSquares.add(nextSquare);
                if (viewDistance.equals(ViewDistance.SINGLE_UNIT)) {
                    break;
                }
                nextSquare = nextSquare.neighbor(viewDirection);
            }

        }

    }

    private boolean isCollaborator(Piece pieceAtNextSquare) {
        return viewColor.equals(pieceAtNextSquare.color());
    }

}