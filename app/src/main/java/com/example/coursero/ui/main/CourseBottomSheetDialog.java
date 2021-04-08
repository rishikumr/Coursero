package com.example.coursero.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.coursero.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;

public class CourseBottomSheetDialog extends BottomSheetDialogFragment {
    private final BOTTOM_SHEET_MODE mode;
    private final YouTubePlayer mYouTubePlayer;
    private final float currentTimeStamp;
    private final String note;
    private BottomSheetListener mListener;


    public CourseBottomSheetDialog(BOTTOM_SHEET_MODE noteMODE, YouTubePlayer mYouTubePlayer, float currentTimeStamp, String note) {
        this.currentTimeStamp = currentTimeStamp;
        this.mode = noteMODE;
        this.mYouTubePlayer = mYouTubePlayer;
        this.note = note;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        Button button = v.findViewById(R.id.bottom_sheet_button);
        EditText edButton = v.findViewById(R.id.bottom_sheet_edit);
        TextView textView = v.findViewById(R.id.bottom_sheet_text);

        if (mode == BOTTOM_SHEET_MODE.EDIT_NOTE) {
            textView.setVisibility(View.GONE);
        } else {
            edButton.setVisibility(View.GONE);
            textView.setText("\n \n " + note + " \n \n");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == BOTTOM_SHEET_MODE.EDIT_NOTE) {
                    mListener.onBottomSheetClicked(currentTimeStamp, edButton.getText().toString());
                }
                mYouTubePlayer.play();
                dismiss();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }

    public enum BOTTOM_SHEET_MODE {EDIT_NOTE, SEE_NOTE}

    public interface BottomSheetListener {
        void onBottomSheetClicked(float currentTimeStamp, String s);
    }
}