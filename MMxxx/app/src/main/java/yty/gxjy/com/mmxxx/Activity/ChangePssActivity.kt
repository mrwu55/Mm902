package yty.gxjy.com.mmxxx.Activity

import android.databinding.DataBindingUtil
import android.view.View
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.ChangePssClass

class ChangePssActivity : BaseActivity(),MmClickListener {

    override fun onClick(view: View) {
        finish()
    }
    private var binding: ChangePssClass?=null

    override fun initData() {
    }

    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_change_pss)
        binding?.listener = this
    }

}
