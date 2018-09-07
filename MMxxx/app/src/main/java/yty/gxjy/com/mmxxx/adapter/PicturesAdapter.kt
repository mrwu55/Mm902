package yty.gxjy.com.mmxxx.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yty.gxjy.com.mmxxx.App
import yty.gxjy.com.mmxxx.Bean.PicDetailBean
import yty.gxjy.com.mmxxx.Interface.RecyclerItemClick
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.PicsBindng


/**
 * Created by WuJingCheng on 2018/7/19.
 */

class PicturesAdapter(picsBean: PicDetailBean?, listener: RecyclerItemClick?) : RecyclerView.Adapter<PicturesAdapter.ViewHolder>() {
    var data = picsBean
    var itemClickListener = listener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PicsBindng = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.pic_pics_item, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(data!=null&&data?.data!=null){
            var dataBean:PicDetailBean.DataBean = data?.data!![position]
            val imageView = holder.bindings.imgPicPics
            val layoutParams = imageView.layoutParams
            val width = App.app.getwidth()/2
            layoutParams.width =width
            layoutParams.height = (width*1.5).toInt()
            imageView.layoutParams = layoutParams
//            imageView.setPadding(16,0,0,0)
            holder.getBinding().imgId= dataBean.picUrl

        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            itemClickListener?.onClick(position)
        })
    }

    override fun getItemCount(): Int {
        if(data!=null&&data?.data!=null){
            return data?.data!!.size
        }
        return 0
    }

    class ViewHolder(binding: PicsBindng) : RecyclerView.ViewHolder(binding.root){
        var bindings = binding
        fun getBinding():PicsBindng{
            return bindings
        }
    }
}
