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
        processPageAndUpdatePageable(page);
        Pageable pageable = PageUtil.getPageable(page.getRequest());
        if (pageable != null) {
            while (pageable.hasNext()) {
                Pageable nextPage = pageable.next();
                page.addTargetRequest(createRequest(nextPage));
                log.debug("find next page {}, current page {}",nextPage,pageable);
            }
        }
    }

    /**
     * NOTE : 返回的 Request 必须正确设置 pageable
     * @see PageUtil#setPageable(Request, Pageable)
     * @param pageable
     * @return
     */
    protected abstract Request createRequest(Pageable pageable);

    /**
     * NOTE : 一般的需要将 Page 里的 Pageable 对象状态进行改变
     * 否则 {@link PageablePageProcessor#processPageAndUpdatePageable(Page)} 方法会陷入死循环
     * @param page
     */
    protected abstract void processPageAndUpdatePageable(Page page);
}
