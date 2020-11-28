package data;

import java.io.Serializable;
import java.math.BigInteger;

public class PublicKey implements Serializable{
    private BigInteger e, modulus;

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public BigInteger getModulus() {
        return modulus;
    }

    public void setModulus(BigInteger modulus) {
        this.modulus = modulus;
    }
}
