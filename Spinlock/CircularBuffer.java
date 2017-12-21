import java.util.ArrayList;
import java.util.LinkedList;

public class CircularBuffer<E> {
    private LinkedList<E> data;
    private int currentPosition;

    public CircularBuffer(E initialValue) {
        data = new LinkedList<>();
        data.add(initialValue);
        currentPosition = 0;
    }

    public void add(E element) {
        // increments size of data, adds element, updates position
        data.add(currentPosition + 1, element);
        updatePosition();
    }

    public E next() {
        updatePosition();
        return data.get(currentPosition);
    }

    @Override
    public String toString() {
        String result = "";
        for (E e: data) {
            result += e + " ";
        }
        return result.substring(0, result.length() - 1);
    }

    public E getNext() {
        // get the next data element, one past current position
        return data.get((currentPosition + 1) % data.size());

    }

    private void updatePosition() {
        currentPosition = (currentPosition + 1) % data.size();
    }
}
