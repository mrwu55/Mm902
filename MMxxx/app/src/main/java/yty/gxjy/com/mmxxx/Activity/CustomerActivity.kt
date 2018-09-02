package yty.gxjy.com.mmxxx.Activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.CustomerClass

class CustomerActivity : BaseActivity(),MmClickListener {
    override fun onClick(view: View) {
        finish()
    }

    private var binding: CustomerClass?=null

    override fun initData() {
    }

    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_customer)
        binding?.listener = this
    }
}
