package yty.gxjy.com.mmxxx.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.MediaController
import kotlinx.android.synthetic.main.video_item.view.*
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.VideItemBinding

/**
 * Created by WuJingCheng on 2018/7/20.
 */
class VideoRecyclerAdapter(videoList:List<String>,context:Context) : RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder>(){
    private var videoList = videoList
    private var context = context
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding: VideItemBinding = holder.getBinding()
        binding.videoUrl= videoList[position]
        var videoView = binding.root.videoView
        //设置视频控制器
        videoView.setMediaController(MediaController(context))
        //播放完成回调
        videoView.setOnCompletionListener(MyPlayerOnCompletionListener())
        var uri =Uri.parse(videoList[position])
        //设置视频路径
//        videoView.setVideoURI(uri)
        binding.btnVideoPlay.setOnClickListener{
            //开始播放视频
            videoView.start()
        }
//
     }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoRecyclerAdapter.ViewHolder? {
        val binding: VideItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.video_item, parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return videoList.size
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
