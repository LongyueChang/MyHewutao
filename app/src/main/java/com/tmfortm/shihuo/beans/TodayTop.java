package com.tmfortm.shihuo.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class TodayTop {

    /**
     * status : 0
     * msg : ok
     * data : [{"title":"⊙今日白菜价⊙","sub_title":"每日好货,准时更新","href":"http://t.shihuo.cn/m/29.html#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DyouhuiList%22%2C%22block%22%3A%22Ad_1%22%2C%22extra%22%3A%22%22%2C%22id%22%3A%22%22%7D","img_url":"http://shihuo.hupucdn.com/appHome/201612/1411/5d598035fdc233bd7a310109983fd618.jpg"},{"title":"中亚热促","sub_title":"UA登陆低至5折","href":"http://www.shihuo.cn/youhui/243343.html#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DyouhuiList%22%2C%22block%22%3A%22Ad_2%22%2C%22extra%22%3A%22%22%2C%22id%22%3A%22%22%7D","img_url":"http://shihuo.hupucdn.com/appHome/201612/1316/7cbcade820b91adb30fb68fdb1da610c.jpg"},{"title":"每日爆款","sub_title":"CK镜框299元","href":"http://www.shihuo.cn/youhui/243680.html#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DyouhuiList%22%2C%22block%22%3A%22Ad_3%22%2C%22extra%22%3A%22%22%2C%22id%22%3A%22%22%7D","img_url":"http://shihuo.hupucdn.com/appHome/201612/0711/57cd932f74a9a2c5bc41e22aebeafec5.jpg"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : ⊙今日白菜价⊙
         * sub_title : 每日好货,准时更新
         * href : http://t.shihuo.cn/m/29.html#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DyouhuiList%22%2C%22block%22%3A%22Ad_1%22%2C%22extra%22%3A%22%22%2C%22id%22%3A%22%22%7D
         * img_url : http://shihuo.hupucdn.com/appHome/201612/1411/5d598035fdc233bd7a310109983fd618.jpg
         */

        private String title;
        private String sub_title;
        private String href;
        private String img_url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
