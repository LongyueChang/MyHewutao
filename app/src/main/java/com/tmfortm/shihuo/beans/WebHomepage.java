package com.tmfortm.shihuo.beans;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public class WebHomepage {
    /**
     * {
     "status": 0,
     "msg": "ok",
     "data": {
     "id": "245970",
     "title": "\u7801\u5168\u597d\u4ef7\uff01Dr. Martens Charlton\u7537\u58eb8\u5b54\u9a6c\u4e01\u9774",
     "subtitle": "$76\uff08\u8f6c\u8fd0\u5230\u624b\u7ea6\uffe5706\uff09",
     "spreadtitle": "Dr. Martens \u7537\u58eb8\u5b54\u9a6c\u4e01\u9774",
     "img": "http:\/\/shihuo.hupucdn.com\/newsIndex\/201612\/1512\/b171f2abf1fca31d41ace773c5de463c.jpg"
     ｝
     ｝
     */

    private int status;
    private String msg;
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public class DataBean{
        private String id;
        private String title;
        private String spreadtitle;
        private String img;
        private String content;
        private String support;
        private String against;
        private String go_website;
        private String text;
        private String hits;
        private String reply_count;
        private boolean daigou_flag;
        private String root_type;
        private String children_id;
        private String store_id;
        private String publish_date;
        private String go_url;
        private String time;
        private String is_show_comment;
        private String type;
        private String column_id;

        public String getSupport() {
            return support;
        }

        public void setSupport(String support) {
            this.support = support;
        }

        public String getColumn_id() {
            return column_id;
        }

        public void setColumn_id(String column_id) {
            this.column_id = column_id;
        }

        public String getAgainst() {
            return against;
        }

        public void setAgainst(String against) {
            this.against = against;
        }

        public String getGo_website() {
            return go_website;
        }

        public void setGo_website(String go_website) {
            this.go_website = go_website;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getReply_count() {
            return reply_count;
        }

        public void setReply_count(String reply_count) {
            this.reply_count = reply_count;
        }

        public boolean isDaigou_flag() {
            return daigou_flag;
        }

        public void setDaigou_flag(boolean daigou_flag) {
            this.daigou_flag = daigou_flag;
        }

        public String getRoot_type() {
            return root_type;
        }

        public void setRoot_type(String root_type) {
            this.root_type = root_type;
        }

        public String getChildren_id() {
            return children_id;
        }

        public void setChildren_id(String children_id) {
            this.children_id = children_id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getPublish_date() {
            return publish_date;
        }

        public void setPublish_date(String publish_date) {
            this.publish_date = publish_date;
        }

        public String getGo_url() {
            return go_url;
        }

        public void setGo_url(String go_url) {
            this.go_url = go_url;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getIs_show_comment() {
            return is_show_comment;
        }

        public void setIs_show_comment(String is_show_comment) {
            this.is_show_comment = is_show_comment;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getSpreadtitle() {
            return spreadtitle;
        }

        public void setSpreadtitle(String spreadtitle) {
            this.spreadtitle = spreadtitle;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", spreadtitle='" + spreadtitle + '\'' +
                    ", img='" + img + '\'' +
                    ", content='" + content + '\'' +
                    ", support='" + support + '\'' +
                    ", against='" + against + '\'' +
                    ", go_website='" + go_website + '\'' +
                    ", text='" + text + '\'' +
                    ", hits='" + hits + '\'' +
                    ", reply_count='" + reply_count + '\'' +
                    ", daigou_flag=" + daigou_flag +
                    ", root_type='" + root_type + '\'' +
                    ", children_id='" + children_id + '\'' +
                    ", store_id='" + store_id + '\'' +
                    ", publish_date='" + publish_date + '\'' +
                    ", go_url='" + go_url + '\'' +
                    ", time='" + time + '\'' +
                    ", is_show_comment='" + is_show_comment + '\'' +
                    ", type='" + type + '\'' +
                    ", column_id='" + column_id + '\'' +
                    '}';
        }
    }
}
