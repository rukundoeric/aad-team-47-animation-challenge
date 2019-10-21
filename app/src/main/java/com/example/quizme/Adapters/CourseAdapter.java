package com.example.quizme.Adapters;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.quizme.MainActivity;
import com.example.quizme.Models.GetCourseModel;
import com.example.quizme.R;
import com.example.quizme.Utils.Animations;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    Context context;
    ArrayList<GetCourseModel> course;

    public CourseAdapter(Context context, ArrayList<GetCourseModel> course) {
        this.context = context;
        this.course = course;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.course_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.course_name.setText(this.course.get(position).getName());
      holder.no_Questions.setText(this.context.getString(R.string.questions) + this.course.get(position).getNo_questions().toString());
        Glide.with(this.context)
                .load(this.course.get(position).getIcon())
                .apply(new RequestOptions()
                        .optionalFitCenter()
                        .placeholder(R.drawable.no_course_image)
                        .centerCrop())
                .into(holder.course_icon);
    }

    @Override
    public int getItemCount() {
        return this.course.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView course_name;
        TextView no_Questions;
        RoundedImageView course_icon;
        RelativeLayout course_card;

        public ViewHolder(final View itemView) {
            super(itemView);
            course_icon = (RoundedImageView) itemView.findViewById(R.id.course_icon);
            no_Questions = (TextView) itemView.findViewById(R.id.number_of_questions);
            course_name = (TextView) itemView.findViewById(R.id.course_name);
            course_card = (RelativeLayout) itemView.findViewById(R.id.course_card);
            course_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animations.translationAnimation(itemView, 0f, 200f).addListener(new ObjectAnimator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            context.startActivity(new Intent(context, MainActivity.class));
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
            });
        }
    }
}
