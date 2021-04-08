package com.example.coursero.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.coursero.R;
import com.example.coursero.viewmodel.CourseViewModel;

public class MainActivity extends AppCompatActivity implements CourseBottomSheetDialog.BottomSheetListener {
    private CourseViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

    }

    @Override
    public void onBottomSheetClicked(float currentTimeStamp, String note) {
        // Bottom sheet dismissed
        mViewModel.saveNoteToCurrentCourse(currentTimeStamp, note);

    }
}