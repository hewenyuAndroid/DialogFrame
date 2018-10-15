package com.hwy.dialogframe;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hwy.dialog.AlertDialog;

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
                .addDefaultAnimation()
                .setWidthPercent(0.6f)
                .setContentView(R.layout.dialog_confrim)
                .setCorner(16)
                .setBackgroundColor(Color.WHITE)
                .setOnClickListener(R.id.tv_title, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "admin", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

        TextView tvTitle = dialog.getView(R.id.tv_title);
        tvTitle.setTextColor(Color.BLUE);

        dialog.setOnClickListener(R.id.tv_confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
