import java.math.*;
import java.util.*;

/* RSA Algorithm
 * 1. Choose two large primes, p & q
 * 2. Compute n = p * q
 * 3. Compute φ(n) = (p-1) * (q-1)
 * 4. Choose e ∈ {2, 2, ... , φ(n)} s.t. gcd(e, φ(n)) = 1
 * 5. Compute d = e^(-1) mod φ(n)
 * The public key is (n, e) - e is the public exponent
 * The private key is (p, q, d) - d is the private exponent
 * 
 */
public class RSA {
    public static void main(String args[]) {
        // Initializing variable
        int p, q, n, φ, e, x; // Doesn't need beginning value
        int d = 0; // Needs a set value to begin with

        // Number to be encrypted and decrypted
        int message = 12;
        double c;
        BigInteger decryptMessage;

        // First prime number p, second prime number q
        // p >= 7, q < 40
        p = 11;
        q = 31;

        // Finding n and φ(n)
        n = p * q;
        φ = (p - 1) * (q - 1);
        System.out.println("The value of φ(n) = " + φ);

        // Case 1, gcd(e, φ) = 1, Use Euler Algorithm
        // Choose e ∈ {2, 2, ... , φ(n)} s.t. gcd(e, φ(n)) = 1
        // e range starting at 2, otherwise will always stop at e = 1
        for(e = 2; e < φ; e++) {
            // e is for the public key exponent
            if(gcd(e, φ) == 1) {
                break;
            }
        }
        System.out.println("The value of e = " + e);
        // Case 2, gcd(e, φ) != 1
        for(int i = 0; i <= 9; i++) {
            x = 1 + (i * φ);

            // d is for the private key exponent
            if(x % e == 0) {
                d = x / e;
                break;
            }
        }
        System.out.println("The value of d = " + d);
        c = (Math.pow(message, e)) % n;
        System.out.println("Encrypted message is: " + c);

        // Converting int value of n to BigInteger
        BigInteger N = BigInteger.valueOf(n);

        // Converting float value of c to BigInteger
        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        decryptMessage = (C.pow(d)).mod(N);
        System.out.println("Decrypted message is: " + decryptMessage);
    }

    public static int gcd(int e, int z) {
        if(e == 0) return z;
        else return gcd(z % e, e);
    }
}