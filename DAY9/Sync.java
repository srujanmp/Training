
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

class Foo {

    private CountDownLatch l1 = new CountDownLatch(1);
    private CountDownLatch l2 = new CountDownLatch(1);

    private Semaphore sem = new Semaphore(1);

    public void first(Runnable pFirst) {
        pFirst.run();
        l1.countDown();
    }

    public void second(Runnable pSec) throws InterruptedException {

        l1.await();
        pSec.run();
        l2.countDown();

        
    }

    public void third(Runnable pThird) throws InterruptedException {
        l2.await();
        pThird.run();

    }
}

public class Sync {

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();

        Thread t2 = new Thread(() -> {
            try {
                foo.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
            }
        });
        Thread t1 = new Thread(() -> foo.first(() -> System.out.println("first")));
        Thread t3 = new Thread(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
            }
        });

        t1.start();
        t2.start();
        t3.start();

    }
}
