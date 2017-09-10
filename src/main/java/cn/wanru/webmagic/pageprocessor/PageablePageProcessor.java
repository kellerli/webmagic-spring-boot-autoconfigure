package cn.wanru.webmagic.pageprocessor;

import cn.wanru.webmagic.PageUtil;
import cn.wanru.webmagic.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;

/**
 * 可翻页的页面处理器
 * @author xxf
 * @date 17-9-10
 */
public abstract class PageablePageProcessor extends SupportablePageProcessor {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void process(Page page) {
        doProcess(page);
        Pageable pageable = PageUtil.getPageable(page.getRequest());
        if (pageable != null) {
            while (pageable.hasNext()) {
                Pageable nextPage = pageable.next();
                page.addTargetRequest(createRequest(nextPage));
                log.debug("find next page {}, current page {}",nextPage,pageable);
            }
        }
    }

    protected abstract Request createRequest(Pageable pageable);

    protected abstract void doProcess(Page page);
}
