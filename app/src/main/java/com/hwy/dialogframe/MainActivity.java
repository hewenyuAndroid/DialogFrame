package com.hwy.dialogframe;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hwy.dialog.AlertDialog;
import com.hwy.dialog.listener.OnDialogClickListener;
import com.hwy.dialog.listener.OperateMessageDialogView;
import com.hwy.dialog.listener.OperateTipsDialogView;
import com.hwy.dialog.type.TipsStyle;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

    }

    public void showNormal(View view) {
        final AlertDialog dialog = new AlertDialog.Builder(mContext)
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

        AlertDialog dialog = new AlertDialog.MessageBuilder(mContext)
                .setCancel("取消")
                .addDefaultAnimation()
                .setWidthPercent(0.65f)
                .setGravity(Gravity.CENTER)
                .setCorner(8)
                .setBackgroundColor(Color.WHITE)
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
                .operateMessageDialogView(
                        new OperateMessageDialogView() {
                            @Override
                            public void update(TextView title, TextView message, TextView confirm, TextView cancel) {
                                // 直接操作四个View

                            }
                        }
                ).show();

    }

    public void showMessageNoTitle(View view) {
        AlertDialog dialog = new AlertDialog.MessageBuilder(mContext)
                .setCancel("取消")
                .addDefaultAnimation()
                .setWidthPercent(0.65f)
                .setGravity(Gravity.CENTER)
                .setCorner(8)
                .hideTitle()
                .setBackgroundColor(Color.WHITE)
                .setMessage("密码错误")
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
                .operateMessageDialogView(
                        new OperateMessageDialogView() {
                            @Override
                            public void update(TextView title, TextView message, TextView confirm, TextView cancel) {
                                // 直接操作四个View

                            }
                        }
                )
                .show();
    }

    public void showMessageSingle(View view) {
        AlertDialog dialog = new AlertDialog.MessageBuilder(mContext)
                .addDefaultAnimation()
                .setWidthPercent(0.65f)
                .setGravity(Gravity.CENTER)
                .setCorner(8)
                .setBackgroundColor(Color.WHITE)
                .setMessage("密码错误")
                .hideCancel()
                .setDimAmount(0.5f)
                .setOnConfirmClickListener(new OnDialogClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog, View view) {
                        Toast.makeText(mContext, "confirm", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .operateMessageDialogView(
                        new OperateMessageDialogView() {
                            @Override
                            public void update(TextView title, TextView message, TextView confirm, TextView cancel) {
                                // 直接操作四个View

                            }
                        }
                )
                .show();
    }

    /**
     * 提示信息的Dialog
     *
     * @param view
     */
    public void showTips(View view) {

        final AlertDialog dialog = new AlertDialog.TipBuilder(mContext)
                .setTipsStyle(TipsStyle.LOADING)
                .setTipsText("正在登录")
                .setWidthPercent(0.3f)
                .setCorner(30f)
                .setCancelable(false)
                .setDimAmount(0f)
                .operateTipsDialogView(new OperateTipsDialogView() {
                    @Override
                    public void update(View icon, TextView tips) {
                        tips.setTextColor(Color.WHITE);
                    }
                })
                .show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 2000);

    }

    public void showTipsInfo(View view) {

        final AlertDialog dialog = new AlertDialog.TipBuilder(mContext)
                .setTipsStyle(TipsStyle.ICON)
                .setTipsIcon(R.drawable.icon_notify_info)
                .setTipsText("请勿重复操作")
                .setWidthPercent(0.3f)
                .setCorner(30f)
                .setCancelable(false)
                .setDimAmount(0f)
                .operateTipsDialogView(new OperateTipsDialogView() {
                    @Override
                    public void update(View icon, TextView tips) {
                        tips.setTextColor(Color.WHITE);
                    }
                })
                .show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 2000);
    }
}
