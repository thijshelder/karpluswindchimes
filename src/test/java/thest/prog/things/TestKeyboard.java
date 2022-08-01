package thest.prog.things;

import java.awt.Event;
import org.junit.Test;
import th.prog.things.KeyBoard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TestKeyboard {

    @Test
    public void testKeyBoardInitial()
    {
        KeyBoard board = new KeyBoard();
        while(true) {
            Thread t = new Thread(board);
            t.start();
        }

    }
}
