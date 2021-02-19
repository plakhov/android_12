public class SimpleThread extends Thread {
    @Override
    public void run() {
        int i = 0;

            do {
                System.out.println("Привет, я новый поток!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Исключение");
                }
                ++i;
            } while (i < 3 && !isInterrupted());
    }
}
