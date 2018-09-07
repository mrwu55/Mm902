package yty.gxjy.com.mmxxx.Interface

import yty.gxjy.com.mmxxx.Bean.VideoBean

/**
 * Created by WuJingCheng
 * on 2018/9/8.
 */
interface VideoCollectListener{
    fun onClick(dataBean: VideoBean.DataBean, position: Int,isCollect:Boolean)
}