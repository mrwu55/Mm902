package yty.gxjy.com.mmxxx.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import yty.gxjy.com.mmxxx.Bean.PicsBean
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.CollectRecyclerBindng

/**
 * Created by WuJingCheng
 * on 2018/8/26.
 */

/**
 * Created by WuJingCheng on 2018/7/19.
 */

class CollectAdapter(listData:MutableCollection<PicsBean.DataBean>?) : RecyclerView.Adapter<CollectAdapter.ViewHolder>() {
    var data = listData
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val binding: CollectRecyclerBindng = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.collect_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.getBinding().imgId= imgList[position]
    }
    fun setDatas(listData:List<PicsBean.DataBean>,isRefresh:Boolean){
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

    class ViewHolder(binding: CollectRecyclerBindng) : RecyclerView.ViewHolder(binding.root){
        var bindings = binding
        fun getBinding(): CollectRecyclerBindng {
            return bindings
        }
    }
}

