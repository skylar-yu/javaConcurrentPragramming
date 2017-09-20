package countdownlatchexample;

/*
we can make several threads start at the same time;
we can wait for several threads to finish (whereas, for example, the Thread.join() method only lets you wait for a single thread).
 */

public class Main {
    public static void main(String[] args) {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: " + result);
    }
}