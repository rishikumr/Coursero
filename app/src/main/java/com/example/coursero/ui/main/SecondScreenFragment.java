package com.example.coursero.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.coursero.R;
import com.example.coursero.model.Course;
import com.example.coursero.util.Constants;
import com.example.coursero.util.StringListConverter;
import com.example.coursero.viewmodel.CourseViewModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.PlayerUiController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class SecondScreenFragment extends Fragment implements View.OnClickListener {

    private static final DecimalFormat df = new DecimalFormat("0.0");
    private final HashMap<Float, String> notesMap = new HashMap<>();
    private NavController navController;
    private CourseViewModel mViewModel;
    private Course currentCourse;
    private YouTubePlayerView youTubePlayerView;
    private float currentTimeStamp;
    private Button add_note_btn;
    private YouTubePlayer mYouTubePlayer;
    private ArrayList<String> savedNotes;

    public SecondScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(CourseViewModel.class);
        currentCourse = mViewModel.getCurrentSelectedCourse();
        //Get Current status from stored value
        currentTimeStamp = currentCourse.getCompletedUpTo();
        savedNotes = currentCourse.getSavedNotes();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getActivity().getLifecycle().addObserver(youTubePlayerView);

        add_note_btn = view.findViewById(R.id.button_add_note);
        add_note_btn.setOnClickListener(this);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            // Called when the player is ready to play videos.
            // You should start using the player only after this method is called.
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = currentCourse.getUrl();
                youTubePlayer.loadVideo(videoId, currentTimeStamp);
                if (currentTimeStamp == 0) {
                    currentCourse.setIsCompleted(false);
                }
                mYouTubePlayer = youTubePlayer;
            }

            // Called periodically by the player, the argument is the number of seconds that have been played.
            @Override
            public void onCurrentSecond(YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);
                videoWatched(second);
            }

            // Called when an error occurs in the player.
            @Override
            public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError error) {
                super.onError(youTubePlayer, error);
            }

            // Called every time the state of the player changes.
            @Override
            public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState state) {
                super.onStateChange(youTubePlayer, state);
                switch (state) {


                    case ENDED:
                        videoFinished();
                        break;
                    case PAUSED:
                        videoPaused(youTubePlayer);
                        break;
                    case PLAYING:
                        break;
                }
            }

            // Called when the total duration of the video is loaded.
            @Override
            public void onVideoDuration(YouTubePlayer youTubePlayer, float duration) {
                super.onVideoDuration(youTubePlayer, duration);
            }

            // Called when the id of the current video is loaded
            @Override
            public void onVideoId(YouTubePlayer youTubePlayer, String videoId) {
                super.onVideoId(youTubePlayer, videoId);
            }

            // Called periodically by the player, the argument is the percentage of the video that has been buffered.
            @Override
            public void onVideoLoadedFraction(YouTubePlayer youTubePlayer, float loadedFraction) {
                super.onVideoLoadedFraction(youTubePlayer, loadedFraction);
            }
        });

        PlayerUiController playerUiController = this.youTubePlayerView.getPlayerUiController();
        playerUiController.showFullscreenButton(false);
        playerUiController.showYouTubeButton(false);
        playerUiController.showMenuButton(false);
        /*LayoutInflater inflater=(LayoutInflater) requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView=inflater.inflate(R.layout.field, null);
        playerUiController.addView(rowView);*/

        Constants.logD(String.valueOf(currentCourse.getCourseId()));
        mViewModel.getLiveSavedNotes(currentCourse.getCourseId()).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String updatedNotesList) {
                Constants.logD("onChanged updatedNotesList" + updatedNotesList);
                savedNotes.clear();
                savedNotes = StringListConverter.fromString(updatedNotesList);
                for (int i = 0; i < savedNotes.size(); i++) {
                    notesMap.put(Float.valueOf(savedNotes.get(i).split(Constants.DIFFERENTIATORS_SIGN)[0]), savedNotes.get(i).split(Constants.DIFFERENTIATORS_SIGN)[1]);
                }

            }
        });


        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

    }

    private void videoWatched(float second) {
        currentTimeStamp = Float.parseFloat(df.format(second));
        boolean isthere = notesMap.containsKey(currentTimeStamp);
        Constants.logD("  currentTimeStamp =" + currentTimeStamp + "  isthere=" + isthere);
        if (isthere) {
            showBottomSheetNotes(CourseBottomSheetDialog.BOTTOM_SHEET_MODE.SEE_NOTE, notesMap.get(currentTimeStamp));
        }
    }

    private void videoPaused(YouTubePlayer youTubePlayer) {
        currentCourse.setCompletedUpTo(currentTimeStamp);
        updateDB();
    }

    private void videoFinished() {
        currentCourse.setCompletedUpTo(0);
        currentCourse.setIsCompleted(true);
        updateDB();
        navController.navigate(R.id.action_secondScreenFragment_to_thirdFragment);
    }

    private void updateDB() {
        //Constants.logD("To be updated the DB" + currentCourse);
        mViewModel.updateCourse(currentCourse);
    }

    private void showBottomSheetNotes(CourseBottomSheetDialog.BOTTOM_SHEET_MODE noteMODE, String note) {
        CourseBottomSheetDialog bottomSheet = new CourseBottomSheetDialog(noteMODE, mYouTubePlayer, currentTimeStamp, note);
        mYouTubePlayer.pause();
        bottomSheet.show(requireActivity().getSupportFragmentManager(), "CourseBottomSheet");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        updateDB();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_note:
                showBottomSheetNotes(CourseBottomSheetDialog.BOTTOM_SHEET_MODE.EDIT_NOTE, "");
        }
    }
}