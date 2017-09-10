package cn.wanru.webmagic;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xxf
 * @date 17-9-10
 */
public class AlwaysFalseDuplicateRemover implements DuplicateRemover {

    private AtomicLong urls = new AtomicLong(0);

    @Override
    public boolean isDuplicate(Request request, Task task) {
        urls.getAndIncrement();
        return false;
    }

    @Override
    public void resetDuplicateCheck(Task task) {
        urls.set(0);
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return urls.intValue();
    }
}
