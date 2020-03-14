import java.time.ZonedDateTime;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/14 12:57
 */

public class Test {
    @org.junit.Test
    public void test01(){
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
