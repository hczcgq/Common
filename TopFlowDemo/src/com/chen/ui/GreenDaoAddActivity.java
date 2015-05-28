package com.chen.ui;

import com.chen.R;
import com.chen.dao.Person;
import com.chen.db.ContactDbService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GreenDaoAddActivity extends Activity {
    private ContactDbService instance;

    private TextView tv_name, tv_sex, tv_age, tv_mobile, tv_email, tv_hometown,
            tv_job, tv_descibe;

    private String name, sex, age, mobile, email, hometown, job, descibe;

    private Person person;

    int type;

    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_greendao_add);
        instance = ContactDbService.getInstance(this);

        type = getIntent().getIntExtra("type", 0);
        if (type == GreenDaoActivity.INTENT_UPDATE) {
            id = getIntent().getLongExtra("id", 1);
            person = instance.loadPerson(id);
        }

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_hometown = (TextView) findViewById(R.id.tv_hometowm);
        tv_job = (TextView) findViewById(R.id.tv_job);
        tv_descibe = (TextView) findViewById(R.id.tv_des);

        if (person != null) {
            tv_name.setText(person.getName());
            tv_sex.setText(person.getSex());
            tv_age.setText(person.getAge());
            tv_email.setText(person.getEmail());
            tv_mobile.setText(person.getMobile());
            tv_hometown.setText(person.getHometown());
            tv_job.setText(person.getJob());
            tv_descibe.setText(person.getDescribe());
        }
    }

    public void save(View view) {
        name = tv_name.getText().toString();
        sex = tv_sex.getText().toString();
        age = tv_age.getText().toString();
        mobile = tv_mobile.getText().toString();
        email = tv_email.getText().toString();
        hometown = tv_hometown.getText().toString();
        job = tv_job.getText().toString();
        descibe = tv_descibe.getText().toString();

        if (isEmpty(name)) {
            Toast.makeText(this, "姓名不能为空", 0).show();
            return;
        }
        if (isEmpty(mobile)) {
            Toast.makeText(this, "手机不能为空", 0).show();
            return;
        }

        long index;
        if (type == GreenDaoActivity.INTENT_UPDATE) {
            index = instance.savePerson(new Person(id, name, mobile, age, sex,
                    email, hometown, job, descibe));

        } else {
            index = instance.savePerson(new Person(null, name, mobile, age,
                    sex, email, hometown, job, descibe));

        }

        if (index > 0) {
            setResult(GreenDaoActivity.REQUEST_ADD);
            finish();
        }

    }

    private boolean isEmpty(String input) {
        if (input == null || "".equals(input) || input.equals("null"))
            return true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
}
