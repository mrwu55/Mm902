package yty.gxjy.com.mmxxx.Bean;

import java.util.List;

public class VideoBean {

    /**
     * code : 0
     * msg : 获取成功
     * count : 3539
     * data : [{"vdId":3400,"avid":5044,"commentNum":0,"gmtCreate":1534923780000,"videoDuration":"0:10","coverUrl":"/0a4215f447343a627efbed333677d62b.jpg","tagName":"自拍,诱惑","collectNum":120,"clickNum":7648,"title":"爱煮饭的女孩子，适合当老婆，尤其是这种身材好颜值高的！","videoUrl":"http://pr.mm798.net/back/20180822/d3e5f2e101a32c8e87769231b50c0463.mp4","thumbNum":41,"fileName":"/d3e5f2e101a32c8e87769231b50c0463.mp4","gdp002":1535629460000},{"vdId":3399,"avid":5043,"commentNum":0,"gmtCreate":1534923693000,"videoDuration":"0:09","coverUrl":"/a464f5c10303fc7b764dd0054ad15a1a.jpg","tagName":"自拍,诱惑","collectNum":216,"clickNum":11106,"title":"美女，我承认你很可爱！但是你做这个动作，就少儿不宜了哦！","videoUrl":"http://pr.mm798.net/back/20180822/d94a4a715e9603b2a6e09de64bb146d3.mp4","thumbNum":70,"fileName":"/d94a4a715e9603b2a6e09de64bb146d3.mp4","gdp002":1535629460000},{"vdId":3398,"avid":5042,"commentNum":0,"gmtCreate":1534923619000,"videoDuration":"0:22","coverUrl":"/963d65e5643d016bd30394b8b7e59bc2.jpg","tagName":"写真,模特","collectNum":108,"clickNum":4172,"title":"美女超模性感写真，诱人身材，令人情不自禁想干坏事！","videoUrl":"http://pr.mm798.net/back/20180822/7624b2742003c5b88970dc6d263a3756.mp4","thumbNum":22,"fileName":"/7624b2742003c5b88970dc6d263a3756.mp4","gdp002":1535629460000},{"vdId":3397,"avid":5041,"commentNum":0,"gmtCreate":1534923382000,"videoDuration":"0:37","coverUrl":"/3c472fc2db92e4e0d2f43b8562e9c888.jpg","tagName":"热舞,自拍,诱惑","collectNum":371,"clickNum":7614,"title":"火辣正妹第二弹！这城市很大，快跟我回家！","videoUrl":"http://pr.mm798.net/back/20180822/37f3812431ea7704190159dc269fdce2.mp4","thumbNum":69,"fileName":"/37f3812431ea7704190159dc269fdce2.mp4","gdp002":1535629460000},{"vdId":3396,"avid":5040,"commentNum":1,"gmtCreate":1534923282000,"videoDuration":"0:45","coverUrl":"/e36ed1247734676cf9140f9d8eddfb10.jpg","tagName":"热舞,诱惑","collectNum":413,"clickNum":8993,"title":"正妹身材爆炸，这个动作简直令人喷鼻血！你一定不能错过！","videoUrl":"http://pr.mm798.net/back/20180822/6bcebaa1a009d0502c6ad7a1ea6a7bf1.mp4","thumbNum":64,"fileName":"/6bcebaa1a009d0502c6ad7a1ea6a7bf1.mp4","gdp002":1535629460000},{"vdId":3395,"avid":5039,"commentNum":1,"gmtCreate":1534923218000,"videoDuration":"0:09","coverUrl":"/80dbb31fbb6cf6e270073d6fdd530140.jpg","tagName":"比基尼,诱惑","collectNum":255,"clickNum":12534,"title":"女神们的泳池派对！瞧瞧她们在干嘛？网友：真好看！","videoUrl":"http://pr.mm798.net/back/20180822/c0111bb1664b2d4de91e0283833e5ab3.mp4","thumbNum":46,"fileName":"/c0111bb1664b2d4de91e0283833e5ab3.mp4","gdp002":1535629460000},{"vdId":3394,"avid":5038,"commentNum":0,"gmtCreate":1534923167000,"videoDuration":"0:20","coverUrl":"/1748aa8932b6daa59a2c261aba57d51e.jpg","tagName":"模特,比基尼","collectNum":335,"clickNum":17502,"title":"辣妹超性感写真！你能坚持几秒？全部看完算我输。","videoUrl":"http://pr.mm798.net/back/20180822/312261a5719eda80c551100bddca7db4.mp4","thumbNum":107,"fileName":"/312261a5719eda80c551100bddca7db4.mp4","gdp002":1535629460000}]
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
         * vdId : 3400
         * avid : 5044
         * commentNum : 0
         * gmtCreate : 1534923780000
         * videoDuration : 0:10
         * coverUrl : /0a4215f447343a627efbed333677d62b.jpg
         * tagName : 自拍,诱惑
         * collectNum : 120
         * clickNum : 7648
         * title : 爱煮饭的女孩子，适合当老婆，尤其是这种身材好颜值高的！
         * videoUrl : http://pr.mm798.net/back/20180822/d3e5f2e101a32c8e87769231b50c0463.mp4
         * thumbNum : 41
         * fileName : /d3e5f2e101a32c8e87769231b50c0463.mp4
         * gdp002 : 1535629460000
         */

        private int vdId;
        private int avid;
        private int commentNum;
        private long gmtCreate;
        private String videoDuration;
        private String coverUrl;
        private String tagName;
        private String collectNum;
        private String clickNum;
        private String title;
        private String videoUrl;
        private int thumbNum;
        private String fileName;
        private long gdp002;

        public int getVdId() {
            return vdId;
        }

        public void setVdId(int vdId) {
            this.vdId = vdId;
        }

        public int getAvid() {
            return avid;
        }

        public void setAvid(int avid) {
            this.avid = avid;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public long getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(long gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getVideoDuration() {
            return videoDuration;
        }

        public void setVideoDuration(String videoDuration) {
            this.videoDuration = videoDuration;
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

        public String getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(String collectNum) {
            this.collectNum = collectNum;
        }

        public String getClickNum() {
            return clickNum;
        }

        public void setClickNum(String clickNum) {
            this.clickNum = clickNum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public int getThumbNum() {
            return thumbNum;
        }

        public void setThumbNum(int thumbNum) {
            this.thumbNum = thumbNum;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public long getGdp002() {
            return gdp002;
        }

        public void setGdp002(long gdp002) {
            this.gdp002 = gdp002;
        }
    }
}
