package yty.gxjy.com.mmxxx.Activity

import android.databinding.DataBindingUtil
import android.view.View
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.WatchedClass

class WatchedActivity : BaseActivity(),MmClickListener {


    private var binding: WatchedClass? = null

    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_watched)
        binding?.listener = this
    }

    override fun initData() {
        var list = listOf<Int>(R.drawable.c1, R.drawable.c2,
                R.drawable.c3, R.drawable.c1, R.drawable.c2, R.drawable.c3,
                R.drawable.c1, R.drawable.c2)
        binding?.data =list
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.re_watched_back ->{
                finish()
            }

        }
    }
}
