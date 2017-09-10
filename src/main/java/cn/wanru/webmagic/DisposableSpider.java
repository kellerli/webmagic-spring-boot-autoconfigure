package cn.wanru.webmagic;

import org.springframework.beans.factory.DisposableBean;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author xxf
 * @date 17-9-9
 */
public class DisposableSpider extends Spider implements DisposableBean {

    public static Spider create(PageProcessor pageProcessor) {
        return new DisposableSpider(pageProcessor);
    }

    /**
     * create a spider with pageProcessor.
     *
     * @param pageProcessor pageProcessor
     */
    public DisposableSpider(PageProcessor pageProcessor) {
        super(pageProcessor);
    }

    @Override
    public void destroy() throws Exception {
        this.stop();
    }
}
