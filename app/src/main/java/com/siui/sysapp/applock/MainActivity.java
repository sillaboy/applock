package com.siui.sysapp.applock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.siui.sysapp.applock.views.BlurLockView;
import com.siui.sysapp.applock.views.Password;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        BlurLockView.OnPasswordInputListener,
        BlurLockView.OnMidButtonClickListener {


    private BlurLockView blurLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        init();
    }

    private void init() {
        blurLockView = (BlurLockView)findViewById(R.id.blurlockview);

        // Set the password
        blurLockView.setCorrectPassword("1234");

        blurLockView.setTitle("请输入密码");
        blurLockView.setLeftButton("");
        blurLockView.setMidButton("取消");
        blurLockView.setRightButton(getIntent().getStringExtra("右边"));
        blurLockView.setType(getPasswordType(), false);

        blurLockView.setOnMidButtonClickListener(this);
        blurLockView.setOnPasswordInputListener(this);
    }

    private Password getPasswordType() {
        if ("PASSWORD_NUMBER".equals(getIntent().getStringExtra("PASSWORD_TYPE")))
            return Password.NUMBER;
        else if ("PASSWORD_NUMBER".equals(getIntent().getStringExtra("PASSWORD_TYPE")))
            return Password.TEXT;
        return Password.NUMBER;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClick() {
        finish();
    }

    @Override
    public void correct(String inputPassword) {
        Toast.makeText(this,
                "密码正确",
                Toast.LENGTH_SHORT).show();
//        blurLockView.hide(
//                500,
//                HideType.FROM_TOP_TO_BOTTOM,
//                EaseType.EaseInCirc);
        finish();
    }

    @Override
    public void incorrect(String inputPassword) {
        Toast.makeText(this,
                "密码错误",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void input(String inputPassword) {

    }
}
