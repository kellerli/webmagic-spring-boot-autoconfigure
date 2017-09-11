package cn.wanru.webmagic.pageprocessor;

import cn.wanru.webmagic.PageUtil;
import us.codecraft.webmagic.Page;

/**
 * @author xxf
 * @date 17-9-11
 */
public abstract class ClassBaseSupportablePageProcessor
        implements SupportablePageProcessor {

    private Class<?> supportClass;

    public ClassBaseSupportablePageProcessor(Class<?> supportClass) {
        this.supportClass = supportClass;
    }

    public Class<?> getSupportClass() {
        return supportClass;
    }

    @Override
    public boolean support(Page page) {
        return PageUtil.getSupportClass(page.getRequest())
                == supportClass;
    }

}
