package com.example.coursero.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursero.R;
import com.example.coursero.model.Quiz;
import com.example.coursero.util.Constants;

public class QuizAnswerAdaptor extends RecyclerView.Adapter<QuizAnswerAdaptor.QuizAnswerAdaptorListHolder> {

    String[] answerList;
    int currentQuizStatus;
    private OnAnswerClickListener listener;

    public QuizAnswerAdaptor(String[] answerList, int currentQuizStatus) {
        this.answerList = answerList;
        this.currentQuizStatus = currentQuizStatus;
    }

    @NonNull
    @Override
    public QuizAnswerAdaptorListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_quiz_answer, parent, false);
        QuizAnswerAdaptorListHolder rcv = new QuizAnswerAdaptorListHolder(layoutView);
        return rcv;
    }


    @Override
    public void onBindViewHolder(QuizAnswerAdaptorListHolder holder, int position) {
        Constants.logD(answerList[0] + answerList[1] + answerList[2]);
        holder.answerItem.setText(answerList[position]);
    }

    @Override
    public int getItemCount() {
        return this.answerList.length;
    }

    public void submitList(Quiz currQuiz) {
        answerList = currQuiz.getAnswerList();
        notifyDataSetChanged();
    }

    public void setOnEventClickListener(OnAnswerClickListener listener) {
        this.listener = listener;

    }

    public interface OnAnswerClickListener {
        void onItemClick(int position);
    }

    class QuizAnswerAdaptorListHolder extends RecyclerView.ViewHolder {
        public TextView answerItem;

        public QuizAnswerAdaptorListHolder(View itemView) {
            super(itemView);
            answerItem = itemView.findViewById(R.id.single_item_quiz_answer);
            answerItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

}
