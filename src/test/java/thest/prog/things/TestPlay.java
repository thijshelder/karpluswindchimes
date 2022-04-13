package thest.prog.things;

import org.junit.Test;
import th.prog.inout.Player;
import th.prog.things.Instrument;

public class TestPlay {

    @Test
    public void tryToPlay()
    {
        Player player = new Player();
        player.initiatePlayer(); 
        player.playAString(new Instrument(110.0d, 220.0d, 340.0d, 440.0d, 880.0d, 1760.0d, 4400.0d), 1);
        //player.playAnOscillator();
    }
}
