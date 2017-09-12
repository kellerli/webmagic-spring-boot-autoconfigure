package cn.wanru.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;

import static cn.wanru.webmagic.PageUtil.getSupportClass;

/**
 * @author xxf
 * @since 2017/9/12
 */
public abstract class ClassBasePipeline implements SupportablePipeline {

    private Class<?> supportClass;

    public ClassBasePipeline(Class<?> supportClass) {
        this.supportClass = supportClass;
    }

    @Override
    public boolean support(ResultItems resultItems) {
        return getSupportClass(resultItems.getRequest()) == supportClass;
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
