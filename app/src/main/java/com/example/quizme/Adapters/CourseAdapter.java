package com.example.quizme.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.quizme.R;

import java.util.ArrayList;

public class CourseAdapter extends Adapter<CourseAdapter.ViewHolder>{

    private static final String TAG = "CourseAdapter";

    private ArrayList<String> mCourseTitle = new ArrayList<>();
    private ArrayList<String> mCourseIcon = new ArrayList<>();
    private Context context;

    public CourseAdapter(ArrayList<String> mCourseTitle, ArrayList<String> mCourseIcon, Context context) {
        this.mCourseTitle = mCourseTitle;
        this.mCourseIcon = mCourseIcon;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.course_title.setText(mCourseTitle.get(position));
    }

    @Override
    public int getItemCount() {
        return mCourseTitle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView course_icon;
        TextView course_title;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView){
            super(itemView);

            course_icon = itemView.findViewById(R.id.course_icon);
            course_title = itemView.findViewById(R.id.course_name);
            parentLayout = itemView.findViewById(R.id.course_card);

        }

    }

}
