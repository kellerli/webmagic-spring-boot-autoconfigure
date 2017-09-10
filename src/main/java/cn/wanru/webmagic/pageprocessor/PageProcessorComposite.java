package cn.wanru.webmagic.pageprocessor;

import cn.wanru.webmagic.NotSupportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author xxf
 * @date 17-9-9
 */
public class PageProcessorComposite implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(PageProcessorComposite.class);

    private List<SupportablePageProcessor> pageProcessors;
    private Site site;

    public PageProcessorComposite(
            List<SupportablePageProcessor> pageProcessors) {

        this.pageProcessors = pageProcessors;
    }

    public PageProcessorComposite(
            List<SupportablePageProcessor> pageProcessors,
            Site site) {

        this.pageProcessors = pageProcessors;
        this.site = site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public void process(Page page) {
        PageProcessor processor = getPageProcessor(page);
        if (processor == null) {
            throw new NotSupportException(page);
        }
        log.trace("find page process {} for page {}",processor,page);
        processor.process(page);
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    private PageProcessor getPageProcessor(Page page) {
        for (SupportablePageProcessor processor : pageProcessors) {
            if (processor.support(page)) {
                return processor;
            }
        }
        return null;
    }
}
