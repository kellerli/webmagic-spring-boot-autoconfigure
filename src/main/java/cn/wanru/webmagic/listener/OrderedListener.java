package cn.wanru.webmagic.listener;

import org.springframework.core.Ordered;
import us.codecraft.webmagic.SpiderListener;

/**
 * @author xxf
 * @date 17-9-10
 */
public interface OrderedListener extends SpiderListener, Ordered {

}
