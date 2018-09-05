package yty.gxjy.com.mmxxx.adapter

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Handler
import android.os.Message
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val binding: PicsBindng = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.pic_pics_item, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(data!=null&&data?.data!=null){
            var dataBean:PicDetailBean.DataBean = data?.data!![position]
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
