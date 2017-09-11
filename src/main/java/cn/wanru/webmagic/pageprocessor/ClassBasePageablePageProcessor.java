package cn.wanru.webmagic.pageprocessor;

import us.codecraft.webmagic.Page;

import static cn.wanru.webmagic.PageUtil.getSupportClass;

/**
 * @author xxf
 * @date 17-9-11
 */
public abstract class ClassBasePageablePageProcessor
        extends PageablePageProcessor {

    private Class<?> supportClass;

    public ClassBasePageablePageProcessor(Class<?> supportClass) {
        this.supportClass = supportClass;
    }

    @Override
    public boolean support(Page page) {
        return getSupportClass(page.getRequest())
                == supportClass;
    }

}
