package cn.wanru.webmagic.pageprocessor;

import cn.wanru.webmagic.NavNMF;
import com.sun.prism.impl.Disposer;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.CssSelector;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xxf
 * @date 17-9-9
 */
@Component
public class NtesPageProcessor implements SupportablePageProcessor {

    @Override
    public boolean support(Page page) {
        return false;
    }

    @Override
    public void process(Page page) {
        Selectable selectable = page.getHtml().css(
                "#fn_fund_value_trend > table > tbody >tr");
        List<NavNMF> navNMFs = new ArrayList<>();
        for (Selectable tr : selectable.nodes()) {
            NavNMF record = parseRecord(tr);
            navNMFs.add(record);
        }
        page.putField("_content",navNMFs);
    }

    private NavNMF parseRecord(Selectable tr) {
        Selectable tds = tr.css("td","text");
        List<Selectable> tdNodes = tds.nodes();
        String date = tdNodes.get(0).get();
        String unitNav = tdNodes.get(1).get();
        String accumNav = tdNodes.get(2).get();

        NavNMF record = new NavNMF();
        record.setDate(date);
        record.setUnitNav(unitNav);
        record.setAccumNav(accumNav);
        return record;
    }
}
