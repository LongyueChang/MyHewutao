package com.tmfortm.shihuo.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public class TodaySpinner {

    /**
     * status : 0
     * msg : ok
     * data : [{"id":"10","name":"淘宝"},{"id":"11","name":"天猫"},{"id":"12","name":"亚马逊"},{"id":"13","name":"优购"},{"id":"14","name":"易迅"},{"id":"15","name":"当当"},{"id":"16","name":"国美"},{"id":"17","name":"京东"},{"id":"18","name":"苏宁"},{"id":"19","name":"其他"},{"id":"20","name":"银泰"},{"id":"21","name":"有货"},{"id":"22","name":"耐克官网"},{"id":"23","name":"识货微店"},{"id":"24","name":"UA中国官网"},{"id":"25","name":"特步官网"},{"id":"26","name":"考拉海购"}]
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
         * id : 10
         * name : 淘宝
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
