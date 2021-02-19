public class SimpleRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Я новый поток, созданый через интерфейс");
    }
}
