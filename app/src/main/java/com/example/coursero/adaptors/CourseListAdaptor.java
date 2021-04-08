package com.example.coursero.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursero.R;

public class CourseListAdaptor extends RecyclerView.Adapter<CourseListAdaptor.CourseViewListHolder> {

    private final String[] courseTitleList;
    private final String[] coursesDurationList;
    private onCourseSelectListener listener;


    public CourseListAdaptor(String[] courseTitleList, String[] coursesDurationList) {
        this.courseTitleList = courseTitleList;
        this.coursesDurationList = coursesDurationList;
    }

    @NonNull
    @Override
    public CourseViewListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_single_item_course, parent, false);
        CourseViewListHolder rcv = new CourseViewListHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewListHolder holder, int position) {
        holder.courseTitle.setText(courseTitleList[position]);
        holder.courseDuration.setText(coursesDurationList[position]);
    }

    @Override
    public int getItemCount() {

        return this.courseTitleList.length;
    }

    public void setOnCourseSelectListener(onCourseSelectListener listener) {
        this.listener = listener;

    }

    public interface onCourseSelectListener {
        void onCourseListItemClick(int position);
    }

    class CourseViewListHolder extends RecyclerView.ViewHolder {
        public TextView courseTitle;
        public TextView courseDuration;

        public CourseViewListHolder(View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseDuration = itemView.findViewById(R.id.courseDuration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onCourseListItemClick(position);
                    }
                }
            });


        }


    }
}
