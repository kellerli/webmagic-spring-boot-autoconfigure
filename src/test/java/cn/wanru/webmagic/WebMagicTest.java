package cn.wanru.webmagic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.*;

/**
 * @author xxf
 * @date 17-9-9
 */
@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebMagicTest.class)
public class WebMagicTest {

    @Autowired
    private Spider spider;

    @Test
    public void testSpider() throws InterruptedException {
        Spider.Status status = spider.getStatus();
        System.out.println(status);
    }

    @Test
    public void testCrawl() throws InterruptedException {
        Thread.sleep(10000);

        String url = "http://quotes.money.163.com/fund/jzzs_150240.html?" +
                "start=2016-04-19&end=2016-07-14";
        Request request = new Request(url);
        request.putExtra("type","nets");
        request.putExtra("start","2016-04-19");
        request.putExtra("end","2016-07-14");
        request.putExtra("code","150240");
        spider.addUrl(url);

        Thread.sleep(20000);
    }



}
