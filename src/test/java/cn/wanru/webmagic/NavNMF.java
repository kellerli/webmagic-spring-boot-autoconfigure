package cn.wanru.webmagic;

/**
 * @author xxf
 * @date 17-9-9
 */
public class NavNMF {

    private String code;
    private String date;
    private String unitNav;
    private String accumNav;
    private String source;

    @Override
    public String toString() {
        return "NavNMF{" +
                "code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", unitNav='" + unitNav + '\'' +
                ", accumNav='" + accumNav + '\'' +
                ", source='" + source + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUnitNav() {
        return unitNav;
    }

    public void setUnitNav(String unitNav) {
        this.unitNav = unitNav;
    }

    public String getAccumNav() {
        return accumNav;
    }

    public void setAccumNav(String accumNav) {
        this.accumNav = accumNav;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
