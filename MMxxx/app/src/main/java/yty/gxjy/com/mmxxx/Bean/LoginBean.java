package yty.gxjy.com.mmxxx.Bean;

/**
 * Created by WuJingCheng
 * on 2018/8/26.
 */

public class LoginBean {

    /**
     * code : 0
     * msg : 登录成功
     * count : 1
     * data : {"uId":1,"uName":"user1","userStatus":0,"onLineStatus":0,"registerTime":1534858019000,"preLoginTime":1535087493000,"lastLoginTime":null,"expiryTime":1534929354000,"mlId":1,"mlName":"超级会员","level":1,"picNum":5,"videoNum":5,"picCollectionNum":5,"avCollectionNum":5}
     */

    private int code;
    private String msg;
    private int count;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uId : 1
         * uName : user1
         * userStatus : 0
         * onLineStatus : 0
         * registerTime : 1534858019000
         * preLoginTime : 1535087493000
         * lastLoginTime : null
         * expiryTime : 1534929354000
         * mlId : 1
         * mlName : 超级会员
         * level : 1
         * picNum : 5
         * videoNum : 5
         * picCollectionNum : 5
         * avCollectionNum : 5
         */

        private int uId;
        private String uName;
        private int userStatus;
        private int onLineStatus;
        private long registerTime;
        private long preLoginTime;
        private Object lastLoginTime;
        private long expiryTime;
        private int mlId;
        private String mlName;
        private int level;
        private int picNum;
        private int videoNum;
        private int picCollectionNum;
        private int avCollectionNum;

        public int getUId() {
            return uId;
        }

        public void setUId(int uId) {
            this.uId = uId;
        }

        public String getUName() {
            return uName;
        }

        public void setUName(String uName) {
            this.uName = uName;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
            this.userStatus = userStatus;
        }

        public int getOnLineStatus() {
            return onLineStatus;
        }

        public void setOnLineStatus(int onLineStatus) {
            this.onLineStatus = onLineStatus;
        }

        public long getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(long registerTime) {
            this.registerTime = registerTime;
        }

        public long getPreLoginTime() {
            return preLoginTime;
        }

        public void setPreLoginTime(long preLoginTime) {
            this.preLoginTime = preLoginTime;
        }

        public Object getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Object lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public long getExpiryTime() {
            return expiryTime;
        }

        public void setExpiryTime(long expiryTime) {
            this.expiryTime = expiryTime;
        }

        public int getMlId() {
            return mlId;
        }

        public void setMlId(int mlId) {
            this.mlId = mlId;
        }

        public String getMlName() {
            return mlName;
        }

        public void setMlName(String mlName) {
            this.mlName = mlName;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getPicNum() {
            return picNum;
        }

        public void setPicNum(int picNum) {
            this.picNum = picNum;
        }

        public int getVideoNum() {
            return videoNum;
        }

        public void setVideoNum(int videoNum) {
            this.videoNum = videoNum;
        }

        public int getPicCollectionNum() {
            return picCollectionNum;
        }

        public void setPicCollectionNum(int picCollectionNum) {
            this.picCollectionNum = picCollectionNum;
        }

        public int getAvCollectionNum() {
            return avCollectionNum;
        }

        public void setAvCollectionNum(int avCollectionNum) {
            this.avCollectionNum = avCollectionNum;
        }
    }
}
