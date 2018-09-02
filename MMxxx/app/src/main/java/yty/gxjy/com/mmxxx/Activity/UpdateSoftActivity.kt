package yty.gxjy.com.mmxxx.Activity

import android.databinding.DataBindingUtil
import android.view.View
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.UpdateSoftClass

class UpdateSoftActivity : BaseActivity(),MmClickListener {


    private var binding: UpdateSoftClass?=null
    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_update_soft)
        binding?.listener = this
    }
    override fun initData() {
    }
    override fun onClick(view: View) {
        finish()
    }
}
