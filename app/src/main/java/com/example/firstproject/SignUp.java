package com.example.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    EditText inputId, inputPw, inputName, inputPhoneNumber, inputAddress;
    String userId, password, userName, phoneNumber, address, infoCheck;
    RadioGroup infoAgree;
    boolean idOK;
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        idOK = false;
        Button btnback = (Button) findViewById(R.id.backBT);
        Button btnSignUp = (Button) findViewById(R.id.signupBT);
        Button btnOverlap = (Button) findViewById(R.id.overlapCheckBT);

        inputId = (EditText) findViewById(R.id.insertID);
        inputPw = (EditText) findViewById(R.id.insertPW);
        inputName = (EditText) findViewById(R.id.insertName);
        inputPhoneNumber = (EditText) findViewById(R.id.insertPhoneNumber);
        inputAddress = (EditText) findViewById(R.id.insertAddress);
//        infoAgree = (RadioGroup) findViewById(R.id.infoAgree);

        btnback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
            }
        });

        btnOverlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = inputId.getText().toString();
                if (userId.isEmpty()) {
                    Toast.makeText(SignUp.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_GRANTED) {
                            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            requestPermissions(permissions, WRITE_EXTERNAL_STORAGE_CODE);
                        } else {
                            checkOverlap(userId);
                        }
                    } else {
                        checkOverlap(userId);
                    }
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioButton rb = (RadioButton) findViewById(R.id.radioYes);

                password = inputPw.getText().toString();
                userName = inputName.getText().toString();
                phoneNumber = inputPhoneNumber.getText().toString();
                address = inputAddress.getText().toString();

                if (!idOK) {
                    Toast.makeText(SignUp.this, "아이디 중복 체크를 해주세요.", Toast.LENGTH_SHORT).show();
                } else if (checkPw(password)) {
                    if (userName.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
                        Toast.makeText(SignUp.this, "입력하지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show();
                    } else if (!rb.isChecked()) {
                        Toast.makeText(SignUp.this, "개인정보 약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUp.this, "회원가입이 왑료되었습니다.", Toast.LENGTH_SHORT).show();
                        saveToTxtFile(userId, password);
                        Intent intent = new Intent(getApplicationContext(),
                                MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });


    }

    private void checkOverlap(String fileName) {
        File file = new File(getFilesDir(), fileName);
        if(file.exists()) {
            Toast.makeText(this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
            idOK = true;
        }
    }

    private void  saveToTxtFile(String id, String pw) {
        FileOutputStream fos = null;
        BufferedOutputStream bufos = null;
        String fileName = id;
        File file = new File(getFilesDir(), fileName);

        try {
            fos = new FileOutputStream(file);
            bufos = new BufferedOutputStream(fos);
            bufos.write(pw.getBytes());
            bufos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void readToTxtFile(String fileName) {
//        File file = new File(getFilesDir(), fileName);
//
//        FileReader fr = null;
//        BufferedReader br = null;
//
//        try {
//
//            fr = new FileReader(file);
//            br = new BufferedReader(fr);
//
//            String readStr = "";
//            String str = null;
//            while ((str = br.readLine()) != null)
//                readStr += str + "\n";
//            br.close();
//            Toast.makeText(this,
//                    readStr.substring(0, readStr.length() - 1) +"ㅎㅎ", Toast.LENGTH_SHORT).show();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private boolean checkPw(String pw) {
        String pwPattern = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$";
        Matcher matcher = Pattern.compile(pwPattern).matcher(pw);

        if(pw.isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!matcher.matches()) {
            Toast.makeText(this, "비밀번호가 안전하지 않습니다(영문(대소문자 구분), 숫자, 특수문자 조합, 9~12자리)", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
