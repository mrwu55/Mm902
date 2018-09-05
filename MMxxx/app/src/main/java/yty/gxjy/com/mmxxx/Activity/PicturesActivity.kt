package yty.gxjy.com.mmxxx.Activity

import android.databinding.DataBindingUtil
import android.view.View
import yty.gxjy.com.mmxxx.Bean.PicDetailBean
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.Interface.RecyclerItemClick
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.PicturesBinding

class PicturesActivity : BaseActivity(), RecyclerItemClick,MmClickListener {
    override fun onClick(view: View) {
        finish()
    }

    override fun onClick(position: Int) {

    }

    var binding: PicturesBinding? = null
    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_pictures)
        binding?.listenerBack = this
        val picturesBean = intent?.getSerializableExtra("picturesBean")
        if(picturesBean!=null){
            binding?.data = picturesBean as PicDetailBean?
            binding?.tvPictureTitle!!.text = intent?.getStringExtra("title")
        }
    }
    override fun initData() {

    }
}
