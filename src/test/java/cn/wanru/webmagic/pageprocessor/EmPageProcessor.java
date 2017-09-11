package cn.wanru.webmagic.pageprocessor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

/**
 * @author xxf
 * @date 17-9-9
 */
@Component
public class EmPageProcessor implements SupportablePageProcessor {

    @Override
    public boolean support(Page page) {
        return false;
    }

    @Override
    public void process(Page page) {

    }
}
