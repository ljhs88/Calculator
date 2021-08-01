package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.regex.Pattern;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView editText;

    private StringBuffer sb;

    private int num = 0;

    private int point = 0;

    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }

        editText = (TextView) findViewById(R.id.edit_text);//获取edit实例
        editText.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        sb = new StringBuffer();
        setButtonOnClickListener();//获取button实例
    }

    public void setButtonOnClickListener() {
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Button button3 = (Button) findViewById(R.id.button_3);
        Button button4 = (Button)findViewById(R.id.button_4);
        Button button5 = (Button)findViewById(R.id.button_5);
        Button button6 = (Button)findViewById(R.id.button_6);
        Button button7 = (Button)findViewById(R.id.button_7);
        Button button8 = (Button)findViewById(R.id.button_8);
        Button button9 = (Button)findViewById(R.id.button_9);
        Button button10 = (Button)findViewById(R.id.button_10);
        Button button11 = (Button)findViewById(R.id.button_11);
        Button button12 = (Button) findViewById(R.id.button_12);
        Button button13 = (Button) findViewById(R.id.button_13);
        Button button14 = (Button) findViewById(R.id.button_14);
        Button button15 = (Button) findViewById(R.id.button_15);
        Button button16 = (Button) findViewById(R.id.button_16);
        Button button17 = (Button) findViewById(R.id.button_17);
        Button button18 = (Button) findViewById(R.id.button_18);
        Button button19 = (Button) findViewById(R.id.button_19);
        Button button20 = (Button) findViewById(R.id.button_20);
        Button button21 = (Button) findViewById(R.id.button_21);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);
        button15.setOnClickListener(this);
        button16.setOnClickListener(this);
        button17.setOnClickListener(this);
        button18.setOnClickListener(this);
        button19.setOnClickListener(this);
        button21.setOnClickListener(this);
        button20.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_16:
                try {
                    if(sb.length()==0) {
                    } else if(sb.charAt(0)!='E'){
                        actionClass ac = new actionClass(sb);
                        String d =ac.getAnswer();
                        editText.setText("");
                        editText.setText(d);
                        sb.replace(0,sb.length(),d);
                        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
                        if(pattern.matcher(d).matches()) {
                            point=0;
                        } else {
                            point=1;
                        }
                    } else {
                        sb.replace(0,sb.length(),"");
                        editText.setText("");
                    }
//                    point=0;
                }catch(Exception exception) {
                    sb.replace(0,sb.length(),"Error");
                    editText.setText("Error");
//                    point=0;
                }
                break;
            case R.id.button_4:
                /*if(sb.length()==0) {
                }else {
                    if(sb.charAt(sb.length()-1)=='(') {
                        num--;
                    } else if(sb.charAt(sb.length()-1)==')') {
                        num++;
                    } else if(sb.charAt(sb.length()-1)=='.') {
                        point=0;
                    }
                    sb.deleteCharAt(sb.length()-1);
                    editText.setText(sb.toString());
                }*/
                if(sb.length()==0) {
                } else {
                    if(sb.charAt(sb.length()-1)=='.') {
                        point=0;
                    }
                    sb.deleteCharAt(sb.length()-1);
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_1:
                num=0;
                point=0;
                sb.replace(0,sb.length(),"");//清空
                editText.setText("");
                break;
            case R.id.button_20:
                /*if(sb.length()==0||sb.charAt(sb.length()-1) == '-'||sb.charAt(sb.length()-1) == '+'||
                        sb.charAt(sb.length()-1) == '*'||sb.charAt(sb.length()-1) == '/'||sb.charAt(sb.length()-1)=='(') {
                    sb.append("(");
                    editText.setText(sb.toString());
                    num++;
                }*/
                if(sb.charAt(sb.length()-1)=='-'&&(sb.charAt(sb.length()-2)=='/'
                        ||sb.charAt(sb.length()-2)=='*'||sb.charAt(sb.length()-2)=='+')) {
                    sb.replace(sb.length()-1,sb.length(),"(");
                    sb.append("-");
                } else {
                    sb.append("(");
                }
                point=0;
                editText.setText(sb.toString());
                break;
            case R.id.button_21:
                /*if(sb.length()==0){
                }else if(num>0&&sb.charAt(sb.length()-1) != '-'&&sb.charAt(sb.length()-1) != '+'&&
                        sb.charAt(sb.length()-1) != '*'&&sb.charAt(sb.length()-1) != '/'&&sb.charAt(sb.length()-1)!='%'&&
                        sb.charAt(sb.length()-1)!='.'||sb.charAt(sb.length()-1)==')') {
                    sb.append(")");
                    editText.setText(sb.toString());
                    num--;
                }*/
                point=0;
                sb.append(")");
                editText.setText(sb.toString());
                break;
            case R.id.button_2:
                if (sb.length()==0) {
                } else {
                    if(sb.charAt(sb.length()-1) != '-'&&sb.charAt(sb.length()-1) != '+'&&
                            sb.charAt(sb.length()-1) != '*'&&sb.charAt(sb.length()-1) != '/'
                            &&sb.charAt(sb.length()-1) != '(') {
                        sb.append("/");
                        point=0;
                    } else {
                        if(sb.charAt(sb.length()-1)=='-'&&(sb.charAt(sb.length()-2)=='/'
                                ||sb.charAt(sb.length()-2)=='*'||sb.charAt(sb.length()-2)=='+')) {
                            sb.replace(sb.length()-2,sb.length(),"/");
                        }
                        if(sb.length()>1&&sb.charAt(sb.length()-2)!='('){
                            sb.setCharAt(sb.length()-1,'/');
                        } else {
                        }
                        //sb.setCharAt(sb.length()-1,'/');
                    }
                    editText.setText(sb.toString());//将新字符串显示
                }
                break;
            case R.id.button_3:
                if (sb.length()==0) {
                } else {
                    if(sb.charAt(sb.length()-1) != '-'&&sb.charAt(sb.length()-1) != '+'&&
                            sb.charAt(sb.length()-1) != '*'&&sb.charAt(sb.length()-1) != '/'&&sb.charAt(sb.length()-1) != '(') {
                        sb.append("*");
                        point=0;
                    } else {
                        if(sb.charAt(sb.length()-1)=='-'&&(sb.charAt(sb.length()-2)=='/'
                                ||sb.charAt(sb.length()-2)=='*'||sb.charAt(sb.length()-2)=='+')) {
                            sb.replace(sb.length()-2,sb.length(),"*");
                        }
                        if(sb.length()>1&&sb.charAt(sb.length()-2)!='('){
                            sb.setCharAt(sb.length()-1,'*');
                        } else {
                        }
                        //sb.setCharAt(sb.length()-1,'*');
                    }
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_8:
                if(sb.length()==0){
                    sb.append("-");
                }else if(sb.charAt(sb.length()-1) != '-'&&sb.charAt(sb.length()-1) != '+') {
                    sb.append("-");
                    point=0;
                } else {
                    sb.setCharAt(sb.length()-1,'-');
                }
                editText.setText(sb.toString());
                break;
            case R.id.button_12:
                if (sb.length()==0) {
                } else {
                    if(sb.charAt(sb.length()-1) != '-'&&sb.charAt(sb.length()-1) != '+'&&
                            sb.charAt(sb.length()-1) != '*'&&sb.charAt(sb.length()-1) != '/'&&sb.charAt(sb.length()-1) != '(') {
                        sb.append("+");
                        point=0;
                    } else {
                        if(sb.charAt(sb.length()-1)=='-'&&(sb.charAt(sb.length()-2)=='/'
                                ||sb.charAt(sb.length()-2)=='*'||sb.charAt(sb.length()-2)=='+')) {
                            sb.replace(sb.length()-2,sb.length(),"+");
                        }
                        if(sb.length()>1&&sb.charAt(sb.length()-2)!='('){
                            sb.setCharAt(sb.length()-1,'+');
                        } else {
                        }
                        //sb.setCharAt(sb.length()-1,'+');
                    }
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_5:
                if(sb.length()==0||sb.charAt(sb.length()-1)!='%') {
                    sb.append("7");
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_6:
                if(sb.length()==0||sb.charAt(sb.length()-1)!='%') {
                    sb.append("8");
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_7:
                if(sb.length()==0||sb.charAt(sb.length()-1)!='%') {
                    sb.append("9");
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_9:
                if(sb.length()==0||sb.charAt(sb.length()-1)!='%') {
                    sb.append("4");
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_10:
                if(sb.length()==0||sb.charAt(sb.length()-1)!='%') {
                    sb.append("5");
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_11:
                if(sb.length()==0||sb.charAt(sb.length()-1)!='%') {
                    sb.append("6");
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_13:
                if(sb.length()==0||sb.charAt(sb.length()-1)!='%') {
                    sb.append("1");
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_14:
                if(sb.length()==0||sb.charAt(sb.length()-1)!='%') {
                    sb.append("2");
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_15:
                if(sb.length()==0||sb.charAt(sb.length()-1)!='%') {
                    sb.append("3");
                    editText.setText(sb.toString());
                }
                break;
            case R.id.button_18:
                sb.append("0");
                editText.setText(sb.toString());
                break;
//            case R.id.button_17:
//                if(sb.length()==0) {
//                }else if(sb.charAt(sb.length()-1)-'0'>=0&&sb.charAt(sb.length()-1)-'0'<=9) {
//                    sb.append("%");
//                    editText.setText(sb.toString());
//                } else {
//                }
//                break;
            case R.id.button_19:
                 /*if (sb.length()==0||(sb.charAt(sb.length()-1)-'0'>=0&&sb.charAt(sb.length()-1)-'0'<=9&&point==0)) {
                    sb.append(".");
                    point=1;
                    editText.setText(sb.toString());
                }*/
                if (point==0) {
                    sb.append(".");
                    point=1;
                    editText.setText(sb.toString());
                }
                break;
            default:
                break;
        }
    }

}
