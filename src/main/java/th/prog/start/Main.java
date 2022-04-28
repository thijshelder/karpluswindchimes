package th.prog.start;

import th.prog.inout.Player;
import th.prog.things.Instrument;

public class Main {

    public static void main(String[] args)
    {
        //KeyBoard board = new KeyBoard();
        Player player = new Player();
        player.initiatePlayer();
        Instrument instrument = new Instrument(110.0d, 220.0d, 440.0d, 340.0d, 880.0d);
        player.playAString(instrument, 1);
    }

}
