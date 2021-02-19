import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchSample {

    private volatile String currentString = "Пока";

    public static void main(String[] args) {
        SynchSample synchSample = new SynchSample();
        Thread thread1 = new Thread(() -> synchSample.method1());
        Thread thread2 = new Thread(() -> synchSample.method2());
        thread1.setName("Поток 1");
        thread2.setName("Поток 2");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(thread1);
        executorService.execute(thread2);
    }

    public void method1() {
        try {
            synchronized (this) {
                for (int i = 0; i < 3; i++) {
                    while (!"Привет".equals(currentString)) {
                        wait();
                    }
                    printMessage("Привет");
                    currentString = "Пока";
                    notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2() {
        try {
            synchronized (this) {
                for (int i = 0; i < 3; i++) {
                    while (!"".equals(currentString)) {
                        wait();
                    }
                    printMessage("Пока");
                    currentString = "Привет";
                    notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method3() {
        try {
            synchronized (this) {
                for (int i = 0; i < 3; i++) {
                    while (!"Пока".equals(currentString)) {
                        wait();
                    }
                    printMessage("Пока");
                    currentString = "Привет";
                    notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printMessage(String message) {
        System.out.println(Thread.currentThread().getName() + " :" + message);
    }
}
