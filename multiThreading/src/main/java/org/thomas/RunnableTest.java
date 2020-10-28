package org.thomas;

/**
 * author Andrew
 * version V1.0
 * 2020/10/28 20:36
 * Description:
 */
public class RunnableTest {

    static class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(name + "运行， i = " + i);
            }
        }
    }

    public static void main(String[] args) {
        MyRunnable mr1 = new MyRunnable("线程A");
        MyRunnable mr2 = new MyRunnable("线程B");
        Thread t1 = new Thread(mr1);
        Thread t2 = new Thread(mr2);
        t1.start();
        t2.start();
    }
}
