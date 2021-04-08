package com.example.coursero.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coursero.R;
import com.example.coursero.adaptors.CourseListAdaptor;
import com.example.coursero.util.Constants;
import com.example.coursero.viewmodel.CourseViewModel;

public class MainFragment extends Fragment {

    NavController navController;
    RecyclerView recyclerView;
    CourseListAdaptor adaptor;
    String[] courseTitleList;
    String[] coursesDurationList;
    private CourseViewModel mViewModel;
    ;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(CourseViewModel.class);
        courseTitleList = mViewModel.getCoursesTitleList();
        coursesDurationList = mViewModel.getCoursesDurationList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Direct jumping to home screen for testing
        /*SharedPreferences pref = requireActivity().getSharedPreferences(Constants.MyPref, 0); // 0 - for private mode
        String currToken = pref.getString("mUserToken", null);*/

        recyclerView = view.findViewById(R.id.course_list_rcv);

        //Constants.logD(String.valueOf(courseTitleList.length));
        Constants.logD(" CourseTitle" + courseTitleList[0]);
        Constants.logD(" CourseDuration" + coursesDurationList[0]);
        adaptor = new CourseListAdaptor(courseTitleList, coursesDurationList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adaptor.setOnCourseSelectListener(new CourseListAdaptor.onCourseSelectListener() {
            @Override
            public void onCourseListItemClick(int position) {
                Constants.logD("Selected Course" + position);
                Constants.logD("Selected CourseTitle" + courseTitleList[position]);
                Constants.logD("Selected CourseDuration" + coursesDurationList[position]);
            }
        });

    }


}