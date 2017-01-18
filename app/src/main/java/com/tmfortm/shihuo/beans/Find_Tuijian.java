package com.tmfortm.shihuo.beans;

/**
 * Created by my on 2016/12/13.
 */

public class Find_Tuijian {

    /**
     * show_type : single9
     * data : {"title":"回首2016|一月一双 2016年1-5月实战利器推荐 （上）","img":"http://shihuo.hupucdn.com/article/2016-12-13/d7f123bd2e5fc8cb8d09ed46d01a5954.jpg?imageView2/0/w/626/h/280/interlace/1%7cimageMogr2/format/jpg","intro":"一月一双 2016年1-5月实战利器推荐 （上）\r\n Kobe、James harden、Lerbon  james、MJ系列等实战战靴","praise":"5","reply_count":"1","author_id":"3369036","author_name":"RocT","avatar":"http://i1.hoopchina.com.cn/user/036/3369036/3369036_big_2.jpg","personal_href":"shihuo://www.shihuo.cn?route=personalHomePage&uid=3369036#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DshiwuHome%22%2C%22block%22%3A%22jingxuan_article%22%2C%22extra%22%3A%22%E5%85%A8%E9%83%A8-48%22%2C%22id%22%3A%221720%22%7D","href":"shihuo://www.shihuo.cn?route=articleDetail&id=1720#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DshiwuHome%22%2C%22block%22%3A%22jingxuan_article%22%2C%22extra%22%3A%22%E5%85%A8%E9%83%A8%22%2C%22id%22%3A%2248%22%7D","param_str":"2016-12-13 11:20:24","date":"2016-12-13"}
     */

    private String show_type;
    /**
     * title : 回首2016|一月一双 2016年1-5月实战利器推荐 （上）
     * img : http://shihuo.hupucdn.com/article/2016-12-13/d7f123bd2e5fc8cb8d09ed46d01a5954.jpg?imageView2/0/w/626/h/280/interlace/1%7cimageMogr2/format/jpg
     * intro : 一月一双 2016年1-5月实战利器推荐 （上）
     Kobe、James harden、Lerbon  james、MJ系列等实战战靴
     * praise : 5
     * reply_count : 1
     * author_id : 3369036
     * author_name : RocT
     * avatar : http://i1.hoopchina.com.cn/user/036/3369036/3369036_big_2.jpg
     * personal_href : shihuo://www.shihuo.cn?route=personalHomePage&uid=3369036#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DshiwuHome%22%2C%22block%22%3A%22jingxuan_article%22%2C%22extra%22%3A%22%E5%85%A8%E9%83%A8-48%22%2C%22id%22%3A%221720%22%7D
     * href : shihuo://www.shihuo.cn?route=articleDetail&id=1720#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DshiwuHome%22%2C%22block%22%3A%22jingxuan_article%22%2C%22extra%22%3A%22%E5%85%A8%E9%83%A8%22%2C%22id%22%3A%2248%22%7D
     * param_str : 2016-12-13 11:20:24
     * date : 2016-12-13
     */

    private DataBean data;

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String title;
        private String img;
        private String intro;
        private String praise;
        private String reply_count;
        private String author_id;
        private String author_name;
        private String avatar;
        private String personal_href;
        private String href;
        private String param_str;
        private String date;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public String getReply_count() {
            return reply_count;
        }

        public void setReply_count(String reply_count) {
            this.reply_count = reply_count;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPersonal_href() {
            return personal_href;
        }

        public void setPersonal_href(String personal_href) {
            this.personal_href = personal_href;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getParam_str() {
            return param_str;
        }

        public void setParam_str(String param_str) {
            this.param_str = param_str;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
