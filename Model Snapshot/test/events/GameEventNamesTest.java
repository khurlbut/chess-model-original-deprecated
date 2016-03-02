package events;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import onceagain.ChessPiece;
import onceagain.Piece;
import onceagain.board.Square;
import onceagain.enums.Color;
import onceagain.enums.Column;
import onceagain.enums.Rank;
import onceagain.enums.Row;

import org.junit.Test;

public class GameEventNamesTest {

    private PutEvent putEvent;
    private RemoveEvent removeEvent;
    private MoveEvent moveEvent;
    private CaptureEvent captureEvent;
    private Square a_1 = new Square(Column.A, Row.R1);
    private Piece piece_a_1;
    private Square a_2 = new Square(Column.A, Row.R2);

    @Test
    public void putEvent_name_format_is_as_follows() {
        piece_a_1 = ChessPiece.newPiece(Rank.Queen, Color.BLACK);

        putEvent = new PutEvent(piece_a_1, a_1);
        assertThat(putEvent.toString(), equalTo("put b Queen A_1"));
    }

    @Test
    public void removeEvent_name_format_is_as_follows() {
        removeEvent = new RemoveEvent(a_1);
        assertThat(removeEvent.toString(), equalTo("remove A_1"));
    }

    @Test
    public void moveEvent_name_format_is_as_follows() {
        moveEvent = new MoveEvent(a_1, a_2);
        assertThat(moveEvent.toString(), equalTo("A_1 --> A_2"));
    }

    @Test
    public void captureEvent_name_format_is_as_follows() {
        captureEvent = new CaptureEvent(a_1, a_2);
        assertThat(captureEvent.toString(), equalTo("A_1 x A_2"));
    }

}
