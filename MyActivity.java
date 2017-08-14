package com.example.ListHoro;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class MyActivity extends Activity {

    Item[] itemArray = {new Item(R.drawable.akrep,0,android.R.color.holo_orange_light,"AKREP","21 Ocak - 22 Kasım",0),
            new Item(R.drawable.aslan ,0,android.R.color.holo_blue_light,"ASLAN","21 Ocak - 22 Kasım",0),
            new Item(R.drawable.balik,0,android.R.color.holo_red_light,"BALIK","21 Ocak - 22 Kasım",0),
            new Item(R.drawable.basak,0,android.R.color.holo_purple,"BASAK","21 Ocak - 22 Kasım",0),
            new Item(R.drawable.boga,0,android.R.color.holo_orange_light,"BOGA","21 Ocak - 22 Kasım",0),
            new Item(R.drawable.ikizler,0,android.R.color.holo_blue_light,"IKIZLER","21 Ocak - 22 Kasım",0),
            new Item(R.drawable.koc,0,android.R.color.holo_red_light,"KOC","21 Ocak - 22 Kasım",0),
            new Item(R.drawable.kova,0,android.R.color.holo_purple,"KOVA","21 Ocak - 22 Kasım",0),
            new Item(R.drawable.oglak,0,android.R.color.holo_orange_light,"OGLAK","21 Ocak - 22 Kasım",0),
            new Item(R.drawable.terazi,0,android.R.color.holo_blue_light,"TERAZI","21 Ocak - 22 Kasım",0),
            new Item(R.drawable.yay,0,android.R.color.holo_red_light,"YAY","21 Ocak - 22 Kasım",0),
            new Item(R.drawable.yengec,0,android.R.color.holo_purple,"YENGEC","21 Ocak - 22 Kasım",0)};

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);




        ListView listView = (ListView) findViewById(R.id.list);

        CustomAdapter adapter = new CustomAdapter(this,R.layout.main, Arrays.asList(itemArray));

        listView.setAdapter(adapter);




    }


}
