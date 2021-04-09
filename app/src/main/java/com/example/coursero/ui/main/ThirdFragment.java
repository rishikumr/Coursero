package com.example.coursero.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.coursero.R;
import com.example.coursero.adaptors.QuizAnswerAdaptor;
import com.example.coursero.database.CourseStaticDetails;
import com.example.coursero.model.Course;
import com.example.coursero.model.Quiz;
import com.example.coursero.viewmodel.CourseViewModel;


public class ThirdFragment extends Fragment {


    CourseViewModel mviewModel;
    String question;
    String[] answers;
    int ans;
    int currentQuizStatus = 0;
    Quiz currQuiz;
    TextView quiz_question;
    String currCourseID;
    Course currCourse;
    CourseViewModel mViewModel;


    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(CourseViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewGiftGive = view.findViewById(R.id.quiz_rcv);
        recyclerViewGiftGive.setHasFixedSize(true);
        StaggeredGridLayoutManager gridLayoutManager1 = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerViewGiftGive.setLayoutManager(gridLayoutManager1);

        //currCourse = mviewModel.getCurrentSelectedCourse();
        //currentQuizStatus = currCourse.getQuiz_status();
        CourseStaticDetails.prepareQuizList();
        currQuiz = CourseStaticDetails.QUIZ_LIST[currentQuizStatus];
        //Constants.logD("currQuiz=" + currQuiz + " CourseStaticDetails.QUIZ_LIST= "+ CourseStaticDetails.QUIZ_LIST[1]);
        answers = currQuiz.getAnswerList();
        QuizAnswerAdaptor answerAdaptor = new QuizAnswerAdaptor(answers, currentQuizStatus);
        recyclerViewGiftGive.setAdapter(answerAdaptor);

        quiz_question = view.findViewById(R.id.quiz_question);
        quiz_question.setText(currQuiz.getQuestion());

        answerAdaptor.setOnEventClickListener(new QuizAnswerAdaptor.OnAnswerClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == currQuiz.getAns()) {
                    currentQuizStatus++;
                    if (currentQuizStatus == 10) {
                        finishTheQuiz();
                        return;
                    }
                    currQuiz = CourseStaticDetails.QUIZ_LIST[currentQuizStatus];
                    quiz_question.setText(currQuiz.getQuestion());
                    answerAdaptor.submitList(currQuiz);
                } else {
                    Toast.makeText(requireActivity(), "Wrong answer", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void finishTheQuiz() {
        updateDB();
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_thirdFragment_to_fourthFragment);
    }

    @Override
    public void onStop() {
        super.onStop();
        updateDB();
    }

    private void updateDB() {
        //Constants.logD("To be updated the DB" + currentCourse);
        //.updateCourse(currCourse);
    }
}