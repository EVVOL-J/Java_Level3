package Lesson4;

public class printLetter {
    static Object object = new Object();
    static volatile String string = "A";

    public static void main(String[] args) {
        new myThread("A", "B").start();
        new myThread("B", "C").start();
        new myThread("C", "A").start();


    }

    static class myThread extends Thread {
        private String letter;
        private String nextLetter;

        public myThread(String letter, String nextLetter) {
            this.letter = letter;
            this.nextLetter = nextLetter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (object) {
                    try {
                        while (!string.equals(letter))
                            object.wait();
                        Thread.sleep(100);
                        System.out.println(letter);
                        string = nextLetter;
                        object.notifyAll();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
