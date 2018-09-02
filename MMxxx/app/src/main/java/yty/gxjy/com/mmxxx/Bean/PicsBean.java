package yty.gxjy.com.mmxxx.Bean;

import java.util.List;

public class PicsBean {

    /**
     * code : 0
     * msg : 获取成功
     * count : 3539
     * data : [{"pdId":3539,"collectNum":0,"title":"娇羞护士尹菲玲珑身材令人饥渴难耐","detailUrl":"/pic/4160/1.jpg","picNum":45,"coverUrl":"http://img1.mm115.net/pic/4160/m.jpg","tagName":"护士,尹菲,饥渴"},{"pdId":3538,"collectNum":0,"title":"娇嫩辣妹缇娜美酥胸裸露欲火焚身","detailUrl":"/pic/4159/1.jpg","picNum":51,"coverUrl":"http://img1.mm115.net/pic/4159/m.jpg","tagName":"辣妹,缇娜美,酥胸,欲火"},{"pdId":3537,"collectNum":0,"title":"清纯嫩模小奶昔闺房私拍美艳夺目","detailUrl":"/pic/4158/1.jpg","picNum":39,"coverUrl":"http://img1.mm115.net/pic/4158/m.jpg","tagName":"嫩模,小奶昔,闺房,私拍"},{"pdId":3536,"collectNum":0,"title":"百变娇娃Gaia造型多变春光露无限","detailUrl":"/pic/4157/1.jpg","picNum":65,"coverUrl":"http://img1.mm115.net/pic/4157/m.jpg","tagName":"娇娃,Gaia,春光"},{"pdId":3535,"collectNum":0,"title":"性感护士杨晨晨暧昧桃色诱人心魂","detailUrl":"/pic/4156/1.jpg","picNum":56,"coverUrl":"http://img1.mm115.net/pic/4156/m.jpg","tagName":"性感,护士,杨晨晨"},{"pdId":3534,"collectNum":0,"title":"香艳御姐凌希儿白嫩巨乳惊爆眼球","detailUrl":"/pic/4155/1.jpg","picNum":43,"coverUrl":"http://img1.mm115.net/pic/4155/m.jpg","tagName":"御姐,凌希儿,巨乳"},{"pdId":3533,"collectNum":0,"title":"极品少妇妲己湿身泳装诱惑勾人欲火","detailUrl":"/pic/4154/1.jpg","picNum":45,"coverUrl":"http://img1.mm115.net/pic/4154/m.jpg","tagName":"少妇,妲己,湿身,诱惑"},{"pdId":3532,"collectNum":0,"title":"魅惑娇娃李七喜清凉装束难遮巨乳","detailUrl":"/pic/4153/1.jpg","picNum":40,"coverUrl":"http://img1.mm115.net/pic/4153/m.jpg","tagName":"魅惑,李七喜,巨乳"},{"pdId":3531,"collectNum":0,"title":"欲望女神苏可儿闺房私拍尺度惊人","detailUrl":"/pic/4152/1.jpg","picNum":53,"coverUrl":"http://img1.mm115.net/pic/4152/m.jpg","tagName":"女神,苏可儿,私拍"},{"pdId":3530,"collectNum":0,"title":"风骚少妇刘钰儿连体网袜令人神魂颠倒","detailUrl":"/pic/4151/1.jpg","picNum":45,"coverUrl":"http://img1.mm115.net/pic/4151/m.jpg","tagName":"风骚,少妇,刘钰儿"},{"pdId":3529,"collectNum":0,"title":"性感尤物何晨曦浴缸搔首弄姿撩人心魄","detailUrl":"/pic/4150/1.jpg","picNum":43,"coverUrl":"http://img1.mm115.net/pic/4150/m.jpg","tagName":"性感,尤物,何晨曦,搔首弄姿"},{"pdId":3528,"collectNum":0,"title":"邻家小妹Carol酱校服诱惑酸甜可人","detailUrl":"/pic/4149/1.jpg","picNum":45,"coverUrl":"http://img1.mm115.net/pic/4149/m.jpg","tagName":"邻家小妹,Carol酱,校服,诱惑"}]
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
         * pdId : 3539
         * collectNum : 0
         * title : 娇羞护士尹菲玲珑身材令人饥渴难耐
         * detailUrl : /pic/4160/1.jpg
         * picNum : 45
         * coverUrl : http://img1.mm115.net/pic/4160/m.jpg
         * tagName : 护士,尹菲,饥渴
         */

        private String pdId;
        private int collectNum;
        private String title;
        private String detailUrl;
        private int picNum;
        private String coverUrl;
        private String tagName;

        public String getPdId() {
            return pdId;
        }

        public void setPdId(String pdId) {
            this.pdId = pdId;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public int getPicNum() {
            return picNum;
        }

        public void setPicNum(int picNum) {
            this.picNum = picNum;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }
    }
}
