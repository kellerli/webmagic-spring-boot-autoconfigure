package cn.wanru.webmagic.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xxf
 * @date 17-9-9
 */
@ConfigurationProperties("spring.webmagic")
public class WebMagicProperties {
    private int threadNum ;
    private Integer[] acceptStatusCode;
    private int cycleRetryTimes;
    private int sleepTime;
    private String domain;
    private boolean useGzip = true;
    private String userAgent;
    private String charset;

    public WebMagicProperties() {
    }

    public int getThreadNum() {
        if (this.threadNum <= 0) {
            this.threadNum =
                    Runtime.getRuntime().availableProcessors() * 2;
        }
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public Integer[] getAcceptStatusCode() {
        return acceptStatusCode;
    }

    public void setAcceptStatusCode(Integer[] acceptStatusCode) {
        this.acceptStatusCode = acceptStatusCode;
    }

    public int getCycleRetryTimes() {
        return cycleRetryTimes;
    }

    public void setCycleRetryTimes(int cycleRetryTimes) {
        this.cycleRetryTimes = cycleRetryTimes;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isUseGzip() {
        return useGzip;
    }

    public void setUseGzip(boolean useGzip) {
        this.useGzip = useGzip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
