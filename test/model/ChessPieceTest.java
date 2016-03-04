package model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import model.board.BoardSetter;
import model.board.CaptureEvent;
import model.board.ChessBoard;
import model.board.GameEvent;
import model.board.MoveEvent;
import model.board.PutEvent;
import model.board.Square;
import model.enums.Color;
import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;
import model.piece.Piece;
import model.piece.PieceFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChessPieceTest {

    private Square d_3 = new Square(Column.D, Row.R3);
    private Square d_4 = new Square(Column.D, Row.R4);
    private Square d_6 = new Square(Column.D, Row.R6);
    private Square c_2 = new Square(Column.C, Row.R2);
    private Square d_7 = new Square(Column.D, Row.R7);
    private Square e_2 = new Square(Column.E, Row.R2);
    private Square c_7 = new Square(Column.C, Row.R7);
    private Square e_7 = new Square(Column.E, Row.R7);
    private Square a_7 = new Square(Column.A, Row.R7);
    private Square g_7 = new Square(Column.G, Row.R7);
    private Square b_2 = new Square(Column.B, Row.R2);
    private Square d_2 = new Square(Column.D, Row.R2);
    private Square f_2 = new Square(Column.F, Row.R2);
    private Square c_3 = new Square(Column.C, Row.R3);
    private Square e_3 = new Square(Column.E, Row.R3);
    private Square a_4 = new Square(Column.A, Row.R4);
    private Square b_4 = new Square(Column.B, Row.R4);
    private Square c_4 = new Square(Column.C, Row.R4);
    private Square e_4 = new Square(Column.E, Row.R4);
    private Square f_4 = new Square(Column.F, Row.R4);
    private Square g_4 = new Square(Column.G, Row.R4);
    private Square h_4 = new Square(Column.H, Row.R4);
    private Square c_5 = new Square(Column.C, Row.R5);
    private Square b_6 = new Square(Column.B, Row.R6);
    private Square d_5 = new Square(Column.D, Row.R5);
    private Square e_5 = new Square(Column.E, Row.R5);
    private Square f_6 = new Square(Column.F, Row.R6);

    private Piece w_queen_d_3;
    private Piece w_queen_d_4;
    private Piece w_queen_d_6;
    private Piece b_queen_d_7;

    private PutEvent put_w_queen_d_3;
    private PutEvent put_w_queen_d_6;
    private PutEvent put_w_queen_d_4;
    private PutEvent put_b_queen_d_7;

    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        w_queen_d_3 = PieceFactory.newPiece(Color.WHITE, Rank.Queen, d_3);
        w_queen_d_4 = PieceFactory.newPiece(Color.WHITE, Rank.Queen, d_4);
        w_queen_d_6 = PieceFactory.newPiece(Color.WHITE, Rank.Queen, d_6);
        b_queen_d_7 = PieceFactory.newPiece(Color.BLACK, Rank.Queen, d_7);

        put_w_queen_d_4 = new PutEvent(w_queen_d_4);
        put_b_queen_d_7 = new PutEvent(b_queen_d_7);

        chessBoard = new ChessBoard();

        chessBoard = chessBoard.playEvent(put_w_queen_d_4);
        chessBoard = chessBoard.playEvent(put_b_queen_d_7);
        chessBoard = chessBoard.setBoardForGameInProgress();
    }

    @Test
    public void piece_name_format_is_as_follows() {
        assertThat(w_queen_d_4.toString(), equalTo("w Queen"));
    }

    @Test
    public void it_finds_all_potentialGameEvents() {
        chessBoard = new BoardSetter().setBoard();
        put_w_queen_d_4 = new PutEvent(w_queen_d_4);
        chessBoard = chessBoard.playEvent(put_w_queen_d_4);
        Square mySquare = chessBoard.squareHolding(w_queen_d_4);

        List<GameEvent> potentialGameEvents = w_queen_d_4.potentialGameEvents(chessBoard);

        assertThat(potentialGameEvents.size(), equalTo(19));

        Piece b_pawn_a_7 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, a_7);
        Piece b_pawn_d_7 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, a_7);
        Piece b_pawn_g_7 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, a_7);

        assertTrue(potentialGameEvents.contains(newCaptureEvent(mySquare, b_pawn_a_7)));
        assertTrue(potentialGameEvents.contains(newCaptureEvent(mySquare, b_pawn_d_7)));
        assertTrue(potentialGameEvents.contains(newCaptureEvent(mySquare, b_pawn_g_7)));

        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, c_3)));
        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, d_3)));
        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, e_3)));

        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, a_4)));
        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, b_4)));
        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, c_4)));
        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, e_4)));
        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, f_4)));
        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, g_4)));
        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, h_4)));

        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, c_5)));
        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, b_6)));

        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, d_5)));
        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, d_6)));

        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, e_5)));
        assertTrue(potentialGameEvents.contains(new MoveEvent(mySquare, f_6)));
    }

    @Test
    public void it_finds_only_legal_potentialGameEvents() {
        chessBoard = new BoardSetter().setBoard();
        put_w_queen_d_4 = new PutEvent(w_queen_d_4);
        chessBoard = chessBoard.playEvent(put_w_queen_d_4);

        List<GameEvent> potentialGameEvents = w_queen_d_4.potentialGameEvents(chessBoard);

        chessBoard = chessBoard.setBoardForGameInProgress();
        for (GameEvent gameEvent : potentialGameEvents) {
            chessBoard.playEvent(gameEvent);
        }
    }

    private CaptureEvent newCaptureEvent(Square mySquare, Piece targetPiece) {
        Square targetSquare = chessBoard.squareHolding(targetPiece);
        CaptureEvent captureEvent = new CaptureEvent(mySquare, targetSquare, targetPiece);
        return captureEvent;
    }

    @Test
    public void it_finds_piecesAttacked() {
        chessBoard = new BoardSetter().setBoard();
        put_w_queen_d_4 = new PutEvent(w_queen_d_4);
        chessBoard = chessBoard.playEvent(put_w_queen_d_4);

        List<Piece> capturePieces = w_queen_d_4.piecesAttacked(chessBoard);

        assertThat(capturePieces.size(), equalTo(3));
        assertTrue(capturePieces.contains(PieceFactory.newPiece(Color.BLACK, Rank.Pawn, a_7)));
        assertTrue(capturePieces.contains(PieceFactory.newPiece(Color.BLACK, Rank.Pawn, d_7)));
        assertTrue(capturePieces.contains(PieceFactory.newPiece(Color.BLACK, Rank.Pawn, g_7)));
    }

    @Test
    public void it_finds_defendedPieces() {
        chessBoard = new BoardSetter().setBoard();
        put_w_queen_d_4 = new PutEvent(w_queen_d_4);
        chessBoard = chessBoard.playEvent(put_w_queen_d_4);

        List<Piece> capturePieces = w_queen_d_4.piecesDefended(chessBoard);

        assertThat(capturePieces.size(), equalTo(3));
        assertTrue(capturePieces.contains(PieceFactory.newPiece(Color.WHITE, Rank.Pawn, b_2)));
        assertTrue(capturePieces.contains(PieceFactory.newPiece(Color.WHITE, Rank.Pawn, d_2)));
        assertTrue(capturePieces.contains(PieceFactory.newPiece(Color.WHITE, Rank.Pawn, f_2)));
    }

    @Test
    public void it_finds_attackingPieces() {
        chessBoard = new BoardSetter().setBoard();
        put_w_queen_d_6 = new PutEvent(w_queen_d_6);
        chessBoard = chessBoard.playEvent(put_w_queen_d_6);

        List<Piece> attackingPieces = w_queen_d_6.opponentAttackers(chessBoard);

        assertThat(attackingPieces.size(), equalTo(2));
        assertTrue(attackingPieces.contains(PieceFactory.newPiece(Color.BLACK, Rank.Pawn, c_7)));
        assertTrue(attackingPieces.contains(PieceFactory.newPiece(Color.BLACK, Rank.Pawn, e_7)));
    }

    @Test
    public void it_finds_supportingPieces() {
        chessBoard = new BoardSetter().setBoard();
        put_w_queen_d_3 = new PutEvent(w_queen_d_3);
        chessBoard = chessBoard.playEvent(put_w_queen_d_3);

        List<Piece> supportingPieces = w_queen_d_3.collaboratorDefenders(chessBoard);

        assertThat(supportingPieces.size(), equalTo(2));
        assertTrue(supportingPieces.contains(PieceFactory.newPiece(Color.WHITE, Rank.Pawn, c_2)));
        assertTrue(supportingPieces.contains(PieceFactory.newPiece(Color.WHITE, Rank.Pawn, e_2)));
    }

}
