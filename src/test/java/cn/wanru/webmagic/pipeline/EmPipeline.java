package cn.wanru.webmagic.pipeline;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * @author xxf
 * @date 17-9-9
 */
@Component
public class EmPipeline implements SupportablePipeline {
    @Override
    public boolean support(ResultItems resultItems) {
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {

    }
}
