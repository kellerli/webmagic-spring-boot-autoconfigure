package cn.wanru.webmagic.pipeline;

import cn.wanru.webmagic.NotSupportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xxf
 * @date 17-9-9
 */
public class PipelineComposite implements Pipeline {

    private static final Logger log =
            LoggerFactory.getLogger(PipelineComposite.class);

    private List<SupportablePipeline> pipelines;

    public PipelineComposite(List<SupportablePipeline> pipelines) {
        this.pipelines = pipelines;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Pipeline> pipelines = getPipelines(resultItems);
        if (pipelines == null || pipelines.isEmpty()) {
            throw new NotSupportException(resultItems);
        }

        log.debug("find {} pipeline(s) for resultitems {}",
                pipelines.size(),resultItems);

        pipelines.forEach(pipeline -> pipeline.process(resultItems,task));
    }

    private List<Pipeline> getPipelines(ResultItems resultItems) {
        return this.pipelines.stream()
                .filter(p -> p.support(resultItems))
                .collect(Collectors.toList());
    }
}
