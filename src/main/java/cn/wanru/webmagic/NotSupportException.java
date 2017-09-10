package cn.wanru.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;

/**
 * @author xxf
 * @date 17-9-9
 */
public class NotSupportException extends RuntimeException {

    private Page page;
    private ResultItems resultItems;

    public NotSupportException() {
    }

    public NotSupportException(Page page) {
        this.page = page;
    }

    public NotSupportException(ResultItems resultItems) {
        this.resultItems = resultItems;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void setResultItems(ResultItems resultItems) {
        this.resultItems = resultItems;
    }

    public Page getPage() {
        return page;
    }

    public ResultItems getResultItems() {
        return resultItems;
    }

    @Override
    public String getMessage() {
        if (page != null) {
            return page.getRequest().getUrl();
        }

        if (resultItems != null) {
            return resultItems.getRequest().getUrl();
        }

        return super.getMessage();
    }
}
