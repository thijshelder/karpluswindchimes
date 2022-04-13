package th.prog.inout;

import th.prog.things.Instrument;
import th.prog.things.OscillatorModel;

import javax.sound.sampled.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Player {

    AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100.0f, 8, 2, 2, 44100.0f, false);
    SourceDataLine line;

    public void initiatePlayer() {
        try {
            Mixer.Info[] mixInfo = AudioSystem.getMixerInfo();
            for (Mixer.Info info : mixInfo) {
                System.out.println(" Mixer description :" + info.getDescription() + "\n");
                System.out.println(" Mixer name        :" + info.getName() + "\n");
                System.out.println(" Mixer vendor      :" + info.getVendor() + "\n");
                System.out.println(" Mixer version     :" + info.getVersion() + "\n");
                System.out.println("------------------------------------------------");

            }
            Mixer mixer = AudioSystem.getMixer(mixInfo[mixInfo.length- 1]);
            Line.Info[] lineinfo = mixer.getSourceLineInfo();
         // for (Line.Info info : lineinfo) {
         //   if(info instanceof Line.Info)
         //     {
         //         Arrays.stream(((DataLine.Info) info).getFormats()).iterator().forEachRemaining(f->

         //          {
         //             System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
         //             System.out.println("channels : "+ f.getChannels());
         //             System.out.println("encoding : "+f.getEncoding());
         //             System.out.println("framerate : "+ f.getFrameRate());
         //             System.out.println("frame size "+f.getFrameSize());
         //             System.out.println("sample rate :" + f.getSampleRate());
         //             System.out.println("sample size bits : "+ f.getSampleSizeInBits());
         //             System.out.println(" is big endian ?  : "+f.isBigEndian());
         //             System.out.println("---------------------------------------------------");
         //         });
         //     }
         // }
            line = AudioSystem.getSourceDataLine(format);
            line.open(format);
            line.start();
        } catch (Exception e) {
            throw new RuntimeException(" could not get that line baby");
        }
    }

    public void playAString(Instrument instrument, int i) {
                instrument.playStringByIndex(line, i);
      }

    public void muteString(Instrument instrument, int i)
    {
        instrument.muteStringByIndex(line, i);
    }


    public void playAnOscillator() {
        try {
            line.open(format);
            line.start();
            OscillatorModel snaar = new OscillatorModel(440);
            snaar.pluck();
            ByteBuffer buffer = ByteBuffer.allocate(4);
            byte s = 0;
            while (!snaar.isSilent(s))
            {
                s = snaar.tic();

                buffer.put(s);
                if (!buffer.hasRemaining()) {
                    line.write(buffer.array(), 0, 4);
                    buffer.clear();
                }
            }
            line.drain();
            line.stop();
            line.close();
            line = null;
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public byte[] floatToByteArray(float f) {
        return ByteBuffer.allocate(4).putFloat(f).array();
    }

    public void closeLines()
    {
        line.drain();
        line.stop();
        line.close();
        line = null;
    }


}
