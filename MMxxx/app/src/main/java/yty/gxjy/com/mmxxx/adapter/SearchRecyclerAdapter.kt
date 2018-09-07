package yty.gxjy.com.mmxxx.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yty.gxjy.com.mmxxx.Bean.SearchBean
import yty.gxjy.com.mmxxx.Interface.SearchClickListener
import yty.gxjy.com.mmxxx.Interface.SearchCollectlistener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.SearchBinding
import yty.gxjy.com.mmxxx.databinding.VideItemBinding
import java.util.*


/**
 * Created by WuJingCheng on 2018/7/19.
 */

class SearchRecyclerAdapter(listData:MutableCollection<SearchBean.DataBean>?, listener: SearchClickListener?, context: Context,videoCollectListener: SearchCollectlistener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var TAG:String? =null
    private var context =context
    private var listener = videoCollectListener
    private var mapCollect = TreeMap<Int,Boolean>()
    var data = listData
    var itemClickListener = listener
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(data!=null){
            val searchBean = data?.toList()!!.get(position)
            if(searchBean.resultType==1){
                val holder =holder as ViewHolder
                holder.getBinding().msg =searchBean.title
                holder.getBinding().imgId = searchBean.coverUrl
                holder.itemView.setOnClickListener(View.OnClickListener {
                    itemClickListener?.onClick(searchBean,position)
                })
            }else{
                val holder =holder as ViewHolderVideo
                val binding =holder.getBinding()
                val tvTitle = searchBean?.title
                binding.tvVideoBb.text =searchBean?.collectNum
                binding.tvVideoClick.text = searchBean?.clickNum
                var getCollect = if(mapCollect[position]==null) false else mapCollect[position]
                binding.isCollect = getCollect!!
                binding.imgLolCollect.setOnClickListener(View.OnClickListener {
                    var getCollect = if(mapCollect[position]==null) false else mapCollect[position]
                    listener.onClick(searchBean!!,position,getCollect!!)
                })
                val videoView = binding.videoView
                val videoPlaybg = binding.reVideoItemPlay
//增加封面
//        val view = LayoutInflater.from(context).inflate(R.layout.thumb_view,null)
//        videoView.getThumbImageViewLayout().addView(view)
//        videoView.setThumbPlay(true)
//        videoView.startButton.setBackgroundResource(R.mipmap.play_btn)
                TAG = searchBean?.videoUrl
                videoView.setUpLazy(TAG, true, null, null, tvTitle)
//增加title
                val tv = videoView.getTitleTextView()
                tv.setText(tvTitle)
                tv.setTextSize(13f)
                tv.visibility = View.VISIBLE
//设置返回键
                videoView.getBackButton().setVisibility(View.GONE)
//设置全屏按键功能
                videoView.getFullscreenButton().setOnClickListener(View.OnClickListener {
                    videoView.startWindowFullscreen(context, false, true) })
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

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==1){
            val binding: SearchBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.lay_search_item, parent, false)
            return ViewHolder(binding)

        }else{
            val bindings: VideItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.video_item, parent, false)
            return ViewHolderVideo(bindings)
        }
    }
    fun setMapDatas(position:Int,isCollect:Boolean){
        mapCollect[position] = isCollect

    }
    override fun getItemViewType(position: Int): Int {
        val dataBean = data?.toList()!!.get(position)
        return dataBean.resultType
    }

    fun setDatas(listData:List<SearchBean.DataBean>,isRefresh:Boolean){
        if(isRefresh){
            data?.clear()
            data?.addAll(listData)
        }else{
            data?.addAll(listData)
        }
    }
    override fun getItemCount(): Int {
        if(data!=null){
            return data?.size!!
        }
        return 0

    }

    class ViewHolder(binding: SearchBinding) : RecyclerView.ViewHolder(binding.root){
        var bindings = binding
        fun getBinding(): SearchBinding {
            return bindings
        }
    }
    class ViewHolderVideo(binding: VideItemBinding) : RecyclerView.ViewHolder(binding.root){
        var bindings = binding
        fun getBinding(): VideItemBinding {
            return bindings
        }
    }
}

