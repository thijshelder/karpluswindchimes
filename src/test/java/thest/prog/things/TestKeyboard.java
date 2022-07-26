package thest.prog.things;

import org.junit.Test;
import th.prog.composer.Scaletor;
import th.prog.things.Instrument;
import th.prog.things.KeyBoard;

import java.io.IOException;

public class TestKeyboard {

    @Test
    public void testKeyBoardInitial() {
        KeyBoard board = new KeyBoard(new Instrument(new Scaletor().createChinesePentatonicScale(220.0d, 440.0d, 2)));
        //board.
    }
}
