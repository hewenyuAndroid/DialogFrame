package com.hwy.dialogframe;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hwy.dialog.AlertDialog;
import com.hwy.dialog.listener.OnDialogClickListener;
import com.hwy.dialog.listener.OperateMessageDialogView;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

    }

    public void showNormal(View view) {
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setContentView(R.layout.dialog_confrim)
                .addDefaultAnimation()
                .setWidthPercent(0.7f)
                .setCorner(8)
                .setBackgroundColor(Color.WHITE)
                .setOnClickListener(R.id.tv_title, new OnDialogClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog, View view) {
                        Toast.makeText(mContext, "admin", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnClickListener(R.id.tv_confirm, new OnDialogClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 显示消息类型的Dialog
     *
     * @param view
     */
    public void showMessage(View view) {

        AlertDialog dialog = new AlertDialog.MessageBuilder(this)
                .setCancel("取消")
                .addDefaultAnimation()
                .setWidthPercent(0.63f)
                .setGravity(Gravity.CENTER)
                .setCorner(8)
                .setBackgroundColor(Color.WHITE)
                .setMessage("是否确认发送消息?")
                .setDimAmount(0.5f)
                .setOnConfirmClickListener(new OnDialogClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog, View view) {
                        Toast.makeText(mContext, "confirm", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnCancelClickListener(new OnDialogClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .operateMessageDialogView(new OperateMessageDialogView() {
                    @Override
                    public void update(TextView title, TextView message, TextView confirm, TextView cancel) {
                        // 直接操作四个View

                    }
                }).show();

    }
}
