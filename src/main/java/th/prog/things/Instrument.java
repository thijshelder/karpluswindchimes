package th.prog.things;

import javax.sound.sampled.Line;
import javax.sound.sampled.SourceDataLine;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.IntStream;

public class Instrument {
// make being active a question of being discarded yet.
    // create some mechanism to rest
    //create an automated playing mechanism
    // create a way to convert random data to useful musicStrings

    List<StringModel> snaren = new ArrayList<>();
    final int SAMPLERATE = 44100;

    public Instrument(Double... args) {
        Arrays.stream(args).forEach(v -> {
            snaren.add(new StringModel(SAMPLERATE, v, 1 - 0.00000000000001 / v));
        });
    }

    public void playStringByIndex(SourceDataLine line, int index) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        StringModel resonatingString = snaren.get(index);
        snaren.get(index).pluck();
        while (snaren.get(index).isActive()) {
            byte s = (byte) snaren.get(index).tic();
            buffer.put(s);
            if (!buffer.hasRemaining()) {
                line.write(buffer.array(), 0, 8);
                buffer.clear();
            }
        }
    }

    public void playStringWithReverb(SourceDataLine line, int index) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        StringModel resonatingString = snaren.get(index);
        StringModel resonator;
        resonatingString.pluck();
        resonator = new StringModel(resonatingString.memory);
        //resonators.add(new StringModel(resonatingString.memory));
        while (resonatingString.isActive()|| resonator.isActive()) {
            byte s = resonatingString.tic();
            if(resonatingString.cycles>4)
            {
               s = (byte) (s -  (byte)( resonator.getTimes()*0.00005)* resonator.tic());
            }
            buffer.put(s);
            if (!buffer.hasRemaining()) {
                line.write(buffer.array(), 0, 8);
                buffer.clear();
            }
        }

    }

    public void playMultipleStringsByIndices(SourceDataLine line, int... indices) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        List<StringModel> filaments = new ArrayList<>();
        Arrays.stream(indices).forEach(i -> filaments.add(snaren.get(i)));
        filaments.forEach(f -> f.pluck());
        while (filaments.get(0).isActive()) {
            buffer.put(filaments.stream().map(f -> (int) f.tic()).reduce(0, Integer::sum).byteValue());
            if (!buffer.hasRemaining()) {
                line.write(buffer.array(), 0, 8);
                buffer.clear();
            }
        }
    }

    // public void addReverb

    public int getNumberOfStrings() {
        return snaren.size();
    }

    public void muteStringByIndex(SourceDataLine line, int index) {
        snaren.get(index).setInactive();
        snaren.get(index).mute();
    }

    public void makeItMelodic() {
    }

    public void playOnKeyBoard() {
    }

}