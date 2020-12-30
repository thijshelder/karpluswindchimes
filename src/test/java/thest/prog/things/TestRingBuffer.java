package thest.prog.things;

import org.junit.Assert;
import org.junit.Test;
import th.prog.things.RingBuffer;

public class TestRingBuffer {

    @Test
    public void testConstructors()
    {
        RingBuffer<Double> buffer = new RingBuffer(Double.class, 10);
        Assert.assertTrue(buffer.size()==0);
    }

    @Test
    public void testEnqueue()
    {
        double value= 2.34;
        RingBuffer<Double> buffer = new RingBuffer(Double.class, 10);
        buffer.enqueue(2.34);
        Assert.assertTrue(buffer.dequeue() == 2.34);
    }

    @Test
    public void testMultipleEnqueueDequeue()
    {
        RingBuffer<Double> buffer = new RingBuffer(Double.class, 10);
        double[] values = new double[] {2.3, 3.5, 5.3, 6.6, 3.5, 3.5, 1.3 ,  5.2, 4.4, 8.9};
        for (double d:values)
        {
            buffer.enqueue(d);
        }
        double dq1 = buffer.dequeue();
        double dq2 = buffer.dequeue();
        Assert.assertTrue(dq1 == values[0] && dq2 == values[1]);
    }

    @Test
    public void testSize()
    {
        RingBuffer<Double> buffer = new RingBuffer(Double.class, 10);
        double[] values = new double[] {2.3, 3.5, 5.3, 6.6, 3.5, 3.5, 1.3 ,  5.2, 4.4, 8.9};
        for (double d:values)
        {
            buffer.enqueue(d);
        }
        double dq1 = buffer.dequeue();
        double dq2 = buffer.dequeue();

        Assert.assertEquals(buffer.size(),8);
    }

    @Test
    public void testIsempty()
    {
        RingBuffer<Double> buffer = new RingBuffer(Double.class, 10);
        Assert.assertTrue("buffer does not know it is empty ", buffer.isEmpty());
    }

    @Test
    public void testIsFull()
    {
        RingBuffer<Double> buffer = new RingBuffer(Double.class, 10);
        double[] values = new double[] {2.3, 3.5, 5.3, 6.6, 3.5, 3.5, 1.3 ,  5.2, 4.4, 8.9};
        for (double d:values)
        {
            buffer.enqueue(d);
        }
        Assert.assertTrue("buffer does not know it is full ", buffer.isFull());
    }

    @Test
    public void testPeek()
    {
        RingBuffer<Double> buffer = new RingBuffer(Double.class, 10);
        double[] values = new double[] {2.3, 3.5, 5.3, 6.6, 3.5, 3.5, 1.3 ,  5.2, 4.4, 8.9};
        for (double d:values)
        {
            buffer.enqueue(d);
        }
        double dq1 = buffer.peek();
        double dq2 = buffer.peek();
        Assert.assertTrue(dq1==dq2&& dq1 ==2.3);
        Assert.assertEquals(buffer.size(),10);
    }



}
