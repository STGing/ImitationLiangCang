package com.example.pc.imitationliangcang.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.utils.SPUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.title_iv_back)
    ImageView titleIvBack;
    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;
    @BindView(R.id.login_userName)
    EditText loginUserName;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.login_forget_pwd)
    TextView loginForgetPwd;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.login_app_weibo)
    ImageView loginAppWeibo;
    @BindView(R.id.login_app_weixin)
    ImageView loginAppWeixin;
    @BindView(R.id.login_app_qq)
    ImageView loginAppQq;
    @BindView(R.id.login_app_douban)
    ImageView loginAppDouban;

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.title_iv_back, R.id.login_forget_pwd, R.id.login_btn, R.id.login_app_weibo, R.id.login_app_weixin, R.id.login_app_qq, R.id.login_app_douban})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back://点击返回
                finish();
                break;
            case R.id.login_forget_pwd://点击忘记密码
                Toast.makeText(this, "忘记密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_btn://点击登录

                //获取用户名和密码
                String name = loginUserName.getText().toString().trim();
                String pwd = loginPwd.getText().toString().trim();

                //校验（没有服务器，所以无法进行其他校验）
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    //跳转
                    Intent intent = new Intent(LoginActivity.this, ShopCarActivity.class);
                    startActivity(intent);
                }


                //保存登录状态(为了登录验证，这里暂停保存false)
                SPUtils.put(this, "isLogin", false);

                finish();

                break;
            case R.id.login_app_weibo://点击使用新浪微博登录

                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.SSOSetting(false);  //设置false表示使用SSO授权方式
                weibo.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                }); // 设置分享事件回调

                weibo.authorize();//单独授权
                weibo.showUser(null);//授权并获取用户信息

                break;
            case R.id.login_app_weixin://点击使用微信登录

                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);

                wechat.SSOSetting(false);
                wechat.authorize();
                wechat.showUser(null);

                break;
            case R.id.login_app_qq://点击使用qq登录

                Platform qq = ShareSDK.getPlatform(QQ.NAME);

                qq.SSOSetting(false);

                qq.authorize();

                qq.showUser(null);


                break;
            case R.id.login_app_douban://点击使用豆瓣登录


                break;
        }
    }
}
