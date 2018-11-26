package com.hwy.dialogframe;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hwy.dialog.AlertDialog;
import com.hwy.dialog.listener.OnDialogClickListener;
import com.hwy.dialog.listener.OperateMessageDialogView;
import com.hwy.dialog.listener.OperateTipsDialogView;
import com.hwy.dialog.type.TipsStyle;
import com.hwy.dialog.util.KeyBoardUtil;

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

    /**
     * 显示信息
     *
     * @param view
     */
    public void showMessageUI(View view) {
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setContentView(R.layout.layout_dialog_message_ui)
                .addDefaultAnimation()
                .setWidthPercent(0.787f)
                .setGravity(Gravity.CENTER)
                .setCorner(8)
                .setText(R.id.tv_dialog_title, "标题")
                .setText(R.id.tv_dialog_content, "消息内容")
                .setText(R.id.tv_dialog_confirm, "确认")
                .setText(R.id.tv_dialog_cancel, "取消")
                .setCancelable(true)
                .setBackgroundColor(Color.WHITE)
                .setDimAmount(0.5f)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                })
                .setOnClickListener(R.id.tv_dialog_cancel, new OnDialogClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .setOnClickListener(R.id.tv_dialog_confirm, new OnDialogClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog, View view) {
                        Toast.makeText(mContext, "确认", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    /**
     * 显示编辑框
     *
     * @param view
     */
    public void showEditUI(View view) {

        String info = "";
        String hint = "请输入...";

        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setContentView(R.layout.layout_dialog_edit_ui)
                .addDefaultAnimation()
                .setWidthPercent(0.787f)
                .setGravity(Gravity.CENTER)
                .setCorner(8)
                .setText(R.id.tv_dialog_title, "标题")
                .setText(R.id.et_dialog_content, null == info ? "" : info)
                .setText(R.id.tv_dialog_confirm, "确认")
                .setText(R.id.tv_dialog_cancel, "取消")
                .setCancelable(true)
                .setBackgroundColor(Color.WHITE)
                .setDimAmount(0.5f)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                })
                .setOnClickListener(R.id.tv_dialog_cancel, new OnDialogClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .setOnClickListener(R.id.tv_dialog_confirm, new OnDialogClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog, View view) {
                        Toast.makeText(mContext, "确认", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

        EditText etContent = dialog.getView(R.id.et_dialog_content);
        if (!TextUtils.isEmpty(hint)) {
            etContent.setHint(hint);
        }

        // 光标定位到最后
        etContent.setSelection(etContent.getText().toString().length());

        // 延迟显示键盘
        KeyBoardUtil.showKeyBoard(etContent);

    }


    /**
     * 显示两个参数
     *
     * @param view
     */
    public void showOptionUI(View view) {

        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setContentView(R.layout.layout_dialog_two_option_ui)
                .addDefaultAnimation()
                .setWidthPercent(0.787f)
                .setGravity(Gravity.CENTER)
                .setCorner(8)
                .setCancelable(true)
                .setBackgroundColor(Color.WHITE)
                .setDimAmount(0.5f)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                })
                .setOnClickListener(R.id.tv_dialog_top_option, new OnDialogClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog, View view) {
                        dialog.dismiss();
                        Toast.makeText(mContext, "top", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnClickListener(R.id.tv_dialog_bottom_option, new OnDialogClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog, View view) {
                        dialog.dismiss();
                        Toast.makeText(mContext, "bottom", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

        TextView top = dialog.getView(R.id.tv_dialog_top_option);
        TextView bottom = dialog.getView(R.id.tv_dialog_bottom_option);

        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.icon_tick);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        top.setCompoundDrawables(null, null, drawable, null);

    }
}
