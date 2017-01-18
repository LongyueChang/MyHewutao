package com.tmfortm.shihuo.beans;

import java.util.List;

/**
 * Created by my on 2016/12/15.
 */

public class Surging_Special_column {

    /**
     * img_url : http://shihuo.hupucdn.com/appHaitao/201612/0215/e716d162df32472756baf09192052d11.jpg?imageView2/2/w/750/h/350/interlace/1
     * href : http://www.shihuo.cn/column/17.html#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DhaitaoIndex%22%2C%22block%22%3A%22Special_Column_Banner_1%22%2C%22extra%22%3A%220%22%2C%22id%22%3A%22%22%7D
     */

    private BannerBean banner;
    /**
     * name : adidas Superstar Snake 男款经典贝壳头休闲鞋
     * price : 194.21
     * original_price : 559
     * img : http://shihuo.hupucdn.com/uploads/trade/haitao/cover/201612/1415/d6e77af4dbafe0d399f1b24a9e79511f.jpg?imageView2/2/w/220/h/220/interlace/1
     * href : http://www.shihuo.cn/haitao/buy/350780-2439089.html#%7B%22from%22%3A%22shihuo%3A%2F%2Fwww.shihuo.cn%3Froute%3DhaitaoIndex%22%2C%22block%22%3A%22Special_Column_Goods_Item%22%2C%22extra%22%3A%220%22%2C%22id%22%3A%22%22%7D
     */

    private List<ListBean> list;

    public BannerBean getBanner() {
        return banner;
    }

    public void setBanner(BannerBean banner) {
        this.banner = banner;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class BannerBean {
        private String img_url;
        private String href;

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

    public static class ListBean {
        private String name;
        private String price;
        private String original_price;
        private String img;
        private String href;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
