import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.moomanow.proxylang.FactoryProxyLang;
import com.moomanow.proxylang.ProxyLangService;

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


		Kwan kwanEng = new Kwan();
		kwanEng.setName("Jarupong");
		Kwan kwanTha = FactoryProxyLang.proxyBean(new ProxyLangService() {
			
			@Override
			public <T> T modifyObjetLang(T object, String lang) {
				// TODO Auto-generated method stub
//				object.
				return null;
			}
		} ,kwanEng,"THA");
		
//		assertEquals("JAR", kwanTha.getName());

//		fail("Not yet implemented");
	}

}
