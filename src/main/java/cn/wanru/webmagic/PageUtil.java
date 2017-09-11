package cn.wanru.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;

/**
 * @author xxf
 * @date 17-9-9
 */
public class PageUtil {

    private static final String data_key = PageUtil.class.getPackage().getName() + ".data";

    private static final String pageable_key = PageUtil.class.getPackage().getName() + ".pageable";

    private static final String support_class_key = PageUtil.class.getPackage().getName() + ".support-class";

    private PageUtil() {

    }

    public static <T> void setData(T data, Page page) {
        page.getResultItems().put(data_key, data);
    }

    public static <T> T getData(ResultItems resultItems) {
        return resultItems.get(data_key);
    }

    public static void setPageable(Request request, Pageable pageable) {
        setField(pageable_key, pageable, request);
    }

    public static Pageable getPageable(Request request) {
        return getField(pageable_key, request);
    }

    public static void setSupportClass(Request request, Class<?> clazz) {
        setField(support_class_key,clazz,request);
    }

    public static <T> Class<T> getSupportClass(Request request) {
        return getField(support_class_key,request);
    }

    public static <T> void setField(String key, T obj, Page page) {
        page.getResultItems().put(key, obj);
    }

    public static <T> T getField(String key, ResultItems resultItems) {
        return resultItems.get(key);
    }

    public static <T> void setField(String key, T obj, Request request) {
        request.putExtra(key, obj);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getField(String key, Request request) {
        return (T) request.getExtra(key);
    }
}
