package th.prog.start;

import th.prog.composer.Scaletor;
import th.prog.inout.Player;
import th.prog.things.Instrument;

public class Main {

    public static void main(String[] args)
    {
        //KeyBoard board = new KeyBoard();
        Player player = new Player();
        player.initiatePlayer();
        Instrument instrument = new Instrument(new Scaletor().createChromaticScale(220.0d, 2));
        for(int i =0;i<instrument.getNumberOfStrings();i++){
            player.playAString(instrument, i);
        }

    }

}
