package mblog;

/**
 * Created by raden on 2019/6/1.
 */
public class ThreadTest {
    private static final long count = 100000L;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                int a = 0;
                for(long i = 0; i <count;i++) {
                    a+=5;
                }

            }
        });
        thread.start();
        int b = 0;
        for(long i = 0; i <count;i++) {
            b--;
        }
        thread.join();
        long end = System.currentTimeMillis() - start;
        System.out.println("并行-运行时间: " + end + "ms,count=" +count );
    }
    private static void serial(){
        long start = System.currentTimeMillis();
        int a =0;
        for(long i = 0; i <count;i++) {
            a+=5;
        }
        int b = 0;
        for(long i = 0; i <count;i++) {
            b--;
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("串行-运行时间: " + end + "ms,count=" +count);
    }
}
