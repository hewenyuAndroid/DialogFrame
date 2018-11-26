# DialogFrame
本项目封装了Dialog的一些通常用法，后续会更新...

### 效果图

![自定义界面](https://github.com/hewenyuAndroid/DialogFrame/blob/master/screen/customDialog.gif)
![默认消息](https://github.com/hewenyuAndroid/DialogFrame/blob/master/screen/normalDialog.gif)
![没有标题](https://github.com/hewenyuAndroid/DialogFrame/blob/master/screen/noTitleDialog.gif)
![单个按钮](https://github.com/hewenyuAndroid/DialogFrame/blob/master/screen/singleDialog.gif)
![TipsLoading](https://github.com/hewenyuAndroid/DialogFrame/blob/master/screen/tipsLoadingDialog.gif)
![TipsIcon](https://github.com/hewenyuAndroid/DialogFrame/blob/master/screen/tipsIconDialog.gif)
![ui](https://github.com/hewenyuAndroid/DialogFrame/blob/master/screen/ui.gif)

### 引用方式
> compile 'com.hewenyu:Dialog:1.0.1'

### 使用方式

* 自定义界面的使用方式
```Java
final AlertDialog dialog = new AlertDialog.Builder(mContext)
            .setContentView(R.layout.dialog_confrim)    // 自定义布局文件
            .addDefaultAnimation()  // 添加默认动画
            .setWidthPercent(0.7f)  // 设置dialog的宽度（同屏幕的百分比）
            .setCorner(8)   // 设置Dialog背景的圆角
            .setBackgroundColor(Color.WHITE)    // 设置背景颜色
            .setOnClickListener(R.id.tv_title, new OnDialogClickListener() {
                @Override
                public void onClick(AlertDialog dialog, View view) {
                    // 设置Dialog里面视图的点击事件
                    Toast.makeText(mContext, "admin", Toast.LENGTH_SHORT).show();
                }
            })
            .setOnClickListener(R.id.tv_confirm, new OnDialogClickListener() {
                @Override
                public void onClick(AlertDialog dialog, View view) {
                // 设置Dialog里面视图的点击事件
                    dialog.dismiss();
                }
            })
            .show();
```

* 默认的消息Dialog
```Java
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
```
* 没有标题的消息Dialog
```Java
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
```
* 单个按钮的Dialog
```Java
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
```
* 加载的Dialog
```Java
AlertDialog dialog = new AlertDialog.TipBuilder(mContext)
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
```
* 带图标的Dialog
```Java
AlertDialog dialog = new AlertDialog.TipBuilder(mContext)
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
```

* **Builder方法API**

| 属性        |    |
| --------   | -----  | 
|setContentView(int layoutId)     |设置Dialog的布局 |
|setText(int viewId, CharSequence text)        |设置文本  | 
|setOnClickListener(int viewId, OnDialogClickListener listener)         |设置点击事件    | 
|fromBottom(boolean isAnimation)         |从底部弹出(是否使用动画)    | 
|setGravity(int gravity)         |设置Dialog的位置    | 
|setWidthAndHeight(int width, int height)         |设置Dialog的    | 
|setWidthPercent(float percent)         |设置Dialog的宽度(同屏幕的比例)    | 
|addDefaultAnimation()         |增加默认的动画    | 
|setAnimations(int styleAnimation)         |设置自定义的动画    | 
|setCancelable(boolean cancelable)         |是否可以取消    | 
|setOnCancelListener(OnCancelListener onCancelListener)         |设置取消按钮的监听|
|setOnDismissListener(OnDismissListener onDismissListener)         |设置DialogDismiss的监听|
|setCorner(float corner)    |设置Dialog的圆角|
|setBackgroundColor(int backgroundColor)    |设置背景颜色|
|setDimAmount(float dimAmount)  |设置Dialog打开时的灰度|