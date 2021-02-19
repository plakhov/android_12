public class LockSample {

    Object lock1 = new Object();

    public static void main(String[] args) {
        LockSample lockSample = new LockSample();
        Thread thread1 = new Thread(() -> lockSample.method1());
        Thread thread2 = new Thread(() -> lockSample.method1());
        thread1.setName("Поток 1");
        thread2.setName("Поток 2");
        thread1.start();
        thread2.start();
    }

    public void method1() {
        printMessage("Начало не сихнонизированного блока");
        try {
            printMessage("ИБД");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printMessage("Конец не сихнонизированного блока");
        synchronized (lock1) {
            printMessage("Начало сихнонизированного блока");
            for (int i = 0; i < 3; i++) {
                printMessage("Привет");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            printMessage("Конец сихнонизированного блока");
        }
    }

    public void method2() {
        synchronized (this) {
            for (int i = 0; i < 3; i++) {
                printMessage("Пока");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printMessage(String message) {
        System.out.println(Thread.currentThread().getName() + " :" + message);
    }
}
