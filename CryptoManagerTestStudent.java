
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This class represents GFA test cases for a CryptoManager object.
 * 
 * @author Zakir Faizi
 * 
 */
public class CryptoManagerTestStudent {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStringInBounds() {
		assertTrue(CryptoManager.isStringInBounds("THIS TEST WILL BE SUCCESSFUL"));
	}

	@Test
	public void testEncryptCaesar() {
		assertEquals("IFMMP!VTFS", CryptoManager.caesarEncryption("HELLO USER", 1));
	}

	@Test
	public void testDecryptCaesar() {
		assertEquals("HELLO USER", CryptoManager.caesarDecryption("IFMMP!VTFS", 1));
	}
}
