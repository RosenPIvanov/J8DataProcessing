package lambdasinaction.chap13;

import java.util.stream.LongStream;

/**
 * Created by disen on 09.07.16.
 */
public class StramTests {
    public static void main(String[] args){
        System.out.println(factorialStrams(600000));
        factorialTailRecursion(5000);

    }

    static long factorialStrams(long n){
        return LongStream.rangeClosed(1, n).reduce(1, (long a, long b) -> a * b);
    }

    static long factorialTailRecursion(long n){
        return factorialHelper(1, n);
    }

    static long factorialHelper(long acc, long n){
        return n==1?acc:factorialHelper(acc*n, n-1);
    }
}
