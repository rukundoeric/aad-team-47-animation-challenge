package com.example.quizme;

import android.os.Bundle;

import com.example.quizme.Adapters.CourseAdapter;
import com.example.quizme.Models.GetCourseModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  private TextView mText;
  private ArrayList<String> mCoursesList;
  DatabaseReference reference;
  RecyclerView recyclerView;
  ArrayList<GetCourseModel> course;
  CourseAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    showDialog();

    recyclerView = (RecyclerView) findViewById(R.id.course_recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    reference = FirebaseDatabase.getInstance().getReference().child("courses");
    reference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        course= new ArrayList<GetCourseModel>();

        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
          GetCourseModel c = dataSnapshot1.getValue(GetCourseModel.class);
          course.add(c);
        }

        adapter = new CourseAdapter(MainActivity.this, course);
        recyclerView.setAdapter(adapter);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        Toast.makeText(MainActivity.this,"Something went wrong", Toast.LENGTH_LONG);

      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }


  private void showDialog() {
    ViewGroup viewGroup = findViewById(android.R.id.content);

    View dialogWelcome = LayoutInflater.from(this).inflate(R.layout.welcome_dialog, viewGroup, false);
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setView(dialogWelcome);


    final AlertDialog alertDialog = builder.create();
    alertDialog.show();
    alertDialog.setCancelable(false);

    final Button dismissDialog = dialogWelcome.findViewById(R.id.dismissDialog);
    dismissDialog.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        alertDialog.dismiss();
      }
    });
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}