package yty.gxjy.com.mmxxx.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yty.gxjy.com.mmxxx.Bean.PicsBean
import yty.gxjy.com.mmxxx.Bean.SearchBean
import yty.gxjy.com.mmxxx.Interface.RecyclerItemClick
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.NewRecyclerBindng
import yty.gxjy.com.mmxxx.databinding.SearchBindng


/**
 * Created by WuJingCheng on 2018/7/19.
 */

class SearchRecyclerAdapter(listData:MutableCollection<SearchBean.DataBean>?, listener: RecyclerItemClick?) : RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {
    var data = listData
    var itemClickListener = listener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val binding: NewRecyclerBindng = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.lay_newgragment_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(data!=null){
            val searchBean = data?.toList()!!.get(position)
//            holder.getBinding().msg =searchBean.title
//            holder.getBinding().imgId = picBean[position].coverUrl
////            var dataBean: PicsBean.DataBean = data?.data!![position]
//            holder.getBinding().imgId= dataBean.coverUrl
//            holder.getBinding().msg = dataBean.title
        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            itemClickListener?.onClick(position)
        })
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

    class ViewHolder(binding: NewRecyclerBindng) : RecyclerView.ViewHolder(binding.root){
        var bindings = binding
        fun getBinding(): NewRecyclerBindng {
            return bindings
        }
    }
}

