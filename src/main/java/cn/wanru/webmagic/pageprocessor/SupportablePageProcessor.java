package cn.wanru.webmagic.pageprocessor;

import cn.wanru.webmagic.Supportable;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author xxf
 * @date 17-9-9
 */
public abstract class SupportablePageProcessor
        implements PageProcessor, Supportable<Page> {

    @Override
    public Site getSite() {
        return null;
    }
}
