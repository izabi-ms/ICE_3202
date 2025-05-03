public class T {
    public static void main(String[] a) throws Exception {
    new Thread(() -> t("YieldThread", 1, 0)).start();
    new Thread(() -> t("SleepThread", 0, 1)).start();
    Thread s = new Thread(() -> t("StopThread", 0, 0));
    s.start();
    s.join(1500);
    System.out.println("\nDone");
    }
    static void t(String n, int y, int s) {
    for (int i = 1; i <= 5; i++) {
    System.out.println(n + " " + i);
    if (y > 0) {
    Thread.yield();
    System.out.println(n + " yielded");
    }
    if (s > 0)
    try {
    Thread.sleep(1000);
    } catch (Exception e) {
    }
    if (n.equals("StopThread") && i == 3) {
    System.out.println(n + " stopped");
    return;
    }
    }
    System.out.println(n + " finished");
    }
    }
    