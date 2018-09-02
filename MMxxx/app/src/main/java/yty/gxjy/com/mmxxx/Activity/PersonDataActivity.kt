package yty.gxjy.com.mmxxx.Activity

import android.databinding.DataBindingUtil
import android.view.View
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.PersonClass


class PersonDataActivity : BaseActivity(),MmClickListener {


    private var binding:PersonClass?=null

    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_person_data)
        binding?.listener = this
    }
    override fun initData() {
        binding?.tvPersonName!!.text = Constans.uName
        binding?.tvPersonVipnum!!.text = Constans.vipName
    }
    override fun onClick(view: View) {
        finish()
    }

}
