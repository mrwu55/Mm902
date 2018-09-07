package yty.gxjy.com.mmxxx.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.media.MediaPlayer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.VideoView
import yty.gxjy.com.mmxxx.Bean.VideoBean
import yty.gxjy.com.mmxxx.Interface.VideoCollectListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.VideItemBinding
import java.util.*


/**
 * Created by WuJingCheng on 2018/7/20.
 */
class VideoRecyclerAdapter(listData:MutableCollection<VideoBean.DataBean>?, context: Context?,videoCollectListener: VideoCollectListener) : RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder>(){
    var TAG:String? =null
    private var videoData = listData
    private var listener = videoCollectListener
    private var videoPlaybgLast:RelativeLayout?=null
    private var context = context
    private var videoViewLast: VideoView? =null
    private var mapCollect = TreeMap<Int,Boolean>()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding: VideItemBinding = holder.getBinding()
        val dataBean = videoData?.toList()?.get(position)
        val tvTitle = dataBean?.title
        binding.tvVideoBb.text =dataBean?.collectNum
        binding.tvVideoClick.text = dataBean?.clickNum
        var getCollect = if(mapCollect[position]==null) false else mapCollect[position]
        binding.isCollect = getCollect!!
        binding.imgLolCollect.setOnClickListener(View.OnClickListener {
            var getCollect = if(mapCollect[position]==null) false else mapCollect[position]
            listener.onClick(dataBean!!,position,getCollect!!)
        })
        val videoView = binding.videoView
        val videoPlaybg = binding.reVideoItemPlay
//增加封面
//        val view = LayoutInflater.from(context).inflate(R.layout.thumb_view,null)
//        videoView.getThumbImageViewLayout().addView(view)
//        videoView.setThumbPlay(true)
//        videoView.startButton.setBackgroundResource(R.mipmap.play_btn)
        TAG = dataBean?.videoUrl
        videoView.setUpLazy(TAG, true, null, null, tvTitle)
//增加title
        val tv = videoView.getTitleTextView()
        tv.setText(tvTitle)
        tv.setTextSize(13f)
        tv.visibility = View.VISIBLE
//设置返回键
        videoView.getBackButton().setVisibility(View.GONE)
//设置全屏按键功能
        videoView.getFullscreenButton().setOnClickListener(View.OnClickListener { videoView.startWindowFullscreen(context, false, true) })
//防止错位设置
        videoView.setPlayTag(TAG)
        videoView.setPlayPosition(position)
//是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        videoView.setAutoFullWithSize(false)
//音频焦点冲突时是否释放
        videoView.setReleaseWhenLossAudio(false)
//全屏动画
        videoView.setShowFullAnimation(true)
//小屏时不触摸滑动
        videoView.setIsTouchWiget(false)

     }
    fun setDatas(listData:List<VideoBean.DataBean>,isRefresh:Boolean){
        if(isRefresh){
            videoData?.clear()
            videoData?.addAll(listData)
        }else{
            videoData?.addAll(listData)
        }
    }
    fun setMapDatas(position:Int,isCollect:Boolean){
        mapCollect[position] = isCollect

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: VideItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.video_item, parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        if(videoData!=null){
            return videoData?.size!!
        }
        return 0
    }
    class ViewHolder(binding: VideItemBinding) : RecyclerView.ViewHolder(binding.root){
        var bindings = binding
        fun getBinding(): VideItemBinding {
            return bindings
        }
    }

    internal inner class MyPlayerOnCompletionListener: MediaPlayer.OnCompletionListener {

        override fun onCompletion(mp: MediaPlayer) {
        }
    }
}
