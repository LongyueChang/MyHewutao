package com.tmfortm.shihuo.beans;

import java.util.List;

/**
 * Created by my on 2016/12/12.
 */

public class Channel {

    /**
     * sub_title : 你最爱的工装靴
     * url : http://www.shihuo.cn/column/77.html
     * img_url : ["http://shihuo.hupucdn.com/appShiwuHome/201612/0611/897caed7c96d51ee5413d30acb48e5ae.jpg?imageView2/0/w/150/h/150/interlace/1%7cimageMogr2/format/jpg"]
     * href : http://www.shihuo.cn/column/77.html#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DshiwuHome%22%2C%22block%22%3A%22Channel_1%22%2C%22extra%22%3A%22%22%2C%22id%22%3A%22%22%7D
     */

    private String sub_title;
    private String url;
    private String href;
    private List<String> img_url;

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<String> getImg_url() {
        return img_url;
    }

    public void setImg_url(List<String> img_url) {
        this.img_url = img_url;
    }
}
