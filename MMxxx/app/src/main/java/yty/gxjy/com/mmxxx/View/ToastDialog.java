package yty.gxjy.com.mmxxx.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import yty.gxjy.com.mmxxx.R;

public class ToastDialog extends AlertDialog{
    private TextView mTvMsg;
    private String mesg;
    public ToastDialog(Context context,String str) {
        super(context,R.style.DialogToast);
        mesg = str;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_lay);
        mTvMsg = findViewById(R.id.tv_toast_msg);
        mTvMsg.setText(mesg);
    }

    public void setMeg(String msg){
        if(msg==null) return;
        mTvMsg.setText(msg);
    }
}
