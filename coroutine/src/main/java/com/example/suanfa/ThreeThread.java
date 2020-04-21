package com.example.suanfa;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeThread {

//    private static Object lock = new Object();
    private static ReentrantLock lock = new ReentrantLock(true);
    private static int count = Integer.MAX_VALUE - 100;

    public static void main(String[] args) {
        Thread thread1 = getThread(0, "a");
        Thread thread2 = getThread(1, "b");
        Thread thread3 = getThread(2, "c");
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(300);
            thread1.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Thread getThread(final int threadNo, final String out) {
        return new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    lock.lock();
                    if (count % 3 == threadNo) {
                        System.out.println(out);
                        if (count == Integer.MAX_VALUE)  {
                            count = Integer.MAX_VALUE % 3;
                        }
                        count++;
                    }
                    lock.unlock();
                }


//                synchronized (lock) {
//                    while (true) {
//                        if (count % 3 == threadNo) {
//                            System.out.println(out);
//                            count++;
//                            lock.notifyAll();
//                        } else {
//                            try {
//                                lock.wait();
//                            } catch (InterruptedException e) {
//                                //do nothing
//                            }
//                        }
//                    }
//                }

            }
        });
    }


    private void other() {
//        ConcurrentHashMap
//        Segment
    }
}
