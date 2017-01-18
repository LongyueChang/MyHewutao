package com.tmfortm.shihuo.beans;

/**
 * Created by my on 2016/12/12.
 */

public class Banner {


    /**
     * sort : 1
     * url : http://www.shihuo.cn/column/77.html
     * img_url : http://shihuo.hupucdn.com/appHome/201612/0614/fe6b1de5e78aa49177bdfe92f50ccb8d.jpg?imageView2/2/w/750/h/268/interlace/1
     * href : http://www.shihuo.cn/column/77.html#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DshiwuHome%22%2C%22block%22%3A%22Banner_1%22%2C%22extra%22%3A%22%22%2C%22id%22%3A%22%22%7D
     */

    private String sort;
    private String url;
    private String img_url;
    private String href;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
