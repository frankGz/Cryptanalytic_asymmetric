package foundation;

import java.math.BigInteger;

public class C1 {

	public static void main(String[] args) {
		final int CERTAINTY = 20;
		final int BIT_SIZE = 20;
		BigInteger one = new BigInteger("1");
		BigInteger p = new BigInteger("6");
		BigInteger q = new BigInteger("12");
		// BigInteger q = new BigInteger(BIT_SIZE, CERTAINTY, new SecureRandom());
		assert p.isProbablePrime(CERTAINTY); // fail prob = 1/2^20
		assert q.isProbablePrime(CERTAINTY); // fail prob = 1/2^20
		
		BigInteger n = p.multiply(q);
		BigInteger phi = p.subtract(one).multiply(q.subtract(one));
		
		BigInteger e = new BigInteger("3");
		assert e.gcd(phi).equals(one);
		
		BigInteger d = e.modInverse(phi);
		assert e.multiply(d).mod(phi).equals(one);
		
		String msg = "G";
		BigInteger m = new BigInteger(msg.getBytes());
		assert m.compareTo(n) == -1;
		
		BigInteger ct = m.modPow(e,  n);
		
		System.out.println("p = " + p);
		System.out.println("q = " + q);
		System.out.println("n = " + n);
		System.out.println("phi = " + phi);
		System.out.println("e = " + e);
		System.out.println("d = " + d);
		System.out.println("m = " + m);
		System.out.println("Our RSA bit size is: " + n.bitCount());

		System.out.println("CT=\t" + ct);
		BigInteger back = ct.modPow(d, n);
		System.out.println("Decrypt: PT=\t" + new String(back.toByteArray()));
		/////PART 1 ENDS//////////////////////////////////////////////////////////////////////////////////////////////
		
				//We can implement 2RSA by choosing a modulus n and two public exponents e1 and e2. In this case,
				//encryption is done using: c1 = me1 (mod n) followed by c2 = c1e2 (mod n). Is 2RSA stronger than 1RSA? Juystify your answer. 
				// Answer: no difference since (m^e1)^e2 == M^(e1*e2)
			
			////PART 3 ENDS///////////////////////////////////////////////////////////////////////////////////////////////
				//The exponents e = 1 and e = 2 should not be used in RSA. Why?
					//If we use e = 1 in RSA and compute c = m1
					//(mod n), then c is the
					//plaintext!
					//In the case of e = 2, We know that ¦Õ(n) = (p-1)(q-1)
					// then GCD(e,¦Õ(n))!= 1) since 2 is not prime
			
			///PART 4/////////////////////////////////////////////////////////////////////////////////////////////////////
			//If they are not equal and not relatively prime they can be attacked, and if they are not prime factoring them would be easier
			// as there exists multiple products that give the same result.

	}

}
