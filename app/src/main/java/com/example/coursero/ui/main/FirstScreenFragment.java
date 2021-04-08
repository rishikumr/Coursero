package com.example.coursero.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursero.R;
import com.example.coursero.adaptors.CourseListAdaptor;
import com.example.coursero.model.Course;
import com.example.coursero.viewmodel.CourseViewModel;

public class FirstScreenFragment extends Fragment {

    private NavController navController;
    private RecyclerView recyclerView;
    private CourseListAdaptor adaptor;
    private String[] courseTitleList;
    private String[] coursesDurationList;
    private CourseViewModel mViewModel;
    private Course[] getAllCourse;

    public static FirstScreenFragment newInstance() {
        return new FirstScreenFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(CourseViewModel.class);
        courseTitleList = mViewModel.getCoursesTitleList();
        coursesDurationList = mViewModel.getCoursesDurationList();
        getAllCourse = mViewModel.getAllCourse();

        recyclerView = view.findViewById(R.id.course_list_rcv);
        adaptor = new CourseListAdaptor(courseTitleList, coursesDurationList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        adaptor.setOnCourseSelectListener(new CourseListAdaptor.onCourseSelectListener() {
            @Override
            public void onCourseListItemClick(int position) {
                //Constants.logD(getAllCourse[position].getTitle());
                mViewModel.setCurrentSelectedCourse(getAllCourse[position]);
                navController.navigate(R.id.action_mainFragment_to_secondScreenFragment);

            }
        });


    }


}