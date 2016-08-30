import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.moomanow.proxylang.FactoryProxyLang;

public class Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@org.junit.Test
	public void test() {


		Kwan kwan = new Kwan();
		
		kwan = FactoryProxyLang.proxyBean(kwan);
		kwan.setName("Jarupong");
		assertEquals("Jarupong", kwan.getName());
		FactoryProxyLang.proxyBeanLang(kwan, "THA");
		assertEquals(null, kwan.getName());

//		fail("Not yet implemented");
	}

}
