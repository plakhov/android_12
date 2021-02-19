public class Main {

    private Object myObject = new Object();
    private String nextWord = "Привет";

    public static void main(String[] args) throws InterruptedException {
        Main mutex = new Main();
        Thread myThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mutex.method1();
            }
        });
        Thread myThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                mutex.method2();
            }
        });
        myThread1.start();
        myThread2.start();
    }

    public void method1() {
        synchronized (myObject) {
            try {
                for (int i = 0; i < 3; i++) {
                    while (!"Привет".equals(nextWord)) {
                        myObject.wait();
                    }
                    System.out.println("Привет");
                    nextWord = "Пока";
                    myObject.notify();
                }
            } catch (InterruptedException e) {

            }
        }
    }

    public void method2() {
        synchronized (myObject) {
            try {
                for (int i = 0; i < 3; i++) {
                    while (!"Пока".equals(nextWord)) {
                        myObject.wait();
                    }
                    System.out.println("Пока");
                    nextWord = "Привет";
                    myObject.notify();
                }
            } catch (InterruptedException e) {

            }
        }
    }

}
