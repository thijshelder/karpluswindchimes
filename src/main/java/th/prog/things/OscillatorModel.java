package th.prog.things;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class OscillatorModel
{
    RingBuffer<Byte> buffer;
    int pos =0;
    float damping = 0.003f;
    int fadeTimes = 0;

    public OscillatorModel(int frequency)
    {
        buffer = new RingBuffer<>(Byte.class, (int) Math.round(44100/frequency));
    }

    public void pluck()
    {
        for(int i =0;i< buffer.getCapacity();i++)
        {
            Byte b = (byte)  (Byte.MAX_VALUE* Math.sin((2*i*Math.PI)/ buffer.getCapacity()));
            buffer.enqueue(b);
        }
    }

    public byte tic()
    {
        byte retValue = buffer.dequeue();
        byte newValue = (byte )  (Byte.MAX_VALUE*(Math.pow(Math.E,(-damping*(1+(int)(pos/buffer.getCapacity()))))*Math.sin((2*pos*Math.PI)/ buffer.getCapacity())));
        if(newValue> 0 )
        {
            newValue --;
        }
        else if(newValue < 0)
        {
            newValue ++;
        }
        buffer.enqueue(newValue);
        pos++;
        return retValue;
    }

    public boolean isSilent(byte presentValue)
    {
        boolean silent = Math.abs(buffer.peek()) - Math.abs(presentValue)< 10;
        if(silent)
        {
            fadeTimes++;
        }
        else if(fadeTimes > 2)
        {
            fadeTimes--;
        }

        return silent && fadeTimes==(buffer.capacity)/2;

    }

    public byte[] toByteArray(double d) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos);
            dos.writeDouble(d);
            dos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("that went spectacularly wrong, look" + e.getMessage(), e.getCause());
        }
    }
}
