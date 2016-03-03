package model.board;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import model.enums.Color;
import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;
import model.piece.Piece;
import model.piece.PieceFactory;

import org.junit.Before;
import org.junit.Test;

public class BoardSetterTest {
    private Square a_1 = new Square(Column.A, Row.R1);
    private Square b_1 = new Square(Column.B, Row.R1);
    private Square c_1 = new Square(Column.C, Row.R1);
    private Square d_1 = new Square(Column.D, Row.R1);
    private Square e_1 = new Square(Column.E, Row.R1);
    private Square f_1 = new Square(Column.F, Row.R1);
    private Square g_1 = new Square(Column.G, Row.R1);
    private Square h_1 = new Square(Column.H, Row.R1);

    private Square a_2 = new Square(Column.A, Row.R2);
    private Square b_2 = new Square(Column.B, Row.R2);
    private Square c_2 = new Square(Column.C, Row.R2);
    private Square d_2 = new Square(Column.D, Row.R2);
    private Square e_2 = new Square(Column.E, Row.R2);
    private Square f_2 = new Square(Column.F, Row.R2);
    private Square g_2 = new Square(Column.G, Row.R2);
    private Square h_2 = new Square(Column.H, Row.R2);

    private Square a_7 = new Square(Column.A, Row.R7);
    private Square b_7 = new Square(Column.B, Row.R7);
    private Square c_7 = new Square(Column.C, Row.R7);
    private Square d_7 = new Square(Column.D, Row.R7);
    private Square e_7 = new Square(Column.E, Row.R7);
    private Square f_7 = new Square(Column.F, Row.R7);
    private Square g_7 = new Square(Column.G, Row.R7);
    private Square h_7 = new Square(Column.H, Row.R7);

    private Square a_8 = new Square(Column.A, Row.R8);
    private Square b_8 = new Square(Column.B, Row.R8);
    private Square c_8 = new Square(Column.C, Row.R8);
    private Square d_8 = new Square(Column.D, Row.R8);
    private Square e_8 = new Square(Column.E, Row.R8);
    private Square f_8 = new Square(Column.F, Row.R8);
    private Square g_8 = new Square(Column.G, Row.R8);
    private Square h_8 = new Square(Column.H, Row.R8);

    private Piece w_pawn_a_2 = PieceFactory.newPiece(Rank.Pawn, Color.WHITE, a_2);
    private Piece w_pawn_b_2 = PieceFactory.newPiece(Rank.Pawn, Color.WHITE, b_2);
    private Piece w_pawn_c_2 = PieceFactory.newPiece(Rank.Pawn, Color.WHITE, c_2);
    private Piece w_pawn_d_2 = PieceFactory.newPiece(Rank.Pawn, Color.WHITE, d_2);
    private Piece w_pawn_e_2 = PieceFactory.newPiece(Rank.Pawn, Color.WHITE, e_2);
    private Piece w_pawn_f_2 = PieceFactory.newPiece(Rank.Pawn, Color.WHITE, f_2);
    private Piece w_pawn_g_2 = PieceFactory.newPiece(Rank.Pawn, Color.WHITE, g_2);
    private Piece w_pawn_h_2 = PieceFactory.newPiece(Rank.Pawn, Color.WHITE, h_2);

    private Piece b_pawn_a_7 = PieceFactory.newPiece(Rank.Pawn, Color.BLACK, a_7);
    private Piece b_pawn_b_7 = PieceFactory.newPiece(Rank.Pawn, Color.BLACK, b_7);
    private Piece b_pawn_c_7 = PieceFactory.newPiece(Rank.Pawn, Color.BLACK, c_7);
    private Piece b_pawn_d_7 = PieceFactory.newPiece(Rank.Pawn, Color.BLACK, d_7);
    private Piece b_pawn_e_7 = PieceFactory.newPiece(Rank.Pawn, Color.BLACK, e_7);
    private Piece b_pawn_f_7 = PieceFactory.newPiece(Rank.Pawn, Color.BLACK, f_7);
    private Piece b_pawn_g_7 = PieceFactory.newPiece(Rank.Pawn, Color.BLACK, g_7);
    private Piece b_pawn_h_7 = PieceFactory.newPiece(Rank.Pawn, Color.BLACK, h_7);

    private Piece w_rook_a_1 = PieceFactory.newPiece(Rank.Rook, Color.WHITE, a_1);
    private Piece w_rook_h_1 = PieceFactory.newPiece(Rank.Rook, Color.WHITE, h_1);
    private Piece w_knight_b_1 = PieceFactory.newPiece(Rank.Knight, Color.WHITE, b_1);
    private Piece w_knight_g_1 = PieceFactory.newPiece(Rank.Knight, Color.WHITE, g_1);
    private Piece w_bishop_c_1 = PieceFactory.newPiece(Rank.Bishop, Color.WHITE, c_1);
    private Piece w_bishop_f_1 = PieceFactory.newPiece(Rank.Bishop, Color.WHITE, f_1);
    private Piece w_queen = PieceFactory.newPiece(Rank.Queen, Color.WHITE, d_1);
    private Piece w_king = PieceFactory.newPiece(Rank.King, Color.WHITE, e_1);

    private Piece b_rook_a_8 = PieceFactory.newPiece(Rank.Rook, Color.BLACK, a_8);
    private Piece b_rook_h_8 = PieceFactory.newPiece(Rank.Rook, Color.BLACK, h_8);
    private Piece b_knight_b_8 = PieceFactory.newPiece(Rank.Knight, Color.BLACK, b_8);
    private Piece b_knight_g_8 = PieceFactory.newPiece(Rank.Knight, Color.BLACK, g_8);
    private Piece b_bishop_c_8 = PieceFactory.newPiece(Rank.Bishop, Color.BLACK, c_8);
    private Piece b_bishop_f_8 = PieceFactory.newPiece(Rank.Bishop, Color.BLACK, f_8);
    private Piece b_queen = PieceFactory.newPiece(Rank.Queen, Color.BLACK, d_8);
    private Piece b_king = PieceFactory.newPiece(Rank.King, Color.BLACK, e_8);

    private BoardSetter boardSetter;

    @Before
    public void setUp() {
        boardSetter = new BoardSetter();
    }

    @Test
    public void all_the_pieces_are_in_the_correct_places() {
        ChessBoard chessBoard = boardSetter.setBoard();

        assertWhitePawnsInPlace(chessBoard);
        assertBlackPawnsInPlace(chessBoard);
        assertWhitePiecesInPlace(chessBoard);
        assertBlackPiecesInPlace(chessBoard);
    }

    private void assertWhitePawnsInPlace(ChessBoard board) {
        assertThat(board.pieceAt(a_2), equalTo(w_pawn_a_2));
        assertThat(board.pieceAt(b_2), equalTo(w_pawn_b_2));
        assertThat(board.pieceAt(c_2), equalTo(w_pawn_c_2));
        assertThat(board.pieceAt(d_2), equalTo(w_pawn_d_2));
        assertThat(board.pieceAt(e_2), equalTo(w_pawn_e_2));
        assertThat(board.pieceAt(f_2), equalTo(w_pawn_f_2));
        assertThat(board.pieceAt(g_2), equalTo(w_pawn_g_2));
        assertThat(board.pieceAt(h_2), equalTo(w_pawn_h_2));
    }

    private void assertBlackPawnsInPlace(ChessBoard board) {
        assertThat(board.pieceAt(a_7), equalTo(b_pawn_a_7));
        assertThat(board.pieceAt(b_7), equalTo(b_pawn_b_7));
        assertThat(board.pieceAt(c_7), equalTo(b_pawn_c_7));
        assertThat(board.pieceAt(d_7), equalTo(b_pawn_d_7));
        assertThat(board.pieceAt(e_7), equalTo(b_pawn_e_7));
        assertThat(board.pieceAt(f_7), equalTo(b_pawn_f_7));
        assertThat(board.pieceAt(g_7), equalTo(b_pawn_g_7));
        assertThat(board.pieceAt(h_7), equalTo(b_pawn_h_7));
    }

    private void assertWhitePiecesInPlace(ChessBoard board) {
        assertThat(board.pieceAt(a_1), equalTo(w_rook_a_1));
        assertThat(board.pieceAt(b_1), equalTo(w_knight_b_1));
        assertThat(board.pieceAt(c_1), equalTo(w_bishop_c_1));
        assertThat(board.pieceAt(d_1), equalTo(w_queen));
        assertThat(board.pieceAt(e_1), equalTo(w_king));
        assertThat(board.pieceAt(f_1), equalTo(w_bishop_f_1));
        assertThat(board.pieceAt(g_1), equalTo(w_knight_g_1));
        assertThat(board.pieceAt(h_1), equalTo(w_rook_h_1));
    }

    private void assertBlackPiecesInPlace(ChessBoard board) {
        assertThat(board.pieceAt(a_8), equalTo(b_rook_a_8));
        assertThat(board.pieceAt(b_8), equalTo(b_knight_b_8));
        assertThat(board.pieceAt(c_8), equalTo(b_bishop_c_8));
        assertThat(board.pieceAt(d_8), equalTo(b_queen));
        assertThat(board.pieceAt(e_8), equalTo(b_king));
        assertThat(board.pieceAt(f_8), equalTo(b_bishop_f_8));
        assertThat(board.pieceAt(g_8), equalTo(b_knight_g_8));
        assertThat(board.pieceAt(h_8), equalTo(b_rook_h_8));
    }


}