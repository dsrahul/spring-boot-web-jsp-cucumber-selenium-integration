package steps;

import com.standard.SpringBootWebApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootWebApplication.class, loader = SpringBootContextLoader.class)
@SpringBootTest
public abstract class AbstractSpringTests {
}
