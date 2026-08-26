package com.wheremydestination.app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.graphics.drawable.GradientDrawable;

public class MainActivity extends Activity {
    LinearLayout root; EditText start, destination;
    int navy=Color.rgb(7,31,84), green=Color.rgb(88,197,29), blue=Color.rgb(18,103,232), red=Color.rgb(240,68,46);
    int dp(float v){return (int)(v*getResources().getDisplayMetrics().density+0.5f);}
    TextView tv(String text,float size,int color){ TextView t=new TextView(this); t.setText(text); t.setTextSize(size); t.setTextColor(color); t.setGravity(Gravity.CENTER_VERTICAL); return t; }
    GradientDrawable bg(int color,float r){ GradientDrawable g=new GradientDrawable(); g.setColor(color); g.setCornerRadius(dp(r)); return g; }
    @Override public void onCreate(Bundle b){super.onCreate(b); showHome();}
    void showHome(){
        ScrollView sv=new ScrollView(this); root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(16),dp(12),dp(16),dp(28)); root.setBackgroundColor(Color.rgb(246,248,252)); sv.addView(root); setContentView(sv);
        LinearLayout bar=new LinearLayout(this); bar.setGravity(Gravity.CENTER_VERTICAL); TextView title=tv("Where Is My ",22,navy); TextView dest=tv("Destination",22,green); title.setTypeface(null,1); dest.setTypeface(null,1); bar.addView(title); bar.addView(dest,new LinearLayout.LayoutParams(-2,dp(52))); root.addView(bar);
        TextView sub=tv("Smart Routes.  Multiple Options.  Perfect Destination.",13,Color.DKGRAY); sub.setGravity(Gravity.CENTER); root.addView(sub,new LinearLayout.LayoutParams(-1,dp(38)));
        ImageView promo=new ImageView(this); promo.setImageResource(com.wheremydestination.app.R.drawable.brand_promo_alt); promo.setScaleType(ImageView.ScaleType.CENTER_CROP); root.addView(promo,new LinearLayout.LayoutParams(-1,dp(250)));
        start=field("Starting Location","Your current location",green); destination=field("Destination","Enter destination",red); root.addView(start); root.addView(destination);
        LinearLayout modes=new LinearLayout(this); modes.setGravity(Gravity.CENTER); String[] ms={"All","Bike","Car","Auto","Bus","Walk"}; for(String m:ms){ TextView x=tv(m,12,navy); x.setGravity(Gravity.CENTER); modes.addView(x,new LinearLayout.LayoutParams(0,dp(52),1)); } root.addView(modes);
        Button nav=new Button(this); nav.setText("START NAVIGATION"); nav.setTextColor(Color.WHITE); nav.setTextSize(16); nav.setTypeface(null,1); nav.setAllCaps(false); nav.setBackground(bg(blue,28)); nav.setOnClickListener(v->navigate()); LinearLayout.LayoutParams np=new LinearLayout.LayoutParams(-1,dp(58)); np.topMargin=dp(12); root.addView(nav,np);
        Button clear=new Button(this); clear.setText("CLEAR"); clear.setTextColor(navy); clear.setAllCaps(false); clear.setBackground(bg(Color.WHITE,28)); clear.setOnClickListener(v->{start.setText(""); destination.setText("");}); root.addView(clear,new LinearLayout.LayoutParams(-1,dp(54)));
        TextView features=tv("\nLIVE LOCATION     •     MULTIPLE TRANSPORT OPTIONS\nBEST ROUTE & TIME ESTIMATE     •     ESTIMATED FARE\nNAVIGATION     •     SAVE PLACES",13,Color.DKGRAY); features.setGravity(Gravity.CENTER); root.addView(features);
    }
    EditText field(String label,String hint,int accent){ LinearLayout box=new LinearLayout(this); box.setOrientation(LinearLayout.VERTICAL); box.setPadding(dp(14),dp(8),dp(14),dp(6)); box.setBackground(bg(Color.WHITE,16)); TextView l=tv(label,12,accent); l.setTypeface(null,1); box.addView(l,new LinearLayout.LayoutParams(-1,dp(24))); EditText e=new EditText(this); e.setHint(hint); e.setTextSize(15); e.setSingleLine(true); e.setPadding(0,0,0,0); box.addView(e,new LinearLayout.LayoutParams(-1,dp(42))); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,dp(82)); p.bottomMargin=dp(10); root.addView(box,p); return e; }
    void navigate(){ String d=destination.getText().toString().trim(); if(d.isEmpty()){destination.setError("Enter a destination"); return;} Uri uri=Uri.parse("google.navigation:q="+Uri.encode(d)); Intent i=new Intent(Intent.ACTION_VIEW,uri); i.setPackage("com.google.android.apps.maps"); try{startActivity(i);}catch(Exception e){startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/maps/dir/?api=1&destination="+Uri.encode(d))));} }
}
