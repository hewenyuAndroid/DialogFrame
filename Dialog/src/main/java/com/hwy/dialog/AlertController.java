package com.hwy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwy.dialog.listener.OnDialogClickListener;
import com.hwy.dialog.listener.OperateMessageDialogView;

/**
 * 作者: hewenyu
 * 日期: 2018/10/15 14:10
 * 说明:
 */
class AlertController {

    private Dialog mDialog;
    private Window mWindow;

    private ViewHolder mViewHolder;

    public AlertController(Dialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public Window getWindow() {
        return mWindow;
    }

    public ViewHolder getViewHolder() {
        return mViewHolder;
    }

    public void setViewHolder(ViewHolder viewHolder) {
        this.mViewHolder = viewHolder;
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        mViewHolder.setText(viewId, text);
    }

    /**
     * 获取View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        return mViewHolder.getView(viewId);
    }

    /**
     * 设置View的点击事件
     *
     * @param viewId
     * @param listener
     */
    public void setOnclickListener(int viewId, OnDialogClickListener listener) {
        mViewHolder.setOnClickListener(viewId, listener, (AlertDialog) mDialog);
    }

    // region ------------------------------- AlertParams ---------------------------------

    public static class AlertParams {

        public Context mContext;
        public int mThemeResId;

        // 点击空白是否能够取消
        public boolean mCancelable = true;

        // Dialog cancel 监听
        public DialogInterface.OnCancelListener mOnCancelListener;

        // Dialog dismiss 监听
        public DialogInterface.OnDismissListener mOnDismissListener;

        // Dialog Key 监听
        public DialogInterface.OnKeyListener mOnKeyListener;

        // 布局View
        public View mView;

        // 布局文件的资源Id
        public int mLayoutId;

        // 存放字体的修改
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();

        // 存放点击事件
        public SparseArray<OnDialogClickListener> mClickArray = new SparseArray<>();

        // 宽度
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 高度
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 动画
        public int mAnimations = 0;

        // 显示的位置
        public int mGravity = Gravity.CENTER;

        // dialog 的宽度和屏幕宽度的百分比
        public float mWidthPercent = 0.6f;

        // dialog 背景色
        public int mBackgroundColor = -1;

        // dialog 背景圆角
        public float mCorner = 0f;

        // dialog 遮罩的透明度
        public float mDimAmount = 0.5f;

        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }

        protected ViewHolder viewHolder = null;

        /**
         * 绑定和设置参数
         *
         * @param mAlert
         */
        public void apply(AlertController mAlert) {
            // 完善这个地方 设置参数

            if (mLayoutId != 0) {
                viewHolder = new ViewHolder(mContext, mLayoutId, mAlert.getWindow());
            }

            if (mView != null) {
                viewHolder = new ViewHolder();
                viewHolder.setContentView(mView);
            }

            if (viewHolder == null) {
                throw new IllegalArgumentException("请设置布局setContentView()");
            }

            // 给Dialog 设置布局
            mAlert.getDialog().setContentView(viewHolder.getContentView());

            // 设置 Controller的辅助类
            mAlert.setViewHolder(viewHolder);

            initTextAndListener(mAlert);

            // 4.配置自定义的效果  全屏  从底部弹出    默认动画
            Window window = mAlert.getWindow();

            // 设置位置
            window.setGravity(mGravity);

            // 设置背景色
            if (mBackgroundColor != -1) {
                viewHolder.getContentView().setBackgroundColor(mBackgroundColor);
            }

            // 设置圆角
            if (mCorner > 0f) {
                setDialogBackground(mCorner, viewHolder.getContentView());
            }

            // 设置动画
            if (mAnimations != 0) {
                window.setWindowAnimations(mAnimations);
            }

            // 设置dialog的宽
            if (mWidthPercent < 0f) {
                mWidthPercent = 0.6f;
            } else if (mWidthPercent > 1.0f) {
                mWidthPercent = 1.0f;
            }
            mWidth = (int) (getScreenWidth(mAlert.getWindow()) * mWidthPercent);

            mHeight = mHeight > getScreenHeight(mAlert.getWindow()) ? getScreenHeight(mAlert.getWindow()) : mHeight;

            // 设置dialog弹起时的背景灰度
            mDimAmount = mDimAmount < 0f ? 0f : mDimAmount;
            mDimAmount = mDimAmount > 1f ? 1f : mDimAmount;
            mAlert.getWindow().setDimAmount(mDimAmount);

            // 设置宽高
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);
        }

        /**
         * 设置文本和点击事件
         *
         * @param mAlert
         */
        protected void initTextAndListener(AlertController mAlert) {
            // 设置文本
            int textArraySize = mTextArray.size();
            for (int i = 0; i < textArraySize; i++) {
                mAlert.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }

            // 设置点击
            int clickArraySize = mClickArray.size();
            for (int i = 0; i < clickArraySize; i++) {
                mAlert.setOnclickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }
        }

        /**
         * 设置弹窗的背景
         *
         * @param corner
         * @param contentView
         */
        protected void setDialogBackground(float corner, View contentView) {

            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setCornerRadius(corner);
            gradientDrawable.setColor(Color.WHITE);

            Drawable bgDrawable = contentView.getBackground();
            if (bgDrawable == null) {
                contentView.setBackgroundDrawable(gradientDrawable);
                return;
            }

            if (bgDrawable instanceof ColorDrawable) {
                gradientDrawable.setColor(((ColorDrawable) bgDrawable).getColor());
                contentView.setBackgroundDrawable(gradientDrawable);
                return;
            }

            if (bgDrawable instanceof GradientDrawable) {
                ((GradientDrawable) bgDrawable).setCornerRadius(corner);
                contentView.setBackgroundDrawable(bgDrawable);
                return;
            }

        }


        /**
         * 获取屏幕的高度
         *
         * @param window
         * @return
         */
        protected int getScreenWidth(Window window) {
            WindowManager wm = window.getWindowManager();
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            return dm.widthPixels;
        }

        /**
         * 获取屏幕的高度
         *
         * @param window
         * @return
         */
        protected int getScreenHeight(Window window) {
            WindowManager wm = window.getWindowManager();
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            return dm.heightPixels;
        }

    }

    // endregion ------------------------------------------------------------------------------

    // region ------------------------- MessageAlertParams --------------------------------------

    public static class MessageAlertParams extends AlertParams {

        public CharSequence mTitle;

        public CharSequence mMessage;

        public CharSequence mConfirm;

        public CharSequence mCancel;

        public int mTitleSize;

        public int mTitleColor;

        public int mMessageSize;

        public int mMessageColor;

        /**
         * 确认/取消按钮的文本大小
         */
        public int mButtonSize;

        public int mConfirmColor;

        public int mCancelColor;

        /**
         * 是否隐藏取消按钮
         */
        public boolean mHideCancel = false;

        /**
         * 确认按钮在左侧
         */
        public boolean mConfirmInLeft = false;

        /**
         * 直接返回四个控件
         */
        public OperateMessageDialogView mOperateMessageView = null;

        /**
         * 是否隐藏线条
         */
        public boolean mHideLine;

        /**
         * 线条的颜色
         */
        public int mLineColor;

        /**
         * 是否隐藏标题
         */
        public boolean mHideTitle = false;

        public MessageAlertParams(Context context, int themeResId) {
            super(context, themeResId);
        }

        @Override
        public void apply(AlertController mAlert) {
            super.apply(mAlert);

            LinearLayout llButton = viewHolder.getContentView().findViewById(R.id.ll_button);
            TextView tvConfirm = (TextView) LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_button, llButton, false);
            tvConfirm.setId(R.id.tvConfirm);
            TextView tvCancel = (TextView) LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_button, llButton, false);
            tvCancel.setId(R.id.tvCancel);
            llButton.removeAllViews();

            if (mHideCancel) {
                // 隐藏取消按钮
                llButton.addView(tvConfirm);
            } else {
                if (mConfirmInLeft) {
                    // 确认按钮在左侧
                    llButton.addView(tvConfirm);
                    llButton.addView(tvCancel);
                } else {
                    // 确认按钮在右侧
                    llButton.addView(tvCancel);
                    llButton.addView(tvConfirm);
                }
            }

            initTextAndListener(mAlert);

            // region ---------------- title ---------------------

            TextView tvTitle = mAlert.getView(R.id.tv_title);
            if (!TextUtils.isEmpty(mTitle)) {
                tvTitle.setText(mTitle);
            }
            if (mTitleColor != 0) {
                tvTitle.setTextColor(mTitleColor);
            }
            if (mTitleSize > 0) {
                tvTitle.setTextSize(mTitleSize);
            }

            if (mHideTitle) {
                tvTitle.setVisibility(View.GONE);
            }

            // endregion ---------------------------------------

            // region ---------------- message ---------------------

            TextView tvMessage = mAlert.getView(R.id.tv_message);
            if (!TextUtils.isEmpty(mMessage)) {
                tvMessage.setText(mMessage);
            }
            if (mMessageColor != 0) {
                tvMessage.setTextColor(mMessageColor);
            }
            if (mMessageSize > 0) {
                tvMessage.setTextSize(mMessageSize);
            }

            // endregion

            // region --------------- 底部按钮 ----------------------

            if (!TextUtils.isEmpty(mConfirm)) {
                tvConfirm.setText(mConfirm);
            }

            if (mButtonSize > 0) {
                tvConfirm.setTextSize(mButtonSize);
                tvCancel.setTextSize(mButtonSize);
            }

            if (mConfirmColor != 0) {
                tvConfirm.setTextColor(mConfirmColor);
            }

            if (!TextUtils.isEmpty(mCancel)) {
                tvCancel.setText(mCancel);
            }

            if (mCancelColor != 0) {
                tvCancel.setTextColor(mCancelColor);
            }

            if (mOperateMessageView != null) {
                mOperateMessageView.update(tvTitle, tvMessage, tvConfirm, tvCancel);
            }

            // endregion -----------------------------------------------------

            // region ----------------------- 线条 ----------------------------

            View line = mAlert.getView(R.id.line);
            if (mHideLine) {
                line.setVisibility(View.GONE);
            }

            if (mLineColor != 0) {
                line.setBackgroundColor(mLineColor);
            }

            // endregion ------------------------------------------------------

        }
    }

    // endregion -------------------------------------------------------


}
