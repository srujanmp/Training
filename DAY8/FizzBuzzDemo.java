// package day8;

import java.util.concurrent.Semaphore;

class FizzBuzz {
    private int n;
    private int current = 1;
    private Semaphore sem = new Semaphore(1);

    public FizzBuzz(int n) { this.n = n; }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while(true){
            sem.acquire();
            if(current > n){ sem.release(); break; }
            if(current % 3 == 0 && current % 5 != 0){
                printFizz.run();
                current++;
            }
            sem.release();
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(true){
            sem.acquire();
            if(current > n){ sem.release(); break; }
            if(current % 5 == 0 && current % 3 != 0){
                printBuzz.run();
                current++;
            }
            sem.release();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(true){
            sem.acquire();
            if(current > n){ sem.release(); break; }
            if(current % 15 == 0){
                printFizzBuzz.run();
                current++;
            }
            sem.release();
        }
    }

    public void number(Runnable printNumber) throws InterruptedException {
        while(true){
            sem.acquire();
            if(current > n){ sem.release(); break; }
            if(current % 3 != 0 && current % 5 != 0){
                printNumber.run();
                current++;
            }
            sem.release();
        }
    }
}

public class FizzBuzzDemo {
    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fb = new FizzBuzz(15);
        Thread s=new Thread();
        fb.number(s);
    }
}