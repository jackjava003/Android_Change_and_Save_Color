package com.example.jack.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvTextSelect, tvText, tvTextGreen, tvTextBlue, textView;
    private SeekBar sbSize, sbSizeGreen, sbSizeBlue;
    private LinearLayout linearLayout, mainLayout;
    private EditText editText;
    private Button submit;
    private RadioButton radioButton,rgb, hex;

    int red = 128, green = 128, blue = 128;
    String radioButtonType = "";
    String radioButtonCheck = "";
    String hexCode = null;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();


    }

    private void findViews() {
        tvTextSelect = (TextView) findViewById(R.id.tvTextSelect);
        tvText = (TextView) findViewById(R.id.tvText);
        sbSize = (SeekBar) findViewById(R.id.sbSize);
        tvTextGreen = (TextView) findViewById(R.id.tvTextGreen);
        sbSizeGreen = (SeekBar) findViewById(R.id.sbSizeGreen);
        tvTextBlue = (TextView) findViewById(R.id.tvTextBlue);
        sbSizeBlue = (SeekBar) findViewById(R.id.sbSizeBlue);
        editText = (EditText) findViewById(R.id.editText);
        submit = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        linearLayout = (LinearLayout) findViewById(R.id.colorBox);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        rgb = (RadioButton) findViewById(R.id.rgb);
        //hex = (RadioButton) findViewById(R.id.hex);


        red = sbSize.getProgress();
        green = sbSizeGreen.getProgress();
        blue = sbSizeBlue.getProgress();
        RadioGroup colorType = (RadioGroup) findViewById(R.id.colorType);
        textView.setText("RGB = " + String.valueOf(red) + "," + String.valueOf(green) + "," + String.valueOf(blue));


        colorType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //int i 傳回當前被點擊的button id  (R.id.xxxxx)唯一個int值   用此id存入變數radioButton
                radioButton = (RadioButton) radioGroup.findViewById(i);
                radioButtonType = (String) radioButton.getText();
                //初始design介面時  有給radio buttom id  在上方也有使用findViewById 存放至rgb  (因為只有2個選項  所以使用一個判斷即可)
                //當被點集的radioButton 與 rgb 是相同內容  則可以確定  使用者選擇的項目是rgb  若不是即為Hex color code
                //針對不同選項給予不同的hint
                if (radioButton==rgb) {
                    editText.setHint(radioButtonType + " " + getString(R.string.format) + ": 128,121,28");
                } else {
                   editText.setHint(radioButtonType + " " + getString(R.string.format) + ": #8B008B");
                }

            }
        });

        //seekBar元件呼叫setOnCheckedChangeListener向OnSeekBarChangeListener註冊監聽器
        //實作OnSeekBarChangeListener必須override其內3個方法
        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;
                hexCode = null;
                tvText.setTextColor(Color.rgb(red, 0, 0));
                tvTextSelect.setBackgroundColor(Color.rgb(red, green, blue));
                tvTextSelect.setTextColor(Color.rgb(red+128, green+128, blue+128));
                textView.setText("RGB = " + String.valueOf(red) + "," + String.valueOf(green) + "," + String.valueOf(blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, getString(R.string.startSize) + " = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, getString(R.string.stopSize) + " = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        sbSizeGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;
                hexCode = null;
                tvTextGreen.setTextColor(Color.rgb(0, green, 0));
                tvTextSelect.setBackgroundColor(Color.rgb(red, green, blue));
                tvTextSelect.setTextColor(Color.rgb(red+128, green+128, blue+128));
                textView.setText("RGB = " + String.valueOf(red) + "," + String.valueOf(green) + "," + String.valueOf(blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, getString(R.string.startSize) + " = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, getString(R.string.stopSize) + " = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        sbSizeBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;
                hexCode = null;
                tvTextBlue.setTextColor(Color.rgb(0, 0, blue));
                tvTextSelect.setBackgroundColor(Color.rgb(red, green, blue));
                tvTextSelect.setTextColor(Color.rgb(red+128, green+128, blue+128));
                textView.setText("RGB = " + String.valueOf(red) + "," + String.valueOf(green) + "," + String.valueOf(blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, getString(R.string.startSize) + " = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, getString(R.string.stopSize) + " = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton==rgb) {
                    hexCode = null;
                    String insert = editText.getText().toString().trim();
                    String[] strArr;
                    try {
                        if (insert.indexOf(",") != -1) {
                            strArr = insert.split(",");
                            if (strArr.length != 3) {
                                throw new Exception();
                            } else {
                                red = Integer.parseInt(strArr[0]);
                                green = Integer.parseInt(strArr[1]);
                                blue = Integer.parseInt(strArr[2]);
                                tvTextSelect.setBackgroundColor(Color.rgb(red, green, blue));
                                tvTextSelect.setTextColor(Color.rgb(red+128, green+128, blue+128));
                                textView.setText("RGB = " + String.valueOf(red) + "," + String.valueOf(green) + "," + String.valueOf(blue));
                            }
                        } else if (insert.indexOf(" ") != -1) {
                            strArr = insert.split(" ");
                            if (strArr.length != 3) {
                                throw new Exception();
                            } else {
                                red = Integer.parseInt(strArr[0]);
                                green = Integer.parseInt(strArr[1]);
                                blue = Integer.parseInt(strArr[2]);
                                tvTextSelect.setBackgroundColor(Color.rgb(red, green, blue));
                                tvTextSelect.setTextColor(Color.rgb(red+128, green+128, blue+128));
                                textView.setText("RGB = " + String.valueOf(red) + "," + String.valueOf(green) + "," + String.valueOf(blue));
                            }
                        } else {
                            textView.setText(getString(R.string.ERRO));
                        }
                    } catch (Exception e) {
                        textView.setText(getString(R.string.ERRO));
                    }
                } else {
                    String insert = editText.getText().toString().trim();
                    try {
                        tvTextSelect.setBackgroundColor(Color.parseColor(insert));
                        textView.setText("Code = " + insert);
                        hexCode = insert;
                    } catch (Exception e) {
                        textView.setText(getString(R.string.ERRO));
                    }

                }
            }
        });
    }

    public void onCreate(View view) {
        count++;
        Button bt = new Button(this);
        //當使用者拉動seekBat或是選擇自行輸入RGB並submit  hexCode會改回null值
        //此時可以確定  使用者是使用RGB進行改色
        if (hexCode == null) {
            bt.setBackgroundColor(Color.rgb(red, green, blue));
            String color = red + "|" + green + "|" + blue;
            bt.setTag(color);
            bt.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
            bt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String tag = (String) view.getTag();
                    hexCode = null;
                    String[] colorArr = tag.split("\\|");
                    red = Integer.parseInt(colorArr[0]);
                    green = Integer.parseInt(colorArr[1]);
                    blue = Integer.parseInt(colorArr[2]);
                    tvTextSelect.setBackgroundColor(Color.rgb(red, green, blue));
                    textView.setText("RGB = " + String.valueOf(red) + "," + String.valueOf(green) + "," + String.valueOf(blue));
                }
            });
        } else {
            bt.setBackgroundColor(Color.parseColor(hexCode));
            bt.setTag(hexCode);
            bt.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
            bt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String tag = (String) view.getTag();
                    tvTextSelect.setBackgroundColor(Color.parseColor(tag));
                    textView.setText("Code = " + tag);
                    hexCode = tag;
                }
            });
        }

        //每存放11個buttom就會新增一個LinearLayout
        if (count % 12 == 0) {
            LinearLayout addLinear = new LinearLayout(this);
            addLinear.setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.addView(addLinear);
            count++;

        }
        //mainLayout為外圍的大LinearLayout 在ScrollView之內
        //由於確定此mainLayout的最後一個child 一定會是LinearLayout (初始design有放入)
        //直接將button放入最後一個LinearLayout  (若滿11個button 會由上面程式碼動態新增一個LinearLayout
        //此新增的LinearLayout 則為 mainLayout的最後一個child   (getChildCount() - 1  原因為child從0開始  count會回傳有幾個)
        LinearLayout lastLayout = (LinearLayout) mainLayout.getChildAt(mainLayout.getChildCount() - 1);
        lastLayout.addView(bt);


    }

    public void onClear(View view) {

        View checkLayout = null;
        //預防使用者按clear建把其他內容刪除   在此進行判斷
        //當mainLayout的最後一個child為LinearLayout  以及  mainLayout的最後一個child不等於包覆save color和clear button的linearLayout
        //才進行刪除
        //並重製count為11  使用者下次submit  count會+1  變為12   12%12 = 0  符合新增linearLayout的條件
        while (((checkLayout = (View) mainLayout.getChildAt(mainLayout.getChildCount() - 1)) instanceof LinearLayout) && (checkLayout != linearLayout)) {
            mainLayout.removeView(checkLayout);

        }
        count = 11;
    }
}
