package cn.wanru.webmagic.pageprocessor;

import cn.wanru.webmagic.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;

import java.util.Collection;

import static cn.wanru.webmagic.PageUtil.getSupportClass;
import static cn.wanru.webmagic.PageUtil.setData;

/**
 * @author xxf
 * @date 17-9-10
 */
public abstract class ClassBasePageablePageProcessor<T>
        extends PageablePageProcessor {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Class<?> supportClass;

    public ClassBasePageablePageProcessor(Class<?> supportClass) {
        this.supportClass = supportClass;
    }

    @Override
    public boolean support(Page page) {
        Class<?> clazz = getSupportClass(page.getRequest());
        return clazz == supportClass;
    }

    @Override
    protected void processPageAndUpdatePageable(Page page,Pageable pageable) {
        T data = parsePage(page,pageable);
        setData(data, page);
        updatePageable(data,pageable);
        if (log.isDebugEnabled()) {
            int size = data instanceof Collection
                    ? ((Collection) data).size() : 1;

            log.debug("url[{}] crawl success,size=[{}]",
                    page.getRequest().getUrl(), size);
        }
    }

    /**
     * 解析 Page 返回结果
     * @param page
     * @param pageable
     * @return
     */
    protected abstract T parsePage(Page page, Pageable pageable);

    protected abstract void updatePageable(T data,Pageable pageable);
}
