package cn.wanru.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import static cn.wanru.webmagic.PageUtil.getData;
import static cn.wanru.webmagic.PageUtil.getSupportClass;

/**
 * @author xxf
 * @since 2017/9/12
 */
public abstract class ClassBasePipeline<T> implements SupportablePipeline {

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

    @Override
    public void process(ResultItems resultItems, Task task) {
        processResult(getData(resultItems));
    }

    protected abstract void processResult(T result);

}
