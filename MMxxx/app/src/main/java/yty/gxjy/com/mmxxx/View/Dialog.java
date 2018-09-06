package yty.gxjy.com.mmxxx.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import yty.gxjy.com.mmxxx.R;

public class Dialog extends AlertDialog{
    private TextView mTvCodeSure,mTvmsg;
    private String msg;
    private OnDialogClick onDialogClick;
    public Dialog(Context context,String msg,OnDialogClick onDialogClick) {
        super(context,R.style.MyDialog);
        this.msg = msg;
        this.onDialogClick = onDialogClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_dialog_lay);
        this.setCancelable(false);
        mTvmsg = findViewById(R.id.tv_codedialog_msg);
        mTvmsg.setText(msg);
        mTvCodeSure = findViewById(R.id.tv_codedialog_sure);
        mTvCodeSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDialogClick.onDialogClick();
                dismiss();
            }
        });
    }
    public interface OnDialogClick{
        void onDialogClick();
    }
}
