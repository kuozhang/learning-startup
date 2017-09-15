package misc;

public class ThreadLocalTest extends Thread {

    private int i;

    private ThreadLocalTest(int i){
        this.i = i;
    }

    private static ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
    private static ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

    public void run(){
        threadLocal1.set(i);
        threadLocal2.set(i + 1);
        System.out.println("Thread - " + i + ": " + threadLocal1.get());
        System.out.println("Thread - " + i + ": " + threadLocal2.get());
    }

    public static void main(String[] args){
        for (int i = 0 ; i < 3 ; i++ ) {
            new ThreadLocalTest(i).start();
        }
    }
}
