package cn.wanru.webmagic.pipeline;

import cn.wanru.webmagic.Supportable;
import org.springframework.core.Ordered;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author xxf
 * @date 17-9-9
 */
public interface SupportablePipeline
        extends Pipeline, Supportable<ResultItems>, Ordered {
}
