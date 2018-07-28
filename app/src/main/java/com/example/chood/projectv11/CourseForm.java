package com.example.chood.projectv11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CourseForm extends AppCompatActivity {


    TextView tvCourseID, tvCourseName, tvPrerequesite;
    EditText etCourseLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_form);

            tvCourseID = (TextView) findViewById(R.id.tvCourseID);
            tvCourseName = (TextView) findViewById(R.id.tvCourseName);
            etCourseLevel = (EditText) findViewById(R.id.etCourseLevel);
            tvPrerequesite = (TextView) findViewById(R.id.preRequisite);
            //btnSubmitCourse = (TextView) findViewById(R.id.btnSubmitCourse);

        }

        public void btnSubmitCourse(View v)
        {
            String courseID = tvCourseID.getText().toString().trim();
            String courseName = tvCourseName.getText().toString().trim();
            int courseLevel = etCourseLevel.getInputType();
            String preRequesite = tvPrerequesite.getText().toString().trim();


            DatabaseDB db = new DatabaseDB(this);
            db.open();
            db.createEntryCourse(courseID,courseName,courseLevel,preRequesite);
            db.close();

            Toast.makeText(CourseForm.this, "Successfully Saved", Toast.LENGTH_SHORT).show();

            tvCourseID.setText("");
            tvCourseName.setText("");
            etCourseLevel.setText("");
            tvPrerequesite.setText("");
        }
    }

