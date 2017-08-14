package com.horoscope.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.R.color.*;
import static android.R.color.holo_green_dark;
import static android.R.color.holo_green_light;
import static android.R.color.holo_orange_light;

public class HoroscopeDetailActivity extends Activity {

    Zodiac zodiac;

    LinearLayout followingButtonLayout;
    LinearLayout shareButtonLayout;
    TextView followingTextView;
    TextView commentTextView;
    ImageView headerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final String zodiacKey = intent.getStringExtra(HoroscopeCollection.HOROSCOPE_INTENT_KEY);
        zodiac = HoroscopeCollection.getZodiac(zodiacKey);

        FadingActionBarHelper helper = new FadingActionBarHelper()
                .actionBarBackground(zodiac.getColor())
                .headerLayout(R.layout.header)
                .contentLayout(R.layout.activity_horoscope_detail);
        setContentView(helper.createView(this));
        helper.initActionBar(this);

        //TODO if data available fetch and save

        followingButtonLayout = (LinearLayout) findViewById(R.id.followingButtonLayout);
        shareButtonLayout = (LinearLayout) findViewById(R.id.shareButtonLayout);
        followingTextView = (TextView) findViewById(R.id.followingTextView);
        headerImageView = (ImageView) findViewById(R.id.image_header);
        commentTextView = (TextView) findViewById(R.id.commentText);


        commentTextView.setText(HoroscopeCollection.getDetailText(this, zodiac.getIdentifier()));

        headerImageView.setImageResource(zodiac.getImage());

        setTitle(zodiac.getIdentifier().getName());
        getActionBar().setIcon(zodiac.getIcon());

        followingButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zodiac.setFollowing(!zodiac.isFollowing());

                determineFollowButtonState();

                HoroscopeCollection.setFollowing(HoroscopeDetailActivity.this, zodiac.getIdentifier(), zodiac.isFollowing());
            }
        });

        determineFollowButtonState();

        shareButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
    }

    private void determineFollowButtonState(){
        if(zodiac.isFollowing()){
            followingButtonLayout.setBackgroundColor(getResources().getColor(holo_green_light));
            followingTextView.setText("Takip ediliyor");
        }else {
            followingButtonLayout.setBackgroundColor(getResources().getColor(holo_orange_light));
            followingTextView.setText("Takip et");
        }

    }

    private void share(){
        String shareItem = "www.google.com.tr Yov biç!";

        List<Intent> targetedShareIntents = new ArrayList<Intent>();

        Intent facebookIntent = getShareIntent("katana", zodiac.getIdentifier().getName(), shareItem);
        if(facebookIntent != null)
            targetedShareIntents.add(facebookIntent);

        Intent twitterIntent = getShareIntent("twitter", zodiac.getIdentifier().getName(), shareItem);
        if(twitterIntent != null)
            targetedShareIntents.add(twitterIntent);

        Intent gmailIntent = getShareIntent("gmail", zodiac.getIdentifier().getName(), shareItem);
        if(gmailIntent != null)
            targetedShareIntents.add(gmailIntent);

        Intent whatsappIntent = getShareIntent("whatsapp", zodiac.getIdentifier().getName(), shareItem);
        if(whatsappIntent != null)
            targetedShareIntents.add(whatsappIntent);

        Intent chooser = Intent.createChooser(targetedShareIntents.remove(0), "Paylaş");

        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));

        startActivity(chooser);
    }

    private Intent getShareIntent(String type, String subject, String text)
    {
        boolean found = false;
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");

        // gets the list of intents that can be loaded.
        List<ResolveInfo> resInfo = this.getPackageManager().queryIntentActivities(share, 0);
        System.out.println("resinfo: " + resInfo);
        if (!resInfo.isEmpty()){
            for (ResolveInfo info : resInfo) {
                if (info.activityInfo.packageName.toLowerCase().contains(type) ||
                        info.activityInfo.name.toLowerCase().contains(type) ) {
                    share.putExtra(Intent.EXTRA_SUBJECT,  subject);
                    share.putExtra(Intent.EXTRA_TEXT,     text);
                    share.setPackage(info.activityInfo.packageName);
                    found = true;
                    break;
                }
            }
            if (!found)
                return null;

            return share;
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
