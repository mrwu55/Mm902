package yty.gxjy.com.mmxxx.Bean;

import java.util.List;

/**
 * Created by WuJingCheng
 * on 2018/9/2.
 */

public class PicDetailBean {

    /**
     * code : 0
     * msg : 获取成功
     * count : 45
     * data : [{"picUrl":"http://img1.mm115.net/pic/4160/1.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/2.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/3.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/4.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/5.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/6.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/7.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/8.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/9.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/10.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/11.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/12.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/13.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/14.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/15.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/16.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/17.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/18.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/19.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/20.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/21.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/22.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/23.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/24.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/25.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/26.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/27.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/28.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/29.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/30.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/31.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/32.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/33.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/34.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/35.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/36.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/37.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/38.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/39.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/40.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/41.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/42.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/43.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/44.jpg","status":1},{"picUrl":"http://img1.mm115.net/pic/4160/45.jpg","status":1}]
     */

    private int code;
    private String msg;
    private int count;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * picUrl : http://img1.mm115.net/pic/4160/1.jpg
         * status : 1
         */

        private String picUrl;
        private int status;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
