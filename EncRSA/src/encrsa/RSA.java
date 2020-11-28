package encrsa;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private final static BigInteger ONE = new BigInteger("1");
    private BigInteger e, d, modulus;
    private BigInteger phi;
    
    /**
     * Constructor of RSA
     * @param bitLength
     */
    public RSA(int bitLength) {
        // Generate prime numbers
        BigInteger p = BigInteger.probablePrime(bitLength, new Random());
        BigInteger q = BigInteger.probablePrime(bitLength, new Random());
        
        // Get phi
        phi = (p.subtract(ONE)).multiply(q.subtract(ONE));
        
        // Get modulus
        modulus = p.multiply(q);
        
        // Generate e
        do e = new BigInteger(2*bitLength, new Random());
        while (e.compareTo(phi) != 1 || e.gcd(phi).compareTo(ONE) != 0);
        
        // Generate d
        d = e.modInverse(phi);
    }
    
    /**
     * Decrypt message
     * @param message
     * @return decrypted message
     */
    public BigInteger decrypt(BigInteger message) {
        return message.modPow(d, modulus);
    }
    
    /**
     * Get e from RSA
     * @return e
     */
    public BigInteger getE() {
        return e;
    }
    
    /**
     * Get modulus from RSA
     * @return modulus
     */
    public BigInteger getModulus() {
        return modulus;
    }
}
