package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Third extends AppCompatActivity {

    SoundPool pool;

    int doo, re, mi, pa, sol, ra, si, doo2, re2, mi2;
    int dooSharp, reSharp, paSharp, solSharp, raSharp, doo2Sharp, re2Sharp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button btnback = (Button) findViewById(R.id.logout);

        btnback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
            }
        });
        pool=new SoundPool(13, AudioManager.STREAM_MUSIC,0);

        doo=pool.load(this, R.raw.doo,1);
        dooSharp = pool.load(this, R.raw.doosharp,1);
        re=pool.load(this, R.raw.re,1);
        reSharp = pool.load(this, R.raw.resharp, 1);
        mi=pool.load(this,R.raw.mi,1);
        pa=pool.load(this,R.raw.pa,1);
        paSharp = pool.load(this, R.raw.pasharp, 1);
        sol=pool.load(this,R.raw.sol,1);
        solSharp=pool.load(this,R.raw.solsharp,1);
        ra=pool.load(this,R.raw.ra,1);
        raSharp=pool.load(this, R.raw.rasharp, 1);
        si=pool.load(this,R.raw.si,1);
        doo2=pool.load(this, R.raw.doo2,1);
    }
    public void mOnClick(View v){

        switch(v.getId())
        {
            case R.id.white1:
                pool.play(doo,1,1,0,0,1);
                break;
            case R.id.black1:
                pool.play(dooSharp,1,1,0,0,1);
                break;
            case R.id.white2:
                pool.play(re,1,1,0,0,1);
                break;
            case R.id.black2:
                pool.play(reSharp,1,1,0,0,1);
                break;
            case R.id.white3:
                pool.play(mi,1,1,0,0,1);
                break;
            case R.id.white4:
                pool.play(pa,1,1,0,0,1);
                break;
            case R.id.black3:
                pool.play(paSharp,1,1,0,0,1);
                break;
            case R.id.white5:
                pool.play(sol,1,1,0,0,1);
                break;
            case R.id.black4:
                pool.play(solSharp,1,1,0,0,1);
                break;
            case R.id.white6:
                pool.play(ra,1,1,0,0,1);
                break;
            case R.id.black6:
                pool.play(raSharp,1,1,0,0,1);
                break;
            case R.id.white7:
                pool.play(si,1,1,0,0,1);
                break;
            case R.id.white8:
                pool.play(doo2,1,1,0,0,1);
                break;
        }
    }
}


