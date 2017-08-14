package com.horoscope.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.nirhart.parallaxscroll.views.ParallaxListView;

import java.util.Arrays;
import java.util.List;


public class HoroscopeListActivity extends Activity {

    List<Zodiac> zodiacList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_list);

        zodiacList = HoroscopeCollection.getZodiacList(this);

        ParallaxListView listView = (ParallaxListView) findViewById(R.id.list_view);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.main, zodiacList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HoroscopeListActivity.this, HoroscopeDetailActivity.class);
                intent.putExtra(HoroscopeCollection.HOROSCOPE_INTENT_KEY, zodiacList.get(position).getIdentifier().getName());
                startActivity(intent);
            }
        });

    }
}
