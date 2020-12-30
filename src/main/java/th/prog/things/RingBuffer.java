package th.prog.things;

import java.lang.reflect.Array;

public class RingBuffer<T> {
    T[] buffer;
    int first;
    int last;
    int cycle;
    int capacity;

    /**
     * Initiate a RingBuffer object using RingBuffer(T.class, capacity
     *
     * @param c        is de actual type of T used as 'T.class' - if T is a Double for exanple, c is declared as 'Double.class'.
     * @param capacity is the desired size of the Ringbuffer, as an int.
     */
    public RingBuffer(Class<T> c, int capacity) {
        this.capacity = capacity;
        buffer = (T[]) Array.newInstance(c, capacity);
    }

    public void enqueue(T value) {
        buffer[last] = value;
        last++;
        if (last == buffer.length) {
            last = 0;
            cycle++;
        }
    }

    public T dequeue() {
        T value = buffer[first];
        buffer[first] = null;
        first++;
        if (first == buffer.length) {
            first = 0;
            if (cycle > 0) {
                cycle--;
            }
        }
        return value;
    }

    public T peek() {
        return buffer[first];
    }

    public boolean isEmpty() {
        return (last - first) == 0;
    }

    public T[] dump()
    {
        return buffer;
    }

    public boolean isFull()
    {
        return size() == buffer.length;
    }

    public int size()
    {
        return last - first + (cycle*buffer.length);
    }

    public int getCapacity()
    {
        return capacity;
    }
}
