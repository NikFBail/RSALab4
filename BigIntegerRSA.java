import java.math.*;
import java.util.*;

public class BigIntegerRSA {

    static final BigInteger ONE = BigInteger.valueOf(1);
    static final BigInteger ZERO = BigInteger.valueOf(0);
    
    public static void main(String[] args) {
        BigInteger p, q, x;
        BigInteger d = ZERO;
        BigInteger e = ZERO;

        BigInteger input = BigInteger.valueOf(12);
        BigInteger result;
        BigInteger encryption;

        p = BigInteger.valueOf(512927377);
        q = BigInteger.valueOf(817504253);

        BigInteger n = p.multiply(q);
        BigInteger φ = (p.subtract(ONE)).multiply(q.subtract(ONE));

        System.out.println("The value of n = " + n);
        System.out.println("The value of φ(n) = " + φ);

        for(int i = 0; i < φ.intValue(); i++) {
            e = BigInteger.valueOf(i);
            if(gcd(e, φ) == BigInteger.ONE) break;
        }
        System.out.println("The value of e = " + e);

        for(int i = 0; i <= 9; i++) {
            x = ONE.add(BigInteger.valueOf(i).multiply(φ));

            if(x.mod(e) == ZERO) {
                d = x.divide(e);
                break;
            }
        }

        System.out.println("The value of d = " + d);

        
        encryption = input.modPow(e, n);
        System.out.println("Encrypted message is: " + encryption);

        // BigInteger of result
        result = (encryption.modPow(d, n));
        System.out.println("Decrypted message is: " + result);
    }

    public static BigInteger gcd(BigInteger e, BigInteger φ) {
        if(e == ZERO) return φ;
        else return gcd(φ.mod(e), e);
    }
}