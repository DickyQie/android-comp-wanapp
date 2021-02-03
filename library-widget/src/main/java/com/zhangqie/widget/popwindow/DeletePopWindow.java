package com.zhangqie.widget.popwindow;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.zhangqie.widget.R;

import razerdp.basepopup.BasePopupWindow;

public class DeletePopWindow extends BasePopupWindow {


    public DeletePopWindow(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.pop_delete);
    }

    @Override
    public void onViewCreated(@NonNull View contentView) {
        super.onViewCreated(contentView);
        setBackgroundColor(Color.TRANSPARENT);
        setPopupGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        TextView tvDelete = findViewById(R.id.tvDelete);
        tvDelete.setOnClickListener(view -> {
            if (onDeleteCallBack != null) {
                onDeleteCallBack.onDelete();
            }
            dismiss();
        });
    }


    private OnDeleteCallBack onDeleteCallBack;

    public DeletePopWindow setOnDeleteCallBack(OnDeleteCallBack onDeleteCallBack) {
        this.onDeleteCallBack = onDeleteCallBack;
        return this;
    }

    public interface OnDeleteCallBack {
        void onDelete();
    }
}
