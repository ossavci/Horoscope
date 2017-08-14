package com.horoscope.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osman on 12.7.2014.
 */
public class HoroscopeCollection {
    private static String HOROSCOPE_SP_KEY = "HOROSCOPE_SP";

    public static String HOROSCOPE_INTENT_KEY = "HOROSCOPE_IK";

    private static String HOROSCOPE_AVAILABLE_KEY = "HOROSCOPE_AK";

    private static List<Zodiac> zodiacList;

    static {
        zodiacList = new ArrayList<Zodiac>();
        zodiacList.add(new Zodiac(HoroscopeIdentifier.KOC, R.drawable.koc,R.drawable.header_leo,R.color.color_blaze,"21 Ocak - 22 Kasım",false));
        zodiacList.add(new Zodiac(HoroscopeIdentifier.BOGA, R.drawable.boga, R.drawable.header_leo, R.color.color_bloored, "21 Ocak - 22 Kasım", false));
        zodiacList.add(new Zodiac(HoroscopeIdentifier.IKIZLER, R.drawable.ikizler, R.drawable.header_leo, R.color.color_cloudblue, "21 Ocak - 22 Kasım", false));
        zodiacList.add(new Zodiac(HoroscopeIdentifier.YENGEC, R.drawable.yengec, R.drawable.header_leo, R.color.color_leafgreen, "21 Ocak - 22 Kasım", false));
        zodiacList.add(new Zodiac(HoroscopeIdentifier.ASLAN, R.drawable.aslan, R.drawable.header_leo, R.color.color_newpoop, "21 Ocak - 22 Kasım", false));
        zodiacList.add(new Zodiac(HoroscopeIdentifier.BASAK, R.drawable.basak, R.drawable.header_leo, R.color.color_nightblue, "21 Ocak - 22 Kasım", false));
        zodiacList.add(new Zodiac(HoroscopeIdentifier.TERAZI, R.drawable.terazi, R.drawable.header_leo, R.color.color_oldpoop, "21 Ocak - 22 Kasım", false));
        zodiacList.add(new Zodiac(HoroscopeIdentifier.AKREP, R.drawable.akrep, R.drawable.header_leo, R.color.color_pigmeat, "21 Ocak - 22 Kasım", false));
        zodiacList.add(new Zodiac(HoroscopeIdentifier.YAY, R.drawable.yay, R.drawable.header_leo, R.color.color_seablue, "21 Ocak - 22 Kasım", false));
        zodiacList.add(new Zodiac(HoroscopeIdentifier.OGLAG, R.drawable.oglak, R.drawable.header_leo, R.color.color_shadegreen, "21 Ocak - 22 Kasım", false));
        zodiacList.add(new Zodiac(HoroscopeIdentifier.KOVA, R.drawable.kova, R.drawable.header_leo, R.color.color_slimgreen, "21 Ocak - 22 Kasım", false));
        zodiacList.add(new Zodiac(HoroscopeIdentifier.BALIK, R.drawable.balik, R.drawable.header_leo, R.color.color_skyblue, "21 Ocak - 22 Kasım", false));
    }

    public enum HoroscopeIdentifier{
        KOC(0, "KOC"),
        BOGA(1, "BOGA"),
        IKIZLER(2, "IKIZLER"),
        YENGEC(3, "YENGEC"),
        ASLAN(4, "ASLAN"),
        BASAK(5, "BASAK"),
        TERAZI(6, "TERAZI"),
        AKREP(7, "AKREP"),
        YAY(8, "YAY"),
        OGLAG(9, "OGLAK"),
        KOVA(10, "KOVA"),
        BALIK(11, "BALIK");

        int idx;
        String name;

        HoroscopeIdentifier(int idx, String name) {
            this.idx = idx;
            this.name = name;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String detailKey(){
            return this.name+"DK";
        }

        String followingKey(){
            return this.name+"FK";
        }
    }

    public static List<Zodiac> getZodiacList(Activity activity){
        for(Zodiac zodiac : zodiacList)
            zodiac.setFollowing(getFollowing(activity, zodiac.getIdentifier()));
        return zodiacList;
    }

    private static void putDetailText(Activity activity, HoroscopeIdentifier identifier, String detailText){
        SharedPreferences sharedPref = activity.getSharedPreferences(HOROSCOPE_SP_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(identifier.detailKey(), detailText);
        editor.commit();
    }

    public static String getDetailText(Activity activity, HoroscopeIdentifier identifier){
        SharedPreferences sharedPref = activity.getSharedPreferences(HOROSCOPE_SP_KEY, Context.MODE_PRIVATE);
        return sharedPref.getString(identifier.detailKey(), "Üzgünüz, bir hata oluştu. "+identifier.getName());
    }

    public static void setFollowing(Activity activity, HoroscopeIdentifier identifier, boolean following){
        SharedPreferences sharedPref = activity.getSharedPreferences(HOROSCOPE_SP_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(identifier.followingKey(), following);
        editor.commit();
    }

    public static boolean getFollowing(Activity activity, HoroscopeIdentifier identifier){
        SharedPreferences sharedPref = activity.getSharedPreferences(HOROSCOPE_SP_KEY, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(identifier.followingKey(), false);
    }

    public static void setDataAvailable(Activity activity, boolean available){
        SharedPreferences sharedPref = activity.getSharedPreferences(HOROSCOPE_SP_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(HOROSCOPE_AVAILABLE_KEY, available);
        editor.commit();
    }

    public static boolean isDataAvailable(Activity activity){
        SharedPreferences sharedPref = activity.getSharedPreferences(HOROSCOPE_SP_KEY, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(HOROSCOPE_AVAILABLE_KEY, false);
    }

    public static Zodiac getZodiac(String identifier){
        HoroscopeIdentifier ident = HoroscopeIdentifier.valueOf(identifier);
        return zodiacList.get(ident.getIdx());
    }

}
