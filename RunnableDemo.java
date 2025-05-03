class MyRunnable implements Runnable {
    @Override
    public void run() {
    for (int i = 1; i <= 5; i++) {
    System.out.println(Thread.currentThread().getName() + ": " + i);
    try {
    Thread.sleep(500);
    } catch (InterruptedException e) {
    System.out.println("Thread interrupted");
    }
    }
    }
    }
    public class RunnableDemo {
    public static void main(String[] args) {
    Thread thread1 = new Thread(new MyRunnable(), "Thread-A");
    Thread thread2 = new Thread(new MyRunnable(), "Thread-B");
    thread1.start();
    thread2.start();
    System.out.println("Main thread continues execution");
    }
    }