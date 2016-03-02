package onceagain.board;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import onceagain.Piece;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BackingMapTest {

    @Mock
    private Square square1;
    @Mock
    private Square square2;
    @Mock
    private Piece piece1;
    @Mock
    private Piece piece2;

    private BackingMap backingMap;

    @Before
    public void setUp() {
        backingMap = new BackingMap();
    }

    @Test
    public void it_returns_null_when_for_an_empty_square() {
        assertNull(backingMap.getPieceAt(square1));
    }

    @Test
    public void it_returns_the_piece_set_on_a_square() {
        backingMap = backingMap.put(square1, piece1);
        assertThat(backingMap.getPieceAt(square1), equalTo(piece1));
    }

    @Test
    public void it_remembers_previous_pieces_when_a_new_piece_is_set() {
        backingMap = backingMap.put(square1, piece1);
        backingMap = backingMap.put(square2, piece2);

        assertThat(backingMap.getPieceAt(square1), equalTo(piece1));
        assertThat(backingMap.getPieceAt(square2), equalTo(piece2));
    }

    @Test
    public void it_can_move_a_piece_from_square1_to_square2() {
        backingMap = backingMap.put(square1, piece1);
        backingMap = backingMap.move(square1, square2);

        assertNull(backingMap.getPieceAt(square1));
        assertThat(backingMap.getPieceAt(square2), equalTo(piece1));
    }

    @Test
    public void it_can_replace_the_piece_on_square2_with_the_piece_on_square1() {
        backingMap = backingMap.put(square1, piece1);
        backingMap = backingMap.put(square2, piece2);

        backingMap = backingMap.replace(square1, square2);

        assertNull(backingMap.getPieceAt(square1));
        assertThat(backingMap.getPieceAt(square2), equalTo(piece1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException_is_thrown_when_attempting_to_set_a_piece_on_a_square_that_is_occupied_by_a_new_piece() {
        backingMap = backingMap.put(square1, piece1);
        backingMap = backingMap.put(square1, piece2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException_is_thrown_when_attempting_to_set_a_piece_on_a_square_that_is_occupied_by_the_same_piece() {
        backingMap = backingMap.put(square1, piece1);
        backingMap = backingMap.put(square1, piece1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException_is_thrown_when_an_attemp_is_made_to_move_to_an_occupied_square() {
        backingMap = backingMap.put(square1, piece1);
        backingMap = backingMap.put(square2, piece2);
        backingMap = backingMap.move(square1, square2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException_is_thrown_when_an_attemp_is_made_to_a_piece_in_twice() {
        backingMap = backingMap.put(square1, piece1);
        backingMap = backingMap.put(square2, piece1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException_is_thrown_when_an_attempt_is_made_to_replace_on_an_empty_square() {
        backingMap = backingMap.put(square1, piece1);
        backingMap = backingMap.replace(square1, square2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException_is_thrown_when_an_attempt_is_made_to_move_from_an_empty_square() {
        backingMap = backingMap.move(square1, square2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException_is_thrown_when_an_attempt_is_made_to_replace_from_an_empty_square() {
        backingMap = backingMap.put(square2, piece1);
        backingMap = backingMap.replace(square1, square2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException_is_thrown_when_an_attempt_is_made_to_remove_from_an_empty_square() {
        backingMap = backingMap.remove(square1);
    }

}
