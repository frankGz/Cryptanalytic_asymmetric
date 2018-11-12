package foundation;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class C3 {

	public static void main(String[] args) throws Exception{
		BigInteger n = new BigInteger("945874683351289829816050197767812346183848578056570056860845622609107886220137" + 
				"220709264916908438536900712481301344278323249667285825328323632215422317870682" + 
				"037630270674000828353944598575250177072847684118190067762114937353265007829546" + 
				"21660256501187035611332577696332459049538105669711385995976912007767106063");
		BigInteger e = new BigInteger("74327");
		BigInteger c = new BigInteger("10870101966939556606443697147757930290262227730644958783498257036423105365610629" + 
				"52991052582846432979261500260278236678653125327546335884041286783340625646715334" + 
				"51395019521734099553221296896703454456327755743017818003765454489903326085581032" + 
				"66831217073027652061091790342124418143422318965525239492387183438956");
		BigInteger p = new BigInteger("10358344307803887695931304169230543785620607743682421994532795393937342395753127" +
				"888522373061586445417642355843316524942445924294144921649080401518286829171");
		
		BigInteger one = new BigInteger("1");
		
		// n = pq
		BigInteger q = n.divide(p);
		
		//phi = (p-1)(q-1)
		BigInteger phi = p.subtract(one).multiply(q.subtract(one));
		BigInteger d = e.modInverse(phi);
		
		System.out.println("Manual pt: "+ new String(c.modPow(d, n).toByteArray()));
		
		
		//Using JCE
	      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	      RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(n, e);
	      RSAPrivateKeySpec privKeySpec = new RSAPrivateKeySpec(n, d);
	      RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
	      RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(privKeySpec);

	      Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
	      cipher.init(Cipher.DECRYPT_MODE, privKey);
	      byte[] pt = cipher.doFinal(c.toByteArray());
	      System.out.println("PT from JCE:\t" + new String(pt).trim());

	}

}
