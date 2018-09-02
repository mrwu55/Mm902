package yty.gxjy.com.mmxxx.Activity

import android.databinding.DataBindingUtil
import android.view.View
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.View.Dialog
import yty.gxjy.com.mmxxx.databinding.CallbackClass

class CallbackActivity : BaseActivity(), MmClickListener {
    private var dialog: Dialog?=null

    private var binding: CallbackClass?=null

    override fun initData() {
    }

    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_callback)
        binding?.listener = this
    }
    override fun onClick(view: View) {
        when(view.id){
            R.id.re_callback_back ->{
                finish()
            }
            R.id.tv_callback_sure ->{
                if(dialog==null){
                    dialog = Dialog(this)
                }
                dialog?.show()
            }
        }
    }
}

