/*
 * Copyright (C) 2013 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gms.example.bannerexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.graphics.Color;
import java.util.ArrayList;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Main Activity. Inflates main activity xml and child fragments.
 */
public class MyActivity extends ActionBarActivity
{

    private AdView myBannerAd;
    private Button textChangeButton;
    private TextView textChangeTextView;
    private RelativeLayout background;
    private ArrayList<String> randomText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Gets the ad view defined in the xml.
        myBannerAd = (AdView) findViewById(R.id.ad_view);
        textChangeButton = (Button) findViewById(R.id.textchangebutton);
        textChangeTextView = (TextView) findViewById(R.id.buttonchangetextview);
        background = (RelativeLayout) findViewById(R.id.background);
        randomText = new ArrayList<String>();
        buildRandomText();

        // Create an ad request.
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        myBannerAd.loadAd(adRequest);
        setupListeners();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handels if the add is clicked
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Called when activity is exitted
    @Override
    public void onPause() {
        if (myBannerAd != null) {
            myBannerAd.pause();
        }
        super.onPause();
    }

    // Called when activity is running
    @Override
    public void onResume() {
        super.onResume();
        if (myBannerAd != null) {
            myBannerAd.resume();
        }
    }

    //Called before activity is closed fully
    @Override
    public void onDestroy() {
        if (myBannerAd != null) {
            myBannerAd.destroy();
        }
        super.onDestroy();

    }
   private void buildRandomText()
    {
        //Adds strings to the random text
        randomText.add("Am I green?");
        randomText.add("I feel a little BLUE today");
        randomText.add("I want a dozen violets...");
        randomText.add("Make yourself yellow to be happy...");
        randomText.add("Magenta SKY");

    }
    private  String randomMessage()
    {
//Picks a random line from the ArrayList
        String randomTopic = "";
        int random = (int) (Math.random() * randomText.size());
        randomTopic = randomText.get(random);
        return randomTopic;
    }
    private void changeColor()
    {
        int redColor;
        int greenColor;
        int blueColor;
//Creates random number between 0 and 255
        redColor = (int) (Math.random() * 256);
        greenColor = (int) (Math.random() * 256);
        blueColor = (int) (Math.random() *256);
// Changes background to the randomly assigned color
        background.setBackgroundColor(Color.rgb(redColor, greenColor, blueColor));

        redColor = (int) (Math.random() * 256);
        greenColor = (int) (Math.random() * 256);
        blueColor = (int) (Math.random() *256);

        textChangeButton.setBackgroundColor(Color.rgb(redColor, greenColor, blueColor));


        redColor = (int) (Math.random() * 256);
        greenColor = (int) (Math.random() * 256);
        blueColor = (int) (Math.random() *256);

        textChangeTextView.setBackgroundColor(Color.rgb(redColor, greenColor, blueColor));


    }
    private void setupListeners()
    {
        textChangeButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View buttonView)
            {
                //Runs color change and set text view to the random messages value
                changeColor();
                textChangeTextView.setText(randomMessage());
            }
        });
    }
}
