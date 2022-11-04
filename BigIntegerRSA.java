import java.math.*;
import java.security.*;
import java.util.*;

/* RSA Algorithm
 * 1. Choose two large primes, p & q
 * 2. Compute n = p * q
 * 3. Compute φ(n) = (p-1) * (q-1)
 * 4. Choose e ∈ {2, 2, ... , φ(n)} s.t. gcd(e, φ(n)) = 1
 * 5. Compute d = e^(-1) mod φ(n)
 * The public key is (n, e) - e is the public expBigInteger.ONEnt
 * The private key is (p, q, d) - d is the private expBigInteger.ONEnt
 */
public class BigIntegerRSA {
    
    public static void main(String[] args) {
        // Initializing variables
        int bitLength = 36;
        BigInteger p, q;
        BigInteger d = BigInteger.ZERO;
        BigInteger e = BigInteger.ZERO;
        Random rand = new SecureRandom(); // Basically a random number generator

        // What the original message is
        BigInteger message = BigInteger.valueOf(12);
        BigInteger encryption;
        BigInteger decryption;
        System.out.println("The message is: " + 12);

        /* Creates the two primes p and q
         * Uses SecureRandom to get a probable prime at random
         * Apparently it's better to use SecureRandom than Random,
         * as Random isn't a cryptographic randomness source
         * (Whatever that means)
        */
        p = BigInteger.valueOf(104395303);
        q = BigInteger.valueOf(122949829);
        System.out.println("Value of q: " + q);
        System.out.println("Value of p: " + p);

        /* Creating n and φ
         * n = p * q
         * φ(n) = (p-1) * (q-1)
        */
        BigInteger n = p.multiply(q);
        BigInteger φ = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println("The value of n = " + n);
        System.out.println("The value of φ(n) = " + φ);

        /* Picking a public key exponent e
         * Currently using e = 2^16 + 1
         * Have exceptions for if e is too big
         * or if e is not a prime
         * In the case the e needs to be changed,
         * Could do something like
         * e = 2^(n/2) + 1 and see if the works
         */
        e = BigInteger.valueOf(65537);
        if(!e.isProbablePrime(100)) throw new IllegalArgumentException("e must be prime");
        if (e.compareTo(φ) == 1) throw new IllegalArgumentException("e too large - use a smaller value or increase the bitLength");
        System.out.println("Value of e: " + e);

        /* Finding the private key exponent d
         * d = e^(-1) mod φ(n)
         */
        d = e.modInverse(φ);
        System.out.println("Value of d = " + d);

        // The encrypted message
        encryption = message.modPow(e, n);
        System.out.println("Encrypted message is: " + encryption);

        // The decrypted message
        decryption = (encryption.modPow(d, n));
        System.out.println("Decrypted message is: " + decryption);
    }
}