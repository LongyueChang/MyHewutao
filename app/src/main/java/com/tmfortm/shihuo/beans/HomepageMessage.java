package com.tmfortm.shihuo.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class HomepageMessage {

    /**
     * status : 1
     * msg : [{"id":"26","message":"【识货最强秒杀日】11.10四场识货专享价秒杀连轴转！价格低至11.11元，手慢无！","url":"http://www.shihuo.cn/youhui/236257.html","create_time":"2016-11-09 17:32:26","flag":0},{"id":"25","message":"【识货心愿单】9月7日订阅用户获奖名单公布","url":"http://www.shihuo.cn/youhui/219722.html#qk=youhui_list","create_time":"2016-09-07 20:00:00","flag":0},{"id":"24","message":"【识货心愿单】9月6日订阅用户获奖名单公布","url":"http://www.shihuo.cn/youhui/219722.html","create_time":"2016-09-06 20:00:00","flag":0},{"id":"23","message":"【识货心愿单】9月5日订阅用户获奖名单公布","url":"http://www.shihuo.cn/youhui/219722.html","create_time":"2016-09-05 20:00:00","flag":0},{"id":"22","message":"【识货心愿单】9月4日订阅用户获奖名单公布","url":"http://www.shihuo.cn/youhui/219722.html#qk=banner&order=1","create_time":"2016-09-04 20:00:00","flag":0},{"id":"21","message":"【识货心愿单】9月3日订阅用户获奖名单公布","url":"http://www.shihuo.cn/youhui/219722.html","create_time":"2016-09-03 20:00:00","flag":0},{"id":"20","message":"【识货APP】热情八月 来识货抽好礼活动 中奖名单公示","url":"http://www.shihuo.cn/youhui/217967.html","create_time":"2016-08-15 17:35:00","flag":0},{"id":"19","message":"【识货APP】三星应用市场合作抽奖活动 中奖名单公示","url":"http://www.shihuo.cn/youhui/214867.html","create_time":"2016-08-01 12:00:00","flag":0},{"id":"18","message":"恭喜骑士获得总冠军，总决赛三分抢楼活动所有（共七场）获奖名单汇总","url":"http://www.shihuo.cn/youhui/205491.html","create_time":"2016-06-20 12:00:00","flag":0},{"id":"17","message":"It's time 助威季后赛 为球队呐喊 赢签名球鞋","url":"http://www.shihuo.cn/youhui/202562.html","create_time":"2016-06-08 12:00:00","flag":0},{"id":"16","message":"总决赛丨识货君正在直播散鞋，场面已经控制不住了 每投进一个三分送一双鞋","url":"http://www.youhuibest.com/","create_time":"2016-06-03 09:30:13","flag":0},{"id":"15","message":"【获奖名单公布】霸占球鞋史上最棒彩蛋 万元AJ12 Wings大奖已刮开！","url":"http://www.shihuo.cn/youhui/197407.html","create_time":"2016-05-24 14:19:02","flag":0},{"id":"13","message":"霸占球鞋史上最棒彩蛋\u2014\u2014万元AJ12 Wings免费送 识货这次玩大了","url":"http://www.shihuo.cn/youhui/197407.html","create_time":"2016-05-17 23:00:00","flag":0},{"id":"12","message":"【获奖名单公布】还在排队抢？NMD识货免费送八双！ 八款配色免费送","url":"http://www.shihuo.cn/youhui/194043.html","create_time":"2016-05-01 12:00:00","flag":0},{"id":"11","message":"【APP活动】还在排队抢？NMD识货免费送八双！ 八款配色免费送","url":"http://www.shihuo.cn/youhui/192306.html","create_time":"2016-04-22 22:00:00","flag":0},{"id":"10","message":"【竞猜结果公布】科比最后一战，谢谢你最后的拼搏，我们都是见证者","url":"http://www.shihuo.cn/youhui/190330.html","create_time":"2016-04-15 12:00:00","flag":0},{"id":"9","message":"60亿科蜜在路上，免费参与科比最后一战竞猜，尽享巨额奖池！","url":"http://www.shihuo.cn/youhui/188588.html","create_time":"2016-04-07 20:00:00","flag":0},{"id":"8","message":"【识货APP】价值8000元Yeezy 350 Boost \u201dMoonrock\u201d获奖名单公布","url":"http://www.shihuo.cn/youhui/183211.html","create_time":"2016-03-22 12:00:00","flag":0},{"id":"7","message":"【识货APP】新版上线adidas Originals NMD获奖名单公布","url":"http://www.shihuo.cn/youhui/183211.html","create_time":"2016-03-21 12:00:00","flag":0},{"id":"6","message":"【识货APP】新版上线 Air Jordan 11 \"72-10\"获奖名单公布","url":"http://www.shihuo.cn/youhui/183211.html","create_time":"2016-03-20 12:00:00","flag":0},{"id":"5","message":"千元奖金@你\u2014\u2014识货君帮你上头条，识货签约作者征集","url":"http://www.shihuo.cn/activity/contractAuthor#qk=banner&order=0","create_time":"2016-03-17 18:11:35","flag":0},{"id":"4","message":"【识货APP】新版上线 价值8000元Yeezy免费拿！绝对公平公正公开！","url":"http://www.shihuo.cn/youhui/182477.html","create_time":"2016-03-16 11:53:18","flag":0},{"id":"2","message":"新版识货v4.0.0上线啦。篮球，跑步，休闲专区重磅推出，正式运营！","url":"http://www.shihuo.cn/youhui/182060.html","create_time":"2016-03-13 16:21:45","flag":0}]
     */

    private int status;
    private List<MsgBean> msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * id : 26
         * message : 【识货最强秒杀日】11.10四场识货专享价秒杀连轴转！价格低至11.11元，手慢无！
         * url : http://www.shihuo.cn/youhui/236257.html
         * create_time : 2016-11-09 17:32:26
         * flag : 0
         */

        private String id;
        private String message;
        private String url;
        private String create_time;
        private int flag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
