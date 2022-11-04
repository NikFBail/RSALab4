/* RSA Algorithm
 * 1. Choose two large primes, p & q
 * 2. Compute n = p * q
 * 3. Compute φ(n) = (p-1) * (q-1)
 * 4. Choose e ∈ {2, 2, ... , φ(n)} s.t. gcd(e, φ(n)) = 1
 * 5. Compute d = e^(-1) mod φ(n)
 * The public key is (n, e) - e is the public exponent
 * The private key is (p, q, d) - d is the private exponent
 */
public class RSA {
    public static void main(String args[]) {
        // Initializing variable
        int p, q, n, φ, e, x; // Doesn't need beginning value
        int d = 0; // Needs a set value to begin with

        // Number to be encrypted and decrypted
        int message = 12;
        int encryption;
        int result;

        // First prime number p, second prime number q
        // p >= 7, q < 40
        p = 11;
        q = 31;

        // Finding n and φ(n)
        n = p * q;
        φ = (p - 1) * (q - 1);
        System.out.println("The value of n = " + n);
        System.out.println("The value of φ(n) = " + φ);

        // Choose e ∈ {2, 3, ... , φ(n)} s.t. gcd(e, φ(n)) = 1
        // e range starting at 2, otherwise will always stop at e = 1
        for(e = 2; e < φ; e++) {
            // e is for the public key exponent
            if(gcd(e, φ) == 1) {
                break;
            }
        }
        e = 97;
        System.out.println("The value of e = " + e);
        // Creating d
        /* uses the proof we learned about in
         * class to show that
         * x^(ed) = x(1 + i*φ(n)) = x mod n
         */
        for(int i = 0; i <= 9; i++) {
            // Helper variable for finding d
            x = 1 + (i * φ);

            // d is for the private key exponent
            if(x % e == 0) {
                d = x / e;
                break;
            }
        }
        d = modInverse(e, φ);
        System.out.println("The value of d = " + d);

        // Creating the encryption
        encryption = (int) ((Math.pow(message, e)) % n);
        System.out.println("Encrypted message is: " + encryption);

        // Creating the decryption
        result = splitExponent(encryption, d, n);
        System.out.println("Decrypted message is: " + result);
    }

    // recursive gcd method
    public static int gcd(int e, int φ) {
        if(e == 0) return φ;
        else return gcd(φ % e, e);
    }

    // A naive method to find modulo
    // multiplicative inverse of A
    // under modulo M
    public static int modInverse(int e, int mod) { 
        for (int X = 1; X < mod; X++)
            if (((e % mod) * (X % mod)) % mod == 1)
                return X;
        return 1;
    }
    
    /* Method to perform
     * y^d mod n
     * in a way to avoid using BigInteger
     * Takes a number, an exponent,
     * and an integer to modulo by
     * Divide exponent by 2, holding the remainder
    */
    public static int splitExponent(int num, int pow, int mod) {
        int rem = pow % 2;
        int result = 1;
        for(int i = 0; i < pow; i += 2) {
            System.out.println("Before: " + result);
            result = (result * ((int) ((Math.pow(num, 2)) % mod))) % mod;
            System.out.println("After: " + result);
        }
        if(rem != 0) {
            result = (result * (num % mod)) % mod;
        }
        return result;
    }
}