public class DeadLockSample {

    Object lock1 = new Object();
    Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            System.out.println("ИБД");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println("ИБД");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            System.out.println("ИБД");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {
                System.out.println("ИБД");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLockSample lockSample = new DeadLockSample();
        Thread thread1 = new Thread(() -> lockSample.method1());
        Thread thread2 = new Thread(() -> lockSample.method2());
        thread1.setName("1");
        thread2.setName("2");
        thread1.start();
        thread2.start();
    }
}
