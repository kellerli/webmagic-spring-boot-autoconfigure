package cn.wanru.webmagic.pipeline;

import cn.wanru.webmagic.NavNMF;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.util.List;

/**
 * @author xxf
 * @date 17-9-9
 */
@Component
public class NtesPipeline implements SupportablePipeline {

    @Override
    public boolean support(ResultItems resultItems) {
        return true;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<NavNMF> content = resultItems.get("_content");
        for (NavNMF nmf : content) {
            System.out.println(nmf);
        }
    }
}
