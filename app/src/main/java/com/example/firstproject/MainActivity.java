package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText inputId, inputPw;
    String userId, userPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSignup = (Button) findViewById(R.id.signup);
        Button btnLogin = (Button) findViewById(R.id.loginBT);

        inputId = (EditText) findViewById(R.id.userID);
        inputPw = (EditText) findViewById(R.id.userPW);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        SignUp.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = inputId.getText().toString();
                userPw = inputPw.getText().toString();

                if(userId.isEmpty()){
                    Toast.makeText(MainActivity.this,"아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if(userPw.isEmpty()) {
                    Toast.makeText(MainActivity.this,"비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if(checkInfo(userId,userPw)) {
                    Intent intent = new Intent(getApplicationContext(),
                            Third.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,readToTxtFile(userId), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean checkInfo(String id, String pw) {
        File file = new File(getFilesDir(), id);
        if (file.exists()) {
            String savedPw = readToTxtFile(id);
            if(pw.equals(savedPw)) {
                return true;
            }
        }
        return false;
    }



    private String readToTxtFile(String fileName) {
        File file = new File(getFilesDir(), fileName);

        FileReader fr = null;
        BufferedReader br = null;

        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String readStr = "";
            String str = null;
            while ((str = br.readLine()) != null)
                readStr += str + "\n";
            br.close();
            return readStr.substring(0, readStr.length() - 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "입력하신 정보가 일치하지 않습니다.";
    }
}
