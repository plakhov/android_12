import java.util.concurrent.*;

public class ExecutorSample {

    static class MessageDto{
        private String name;

        public MessageDto(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService;
        executorService = Executors.newSingleThreadExecutor();
        executorService = Executors.newFixedThreadPool(6);
        executorService = Executors.newCachedThreadPool();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        try {
            Future<MessageDto> future = executorService.submit((Callable) () -> {
                System.out.println("Асинхронный вызов");
                Thread.sleep(3000);
                return new MessageDto("Результат из потока");
            });
            System.out.println(future.get(2, TimeUnit.SECONDS).getName());
            System.out.println("Задача поставлена");
            System.out.println("Задача выполнена");
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
