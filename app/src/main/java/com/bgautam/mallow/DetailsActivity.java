package com.bgautam.mallow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bgautam.mallow.network.CustomVolleyRequestQueue;
import com.bgautam.mallow.pojo.Employee;
import com.bgautam.mallow.pojo.Skill;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private NetworkImageView imageView;
    private ImageLoader imageLoader;

    private TextView firstName;
    private TextView lastName;
    private TextView address;
    private TextView city;
    private TextView zipcode;

    private TextView gender;
    private TextView dob;
    private TextView designation;
    private TextView mobile;
    private TextView email;
    private TextView nationality;
    private TextView language;
    private TextView techSkillSet;
    private TextView extraSkillSet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initializeView();

        setData();
    }

    private void setData() {
        Bundle extras = getIntent().getExtras();
        Employee data = (Employee) extras.get("Employee");


        imageView.setImageUrl(data.getImageURL(),imageLoader);
        firstName.setText(data.getFirstName());
        lastName.setText(data.getLastName());
        address.setText(data.getAddress());
        city.setText(data.getCity());
        zipcode.setText(data.getZipcode());

        gender.setText(data.getGender());
        dob.setText(data.getDob());
        designation.setText(data.getDesignation());
        mobile.setText(data.getMobile());
        email.setText(data.getEmail());

        nationality.setText(data.getNationality());
        language.setText(data.getLanguage());
        List<Skill> skills = data.getSkills();
        String skillText =  "";
        String extraSkillText =  "";

        for (Skill skill : skills) {

            if (skill.getTechnical() != null) {
                skillText += skill.getTechnical();
                skillText += ",";
            }

            if(skill.getExtraCurricular()!=null) {
                extraSkillText += skill.getExtraCurricular();
                extraSkillText += ",";
            }
        }

        techSkillSet.setText(skillText);
        extraSkillSet.setText(extraSkillText);

    }





    private void initializeView() {

        imageLoader = CustomVolleyRequestQueue.getInstance(this)
                .getImageLoader();

        imageView = (NetworkImageView) findViewById(R.id.imageView);

        firstName = (TextView)findViewById(R.id.firsNameText);
        lastName = (TextView)findViewById(R.id.lastNameText);
        address = (TextView)findViewById(R.id.addressText);
        city = (TextView)findViewById(R.id.cityText);
        zipcode = (TextView)findViewById(R.id.zipcodeText);

        gender = (TextView)findViewById(R.id.genderText);
        dob = (TextView)findViewById(R.id.dobText);
        designation = (TextView)findViewById(R.id.designationText);
        mobile = (TextView)findViewById(R.id.phoneText);
        email = (TextView)findViewById(R.id.emailText);

        nationality = (TextView)findViewById(R.id.nationalityText);
        language = (TextView)findViewById(R.id.languageText);
        techSkillSet = (TextView)findViewById(R.id.technicalSkillSetText);
        extraSkillSet = (TextView)findViewById(R.id.extraCurricularSkillSetText);

    }
}
