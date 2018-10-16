package com.hwy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.hwy.dialog.listener.OnDialogClickListener;
import com.hwy.dialog.listener.OperateMessageDialogView;
import com.hwy.dialog.listener.OperateTipsDialogView;
import com.hwy.dialog.type.TipsStyle;

/**
 * 作者: hewenyu
 * 日期: 2018/10/15 14:01
 * 说明: dialog 构建
 */
public class AlertDialog extends Dialog {

    private AlertController mAlert;

    public AlertDialog(Context context) {
        super(context);
        mAlert = new AlertController(this, getWindow());
    }

    public AlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this, getWindow());
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        mAlert.setText(viewId, text);
    }

    /**
     * 获取View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        return mAlert.getView(viewId);
    }

    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     */
    public void setOnClickListener(int viewId, OnDialogClickListener listener) {
        mAlert.setOnclickListener(viewId, listener);
    }

    // region ------------------------------- Builder ------------------------------------------------

    public static class Builder {

        protected AlertController.AlertParams P;

        public Builder(Context context) {
            this(context, R.style.dialog);
        }

        public Builder(Context context, int themeResId) {
            P = new AlertController.AlertParams(context, themeResId);
        }

        public Builder setContentView(View view) {
            P.mView = view;
            P.mLayoutId = 0;
            return this;
        }

        // 设置布局内容的layoutId
        public Builder setContentView(int layoutId) {
            P.mView = null;
            P.mLayoutId = layoutId;
            return this;
        }

        // 设置文本
        public Builder setText(int viewId, CharSequence text) {
            P.mTextArray.put(viewId, text);
            return this;
        }

        // 设置点击事件
        public Builder setOnClickListener(int viewId, OnDialogClickListener listener) {
            P.mClickArray.put(viewId, listener);
            return this;
        }

        /**
         * 从底部弹出
         *
         * @param isAnimation 是否有动画
         * @return
         */
        public Builder fromBottom(boolean isAnimation) {
            if (isAnimation) {
                P.mAnimations = R.style.dialog_from_bottom_anim;
            }
            setGravity(Gravity.BOTTOM);
            return this;
        }

        /**
         * 设置显示的位置
         *
         * @param gravity
         * @return
         */
        public Builder setGravity(int gravity) {
            P.mGravity = gravity;
            return this;
        }

        /**
         * 设置Dialog的宽高
         *
         * @param width
         * @param height
         * @return
         */
        public Builder setWidthAndHeight(int width, int height) {
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        /**
         * 设置dialog 的宽度和屏幕宽度的百分比
         *
         * @param percent 0.0f - 1.0f
         * @return
         */
        public Builder setWidthPercent(float percent) {
            P.mWidthPercent = percent;
            return this;
        }

        /**
         * 添加默认动画
         *
         * @return
         */
        public Builder addDefaultAnimation() {
            P.mAnimations = R.style.dialog_scale_anim;
            return this;
        }


        /**
         * 设置动画
         *
         * @param styleAnimation
         * @return
         */
        public Builder setAnimations(int styleAnimation) {
            P.mAnimations = styleAnimation;
            return this;
        }

        /**
         * 点击 dialog 外面的区域是否可以取消
         *
         * @param cancelable
         * @return
         */
        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        /**
         * 设置 dialog cancel 监听
         *
         * @param onCancelListener
         * @return
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        /**
         * dialog dismiss方法监听
         *
         * @param onDismissListener
         * @return
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        /**
         * 设置弹窗背景的圆角
         *
         * @param corner
         * @return
         */
        public Builder setCorner(float corner) {
            P.mCorner = corner;
            return this;
        }

        /**
         * 设置背景色
         *
         * @param backgroundColor
         * @return
         */
        public Builder setBackgroundColor(int backgroundColor) {
            P.mBackgroundColor = backgroundColor;
            return this;
        }

        /**
         * 设置Dialog弹起时，Activity 的灰度
         *
         * @param dimAmount
         * @return
         */
        public Builder setDimAmount(float dimAmount) {
            P.mDimAmount = dimAmount;
            return this;
        }

        /**
         * 构建 dialog 对象
         *
         * @return
         */
        public AlertDialog create() {
            final AlertDialog dialog = new AlertDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        /**
         * 显示 dialog
         *
         * @return
         */
        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }

    // endregion ----------------------------------------------------


    // region ------------------------- MessageBuilder -------------------------------

    /**
     * 消息类型的Dialog
     */
    public static class MessageBuilder extends Builder {

        private AlertController.MessageAlertParams mParams;

        public MessageBuilder(Context context) {
            this(context, R.style.dialog);
        }

        public MessageBuilder(Context context, int themeResId) {
            super(context, themeResId);
            P = new AlertController.MessageAlertParams(context, themeResId);
            mParams = (AlertController.MessageAlertParams) P;
            setContentView(R.layout.layout_dialog_message);
        }

        // region --------------------- 父类方法重写 -------------------------------

        @Override
        public MessageBuilder setContentView(View view) {
            return (MessageBuilder) super.setContentView(view);
        }

        @Override
        public MessageBuilder setContentView(int layoutId) {
            return (MessageBuilder) super.setContentView(layoutId);
        }

        @Override
        public MessageBuilder setText(int viewId, CharSequence text) {
            return (MessageBuilder) super.setText(viewId, text);
        }

        @Override
        public MessageBuilder setOnClickListener(int viewId, OnDialogClickListener listener) {
            return (MessageBuilder) super.setOnClickListener(viewId, listener);
        }

        @Override
        public MessageBuilder fromBottom(boolean isAnimation) {
            return (MessageBuilder) super.fromBottom(isAnimation);
        }

        @Override
        public MessageBuilder setGravity(int gravity) {
            return (MessageBuilder) super.setGravity(gravity);
        }

        @Override
        public MessageBuilder setWidthAndHeight(int width, int height) {
            return (MessageBuilder) super.setWidthAndHeight(width, height);
        }

        @Override
        public MessageBuilder setWidthPercent(float percent) {
            return (MessageBuilder) super.setWidthPercent(percent);
        }

        @Override
        public MessageBuilder addDefaultAnimation() {
            return (MessageBuilder) super.addDefaultAnimation();
        }

        @Override
        public MessageBuilder setAnimations(int styleAnimation) {
            return (MessageBuilder) super.setAnimations(styleAnimation);
        }

        @Override
        public MessageBuilder setCancelable(boolean cancelable) {
            return (MessageBuilder) super.setCancelable(cancelable);
        }

        @Override
        public MessageBuilder setOnCancelListener(OnCancelListener onCancelListener) {
            return (MessageBuilder) super.setOnCancelListener(onCancelListener);
        }

        @Override
        public MessageBuilder setOnDismissListener(OnDismissListener onDismissListener) {
            return (MessageBuilder) super.setOnDismissListener(onDismissListener);
        }

        @Override
        public MessageBuilder setOnKeyListener(OnKeyListener onKeyListener) {
            return (MessageBuilder) super.setOnKeyListener(onKeyListener);
        }

        @Override
        public MessageBuilder setCorner(float corner) {
            return (MessageBuilder) super.setCorner(corner);
        }

        @Override
        public MessageBuilder setBackgroundColor(int backgroundColor) {
            return (MessageBuilder) super.setBackgroundColor(backgroundColor);
        }

        /**
         * 设置Dialog弹起时，Activity 的灰度
         *
         * @param dimAmount
         * @return
         */
        public MessageBuilder setDimAmount(float dimAmount) {
            return (MessageBuilder) super.setDimAmount(dimAmount);
        }

        // endregion ---------------------------------------------------------------------


        /**
         * 设置消息内容
         *
         * @param message
         */
        public MessageBuilder setMessage(CharSequence message) {
            mParams.mMessage = message;
            return this;
        }

        /**
         * 设置消息内容的大小
         *
         * @param size
         * @return
         */
        public MessageBuilder setMessageSize(int size) {
            mParams.mMessageSize = size;
            return this;
        }

        /**
         * 设置消息内容的颜色
         *
         * @param color
         * @return
         */
        public MessageBuilder setMessageColor(int color) {
            mParams.mMessageColor = color;
            return this;
        }

        /**
         * 设置标题
         *
         * @param title
         */
        public MessageBuilder setTitle(CharSequence title) {
            mParams.mTitle = title;
            return this;
        }

        /**
         * 设置标题文本大小
         *
         * @param size
         * @return
         */
        public MessageBuilder setTitleSize(int size) {
            mParams.mTitleSize = size;
            return this;
        }

        /**
         * 设置标题颜色
         *
         * @param color
         * @return
         */
        public MessageBuilder setTitleColor(int color) {
            mParams.mTitleColor = color;
            return this;
        }

        /**
         * 设置确认按钮的文本
         *
         * @param confirm
         * @return
         */
        public MessageBuilder setConfirm(CharSequence confirm) {
            mParams.mConfirm = confirm;
            return this;
        }

        /**
         * 设置取消按钮的文本
         *
         * @param cancel
         * @return
         */
        public MessageBuilder setCancel(CharSequence cancel) {
            mParams.mCancel = cancel;
            return this;
        }

        /**
         * 设置确认按钮的颜色
         *
         * @param color
         * @return
         */
        public MessageBuilder setConfirmColor(int color) {
            mParams.mConfirmColor = color;
            return this;
        }

        /**
         * 设置取消按钮的颜色
         *
         * @param color
         * @return
         */
        public MessageBuilder setCancelColor(int color) {
            mParams.mCancelColor = color;
            return this;
        }

        /**
         * 确认/取消按钮的文本大小
         *
         * @param size
         * @return
         */
        public MessageBuilder setButtonTextSize(int size) {
            mParams.mButtonSize = size;
            return this;
        }

        /**
         * 隐藏取消按钮
         *
         * @return
         */
        public MessageBuilder hideCancel() {
            mParams.mHideCancel = true;
            return this;
        }

        /**
         * 确认按钮在左侧
         *
         * @return
         */
        public MessageBuilder confirmInLeft() {
            mParams.mConfirmInLeft = true;
            return this;
        }

        /**
         * 设置确认按钮的点击事件
         *
         * @param listener
         * @return
         */
        public MessageBuilder setOnConfirmClickListener(OnDialogClickListener listener) {
            mParams.mClickArray.put(R.id.tvConfirm, listener);
            return this;
        }

        /**
         * 设置取消按钮的点击事件
         *
         * @param listener
         * @return
         */
        public MessageBuilder setOnCancelClickListener(OnDialogClickListener listener) {
            mParams.mClickArray.put(R.id.tvCancel, listener);
            return this;
        }

        /**
         * 设置四个按钮的布局参数
         *
         * @param operateMessageDialogView
         * @return
         */
        public MessageBuilder operateMessageDialogView(OperateMessageDialogView operateMessageDialogView) {
            mParams.mOperateMessageView = operateMessageDialogView;
            return this;
        }

        /**
         * 是否隐藏线条
         *
         * @return
         */
        public MessageBuilder hideLine() {
            mParams.mHideLine = true;
            return this;
        }

        /**
         * 设置线条的颜色
         *
         * @param color
         * @return
         */
        public MessageBuilder setLineColor(int color) {
            mParams.mLineColor = color;
            return this;
        }

        /**
         * 隐藏标题
         *
         * @return
         */
        public MessageBuilder hideTitle() {
            mParams.mHideTitle = true;
            return this;
        }

    }

    // endregion -----------------------------------------------------------

    // region ----------------------- TipsBuilder ---------------------------------

    public static class TipBuilder extends Builder {

        private final AlertController.TipAlertParams mParams;

        public TipBuilder(Context context) {
            this(context, R.style.dialog);
        }

        public TipBuilder(Context context, int themeResId) {
            super(context, themeResId);
            P = new AlertController.TipAlertParams(context, themeResId);
            mParams = (AlertController.TipAlertParams) P;
            setContentView(R.layout.layout_dialog_tips);
        }

        // region ------------------- 重写父类的方法 ----------------------------

        @Override
        public TipBuilder setContentView(View view) {
            return (TipBuilder) super.setContentView(view);
        }

        @Override
        public TipBuilder setContentView(int layoutId) {
            return (TipBuilder) super.setContentView(layoutId);
        }

        @Override
        public TipBuilder setText(int viewId, CharSequence text) {
            return (TipBuilder) super.setText(viewId, text);
        }

        @Override
        public TipBuilder setOnClickListener(int viewId, OnDialogClickListener listener) {
            return (TipBuilder) super.setOnClickListener(viewId, listener);
        }

        @Override
        public TipBuilder fromBottom(boolean isAnimation) {
            return (TipBuilder) super.fromBottom(isAnimation);
        }

        @Override
        public TipBuilder setGravity(int gravity) {
            return (TipBuilder) super.setGravity(gravity);
        }

        @Override
        public TipBuilder setWidthAndHeight(int width, int height) {
            return (TipBuilder) super.setWidthAndHeight(width, height);
        }

        @Override
        public TipBuilder setWidthPercent(float percent) {
            return (TipBuilder) super.setWidthPercent(percent);
        }

        @Override
        public TipBuilder addDefaultAnimation() {
            return (TipBuilder) super.addDefaultAnimation();
        }

        @Override
        public TipBuilder setAnimations(int styleAnimation) {
            return (TipBuilder) super.setAnimations(styleAnimation);
        }

        @Override
        public TipBuilder setCancelable(boolean cancelable) {
            return (TipBuilder) super.setCancelable(cancelable);
        }

        @Override
        public TipBuilder setOnCancelListener(OnCancelListener onCancelListener) {
            return (TipBuilder) super.setOnCancelListener(onCancelListener);
        }

        @Override
        public TipBuilder setOnDismissListener(OnDismissListener onDismissListener) {
            return (TipBuilder) super.setOnDismissListener(onDismissListener);
        }

        @Override
        public TipBuilder setOnKeyListener(OnKeyListener onKeyListener) {
            return (TipBuilder) super.setOnKeyListener(onKeyListener);
        }

        @Override
        public TipBuilder setCorner(float corner) {
            return (TipBuilder) super.setCorner(corner);
        }

        @Override
        public TipBuilder setBackgroundColor(int backgroundColor) {
            return (TipBuilder) super.setBackgroundColor(backgroundColor);
        }

        @Override
        public TipBuilder setDimAmount(float dimAmount) {
            return (TipBuilder) super.setDimAmount(dimAmount);
        }

        // endregion ------------------------------------------------------------

        /**
         * 设置Tips显示的类型
         *
         * @param tipsStyle
         * @return
         */
        public TipBuilder setTipsStyle(TipsStyle tipsStyle) {
            mParams.mTipsStyle = tipsStyle;
            return this;
        }

        /**
         * 设置Tips的图标
         *
         * @param resId
         * @return
         */
        public TipBuilder setTipsIcon(int resId) {
            mParams.mTipsIcon = resId;
            return this;
        }

        /**
         * 设置 tips 提示信息
         *
         * @param text
         * @return
         */
        public TipBuilder setTipsText(CharSequence text) {
            mParams.mTips = text;
            return this;
        }

        /**
         * 设置tips文字的大小
         *
         * @param size
         * @return
         */
        public TipBuilder setTipsSize(int size) {
            mParams.mTipsSize = size;
            return this;
        }

        /**
         * 设置 tips 文字的颜色
         *
         * @param color
         * @return
         */
        public TipBuilder setTipsColor(int color) {
            mParams.mTipsColor = color;
            return this;
        }

        /**
         * 监听Tips
         *
         * @param operateTipsDialogView
         * @return
         */
        public TipBuilder operateTipsDialogView(OperateTipsDialogView operateTipsDialogView) {
            mParams.mOperateTipsView = operateTipsDialogView;
            return this;
        }

    }

    // endregion
}
