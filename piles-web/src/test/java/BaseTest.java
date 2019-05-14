import com.piles.PilesApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Auther: zhanglizhi
 * @Date: 2019/5/14 17:49
 * @Description:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= PilesApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)// 指定spring-boot的启动类
public class BaseTest {
}
