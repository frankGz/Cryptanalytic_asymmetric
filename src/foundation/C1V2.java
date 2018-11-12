package foundation;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class C1V2 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		//1. Implement RSA with modulus 55 and public exponent 3 by finding p, q, and d. Given a plaintext m with GCD(m,55)=1,
				//prove that m = c^d (mod 55), where c is m^e (mod 55). 
		
		final int certainty = 20;
		BigInteger one = new BigInteger("1");
		
		// 55 = 11 * 5, where 11 and 5 are both prime
		BigInteger p = new BigInteger("11");
		BigInteger q = new BigInteger("5");
		
		// n = pq = 55
		BigInteger n = p.multiply(q);
		
		//phi = (p-1)(q-1)
		BigInteger phi = p.subtract(one).multiply(q.subtract(one));
		System.out.println("phi="+phi);
		
		//public exponent e where phi > e > 1 such that GCD(e, phi) = 1
		BigInteger e = new BigInteger("3");
		System.out.println("GCD(e, phi)="+ e.gcd(phi));
		
		// d = inverse of e mod phi, 模逆元  e^-1 mod phi [or] e*d = 1 in the view of phi
		// if a and n are coprime, there is a b such that ab≡1(mod n)
		BigInteger d = e.modInverse(phi);
		System.out.println("d="+d);
		
		String msg = "3";
		BigInteger m = new BigInteger(msg.getBytes());
		// use mod if m >= n
		System.out.println("m="+ m);
		System.out.println("GCD(m, 55)="+ m.gcd(n));
				
		BigInteger c = m.modPow(e,n); // CT
		BigInteger pt =c.modPow(d, n);
		
		System.out.println("c="+c);
		System.out.println("c^d (mod 55)=" + pt);
		System.out.println("PT=" + new String(pt.toByteArray()));
		
		/*
		 We can implement 2RSA by choosing a modulus n and two public exponents e1 and e2. 
		 In this case, encryption is done using: c1 = m^e1 (mod n) followed by c2 = c1^e2 (mod n). 
		 Is 2RSA stronger than 1RSA? Juystify your answer. 
		 
		 (m^e1)^e2 == m^(e1*e2)
		 
		 https://crypto.stackexchange.com/questions/5382/is-it-safer-to-encrypt-twice-with-rsa
		 */
		
		/*
		 Why are the public exponents e=1 and e=2 never used in RSA? 
		 Use e=1, then CT = m^e (mod n) = CT = m (mod n) = PT
		 uSE e=2, φ(n) = (p-1)(q-1),Neither p nor q are equal to 2. This means they are odd. 
		 The product of (p−1)(q−1) would be even i.e. not coprime with 2.
		 Decryption is not unique
		 
		 https://stackoverflow.com/questions/17490282/why-is-this-commit-that-sets-the-rsa-public-exponent-to-1-problematic
		 
		 */
		
		
		
	}

}
