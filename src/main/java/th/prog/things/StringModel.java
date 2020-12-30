package th.prog.things;

import java.util.Random;

public class StringModel {
    RingBuffer<Byte> string;
    int size;
    double dampFactor = 1.0;
    int times;
    int cycles= 0;
    int fadeTimes;
    boolean isActive = true;
    Byte[] memory;
    double frequency;

    public StringModel(int size) {
        this.size = size;
        string = new RingBuffer<>(Byte.class, size);
    }

    public StringModel(int sampleRate, double frequency, double dampFactor) {
        this.frequency = frequency;
        this.size = (int) Math.round(8 * sampleRate / frequency);
        System.out.println("zise is :" + size);
        this.dampFactor = dampFactor;
        string = new RingBuffer<>(Byte.class, size);
    }

    public StringModel(int size, Float dampFactor) {
        this.size = size;
        this.dampFactor = dampFactor;
        string = new RingBuffer<>(Byte.class, size);
    }

    public StringModel(Byte[] init) {
        string = new RingBuffer<>(Byte.class, init.length);
        for (Byte d : init) {
            string.enqueue(d);
        }
    }

    public void pluck() {
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            byte s = (byte) ((byte)(Byte.MAX_VALUE* Math.sin((i*Math.PI)/ 3*string.getCapacity()))-(0.5-r.nextGaussian() * 0.4 * (Byte.MAX_VALUE)));;
            string.enqueue(s);
        }
        memory = string.dump();
    }

    public byte tic(boolean even) {
        byte retValue = string.dequeue();
        byte newValue = (byte) ((byte) ((retValue + string.peek()) / 2));
        string.enqueue(newValue);
        times++;
        isActive = !isSilent(retValue);
        if(times%string.capacity==0)
        {
            cycles++;
        }
        if(even) {

            return retValue;
        }
        else
        {
            return (byte) ((byte) 0 - retValue);
        }

    }

    public void setInactive()
    {
        isActive = false;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive()
    {
      isActive = true;
    }
    public double getFrequency()
    {
        return frequency;
    }

    public void mute()
    {
        isActive = true;
        for (int i=0;i<size;i++)
        {
            string.enqueue((byte)0);
        }
    }

    public int getCycles()
    {
        return cycles;
    }

    private boolean isSilent(byte presentValue) {
        boolean silent = Math.abs(string.peek()- presentValue) == 0;
        if (silent) {
            fadeTimes++;
        } else if (fadeTimes > 2) {
            fadeTimes--;
        }
        return fadeTimes > string.getCapacity() && silent;

    }


    public int getTimes() {
        return times;
    }
}
