package yty.gxjy.com.mmxxx.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.media.MediaPlayer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.RelativeLayout
import android.widget.VideoView
import yty.gxjy.com.mmxxx.Bean.VideoBean
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.VideItemBinding

/**
 * Created by WuJingCheng on 2018/7/20.
 */
class VideoRecyclerAdapter(videoBean:VideoBean,context:Context) : RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder>(){
    private var videoBean = videoBean
    private var videoPlaybgLast:RelativeLayout?=null
    private var context = context
    private var videoViewLast: VideoView? =null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding: VideItemBinding = holder.getBinding()
        val dataBean = videoBean?.data!![position]
        val tvTitle = dataBean.title
        binding.tvVideoTitle.text =tvTitle
        binding.tvVideoBb.text =dataBean.collectNum
        binding.tvVideoClick.text = dataBean.clickNum
        val videoView = binding.videoView
        val videoPlaybg = binding.reVideoItemPlay
        videoPlaybg.visibility = View.VISIBLE
        videoPlaybg.setOnClickListener(View.OnClickListener {
            if(videoViewLast!=null){
                videoPlaybgLast?.visibility = View.VISIBLE
                if(videoViewLast?.isPlaying!!){
                    videoViewLast?.stopPlayback()

                }
//                videoView?.s
            }
            videoPlaybg.visibility = View.GONE
            videoView.setVideoPath(dataBean.videoUrl)
            videoView.requestFocus()
            videoView.start()
            videoViewLast = videoView
            videoPlaybgLast = videoPlaybg
        })

     }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoRecyclerAdapter.ViewHolder? {
        val binding: VideItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.video_item, parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        if(videoBean!=null&&videoBean?.data!=null){
            return videoBean.data.size
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
