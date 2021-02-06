package hego.android.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "tag";
    private LinearLayout container;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayList2;
    private Button button;
    private int count = 0;
    private int two;
    private int one;
    private String last_one;
    private String latest;
    private Button ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout)findViewById(R.id.container);
        arrayList = new ArrayList<String>();
        arrayList2 = new ArrayList<>();
        Random random = new Random();

//        ok = (Button)findViewById(R.id.ok);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 int min = 0;
//                 int max = 12;
//                int num = random.nextInt((max - min) + 1) + min;
//                if (!arrayList.contains(String.valueOf(num))){
//                    arrayList.add(String.valueOf(num));
//                }else {
//                    while (arrayList.contains(String.valueOf(num))){
//                        num = new Random().nextInt((max - min) + 1) +min;
//                        Log.e(TAG, "onClick: loop : "+num );
//                        if (arrayList.size() >= 13){
//                            Log.e(TAG, "onClick: breck" );
//                            return;
//                        }
//                    }
//                    arrayList.add(String.valueOf(num));
//                }
//                Log.e(TAG, "onClick: "+num );
//
//                for (String s : arrayList){
//                    Log.e(TAG, "onClick: S : "+s );
//                }
//            }
//        });

        for (int i = 1; i < 10; i++) {
            button = new Button(this);
            button.setText(String.valueOf(i));
            button.setId(i);
            button.setContentDescription(String.valueOf(i));
            button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            container.addView(button);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences sharedPreferences = getSharedPreferences("last",MODE_PRIVATE);
                    int last = sharedPreferences.getInt("last",100);
                    if (last != 100){
                        Button button2 = findViewById(last);
                        button2.setEnabled(false);
                        button2.setBackgroundColor(Color.parseColor("#eeeeee"));
                    }

                    v.setBackgroundColor(Color.parseColor("#0000ff"));
                    arrayList2.add(String.valueOf(v.getId()));

                    int min = 1;
                    int max = 9;
                    int num = random.nextInt((max - min) + 1) + min;
                    if (!arrayList.contains(String.valueOf(num))){
                        arrayList.add(String.valueOf(num));
                    }else {
                        while (arrayList.contains(String.valueOf(num))){
                            num = new Random().nextInt((max - min) + 1) +min;
                            Log.e(TAG, "onClick: loop : "+num );
                            if (arrayList.size() >= 9){
                                Log.e(TAG, "onClick: breck" );
                                return;
                            }
                        }
                        arrayList.add(String.valueOf(num));
                    }

                    Log.e(TAG, "onClick: num : "+num );

                    Button b = findViewById(num);
                    b.setEnabled(true);
                    b.setBackgroundColor(Color.parseColor("#ff0000"));

                    SharedPreferences.Editor editor = getSharedPreferences("last",MODE_PRIVATE).edit();
                    editor.putInt("last",v.getId());
                    editor.apply();


                }
            });
        }


    }
}