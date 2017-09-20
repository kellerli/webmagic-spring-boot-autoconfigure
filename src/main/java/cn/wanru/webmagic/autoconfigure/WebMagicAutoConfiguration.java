package cn.wanru.webmagic.autoconfigure;

import cn.wanru.webmagic.AlwaysFalseDuplicateRemover;
import cn.wanru.webmagic.DisposableSpider;
import cn.wanru.webmagic.listener.OrderedListener;
import cn.wanru.webmagic.pageprocessor.CompositePageProcessor;
import cn.wanru.webmagic.pageprocessor.SupportablePageProcessor;
import cn.wanru.webmagic.pipeline.CompositePipeline;
import cn.wanru.webmagic.pipeline.SupportablePipeline;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.OrderComparator;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.*;

/**
 * @author xxf
 * @date 17-9-9
 */
@Configuration
@ConditionalOnClass(Spider.class)
@ConditionalOnMissingBean(Spider.class)
@EnableConfigurationProperties({WebMagicProperties.class})
public class WebMagicAutoConfiguration {

    private ApplicationContext context;
    private WebMagicProperties webMagicProperties;

    public WebMagicAutoConfiguration(ApplicationContext context,
            WebMagicProperties webMagicProperties) {

        this.context = context;
        this.webMagicProperties = webMagicProperties;
    }

    @Bean
    public Spider spider() {
        List<SupportablePageProcessor> beans = getPageProcessors();
        CompositePageProcessor compositePageProcessor =
                new CompositePageProcessor(beans);
        compositePageProcessor.setSite(getSite());
        compositePageProcessor.setIgnore404(webMagicProperties.isIgnore404());

        List<SupportablePipeline> pipelines = getPipelines();
        CompositePipeline compositePipeline = new CompositePipeline(pipelines);

        Spider spider = DisposableSpider.create(compositePageProcessor)
                .setScheduler(
                        new QueueScheduler().setDuplicateRemover(
                        new AlwaysFalseDuplicateRemover()))
                .thread(webMagicProperties.getThreadNum())
                .setSpiderListeners(getSpiderListeners())
                .addPipeline(compositePipeline)
                .setExitWhenComplete(false);
        spider.runAsync();
        return spider;
    }

    private List<SupportablePageProcessor> getPageProcessors() {
        Map<String, SupportablePageProcessor> beansMap =
                context.getBeansOfType(SupportablePageProcessor.class);

        return Collections.unmodifiableList(new ArrayList<>(beansMap.values()));
    }

    private Site getSite() {
        Site site = Site.me();
        if (webMagicProperties.getAcceptStatusCode() != null) {
            List<Integer> acceptCodes =
                    Arrays.asList(webMagicProperties.getAcceptStatusCode());
            if (webMagicProperties.isIgnore404()) {
                acceptCodes.add(404);
            }
            Set<Integer> set = new HashSet<>(acceptCodes);
            site.setAcceptStatCode(set);
        }

        site.setCycleRetryTimes(3);
        if (webMagicProperties.getCycleRetryTimes() != 0) {
            site.setCycleRetryTimes(webMagicProperties.getCycleRetryTimes());
        }

        if (webMagicProperties.getSleepTime() != 0) {
            site.setSleepTime(webMagicProperties.getSleepTime());
        }

        if (webMagicProperties.getDomain() != null) {
            site.setDomain(webMagicProperties.getDomain());
        }

        if (webMagicProperties.getUserAgent() != null) {
            site.setUserAgent(webMagicProperties.getUserAgent());
        }

        site.setUseGzip(webMagicProperties.isUseGzip());

        if (webMagicProperties.getCharset() != null) {
            site.setCharset(webMagicProperties.getCharset());
        }

        return site;
    }

    private List<SpiderListener> getSpiderListeners() {
        Collection<OrderedListener> orderedListeners =
                context.getBeansOfType(OrderedListener.class).values();

        Collection<SpiderListener> listeners =
                context.getBeansOfType(SpiderListener.class).values();

        List<SpiderListener> result = new ArrayList<>(orderedListeners.size());
        OrderComparator.sort(result);
        result.addAll(listeners);
        return result;
    }

    private List<SupportablePipeline> getPipelines() {
        Map<String, SupportablePipeline> beansMap =
                this.context.getBeansOfType(SupportablePipeline.class);
        Collection<SupportablePipeline> values = beansMap.values();
        List<SupportablePipeline> beans = new ArrayList<>(values);
        OrderComparator.sort(beans);
        return beans;
    }

}
