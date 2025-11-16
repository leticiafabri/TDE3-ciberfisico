public class Corrigido {

    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> trabalho(), "T1");
        Thread t2 = new Thread(() -> trabalho(), "T2");

        t1.start();
        t2.start();
    }

    static void trabalho() {
        log("tentando adquirir LOCK_A");
        synchronized (LOCK_A) {
            log("adquiriu LOCK_A");
            dormir(50);

            log("tentando adquirir LOCK_B");
            synchronized (LOCK_B) {
                log("adquiriu LOCK_B");
                log("concluiu trabalho");
            }

            log("liberou LOCK_B");
        }
        log("liberou LOCK_A");
    }

    static void dormir(long ms) {
        try { Thread.sleep(ms); }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " - " + msg);
    }
}
