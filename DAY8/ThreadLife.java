// package day8;

class MovieAI extends Thread{
    public void music(){
        System.out.println(Thread.currentThread().
            getName()+" is in music state");
        if(Thread.currentThread().getName().
            equals("No way Home"))
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            e.printStackTrace();}
        System.out.println("Composed Music, RR "+
            Thread.currentThread().getName()
        );
    }
    public void write(){
        if(Thread.currentThread().getName().equals("Dawn of Justice"))
            Thread.currentThread().stop();
        System.out.println("Write script, screen play, dialog "+
            Thread.currentThread().getName()
        );
    }
    // director
    public void run(){
        music();
        write();
    }
}

public class ThreadLife {
    public static void main(String[] args) {
        MovieAI movie = new MovieAI();
        Thread t1=new Thread(movie,"No way Home");
        Thread t2=new Thread(movie,"Ragnorok");
        Thread t3=new Thread(movie,"Dawn of Justice");
        Thread t4=new Thread(movie,"Eng game");
        t1.start();t2.start();
        t3.start();t4.start();
    }
}
