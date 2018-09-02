package yty.gxjy.com.mmxxx.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import yty.gxjy.com.mmxxx.R;

public class Dialog extends AlertDialog{
    private TextView mTvCodeSure;
    public Dialog(Context context) {
        super(context,R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_dialog_lay);
        this.setCancelable(false);
        mTvCodeSure = findViewById(R.id.tv_codedialog_sure);
        mTvCodeSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
