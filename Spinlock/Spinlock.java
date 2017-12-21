
public class Spinlock {

    public static void main(String[] args) {
        CircularBuffer<Integer> spinlock = new CircularBuffer<>(0);
        int step = 382;
        // step from 1 to 2017
        for(int i = 1; i < 2018; i++) {
            for(int j = 0; j < step; j++) {
                spinlock.next();
            }
            spinlock.add(i);
            // print to check operation
            // System.out.println(spinlock.toString());
        }
        System.out.println(spinlock.getNext());
    }
}


