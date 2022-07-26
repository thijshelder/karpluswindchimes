package th.prog.things;

import java.util.Random;

public class StringModel {
    RingBuffer<Byte> vibratingString;
    int size;
    double dampFactor = 1.0;
    int times;
    int cycles = 0;
    int fadeTimes;
    boolean isActive = true;
    Byte[] memory;
    double frequency;

    public StringModel(int size) {
        this.size = size;
        vibratingString = new RingBuffer<>(Byte.class, size);
    }

    public StringModel(int sampleRate, double frequency, double dampFactor) {
        this.frequency = frequency;
        this.size = (int) Math.round(8 * sampleRate / frequency);
        System.out.println("zise is :" + size);
        this.dampFactor = dampFactor;
        vibratingString = new RingBuffer<>(Byte.class, size);
    }

    public StringModel(int size, Float dampFactor) {
        this.size = size;
        this.dampFactor = dampFactor;
        vibratingString = new RingBuffer<>(Byte.class, size);
    }

    public StringModel(Byte[] init) {
        vibratingString = new RingBuffer<>(Byte.class, init.length);
        for (Byte d : init) {
            vibratingString.enqueue(d);
        }
    }

    public void pluck() {
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            byte s = (byte) ((byte) (Byte.MAX_VALUE * Math.sin((i * Math.PI) / 3 * vibratingString.getCapacity())) - (0.5 - r.nextGaussian() * 0.4 * (Byte.MAX_VALUE)));
            vibratingString.enqueue(s);
        }
        memory = vibratingString.dump();
    }

    public byte tic() {
        byte retValue = vibratingString. dequeue();
        byte newValue = (byte) ((byte) ((retValue + vibratingString.peek()) / 2));
        vibratingString.enqueue(newValue);
        times++;
        isActive = !isSilent(retValue);
        if (times % vibratingString.capacity == 0) {
            cycles++;
        }
        if (times%2==0) {
            return retValue;
        } else {
            return (byte) ((byte) 0 - retValue);
        }
    }

    public void setInactive() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive() {
        isActive = true;
    }

    public double getFrequency() {
        return frequency;
    }

    public void mute() {
        isActive = true;
        for (int i = 0; i < size; i++) {
            vibratingString.enqueue((byte) 0);
        }
    }

    public int getCycles() {
        return cycles;
    }

    private boolean isSilent(byte presentValue) {
        boolean silent = Math.abs(vibratingString.peek() - presentValue) == 0;
        if (silent) {
            fadeTimes++;
        } else if (fadeTimes > 2) {
            fadeTimes--;
        }
        return fadeTimes > vibratingString.getCapacity() && silent;

    }


    public int getTimes() {
        return times;
    }
}
