package unit.wallmartbackend;

import cl.arellano.wallmart.wallmartbackend.WallmartBackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = WallmartBackendApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WallmartBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
