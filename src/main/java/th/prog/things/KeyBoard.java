package th.prog.things;

import th.prog.inout.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class KeyBoard implements KeyListener, WindowListener {

    Instrument instrument = new Instrument(440.0d, 330.0d, 660.0d, 880.0d);
    Player player = new Player();

    public KeyBoard(Instrument instrument)
    {
        player.initiatePlayer();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JTextField field = new JTextField();
        field.addKeyListener(this);
        panel.add(field);
        frame.add(panel);
        frame.setSize(440,400);
        frame.setVisible(true);

    }



    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        boolean sigue = true;
        if (key == 'a')
            while (sigue)
            {
                    player.muteString(instrument, 0);
                    player.playAString(instrument, 0);
                    sigue = false;
            }
        else if (key == 's')
            while (sigue)
            {
                player.playAString(instrument, 1);
                sigue = false;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        if(key=='a')
        {
            player.muteString(instrument, 0);

        }
        else if(key == 's')
        {
            player.muteString(instrument, 1);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        player.closeLines();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
