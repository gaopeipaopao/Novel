package com.example.gaope.novel.Prove;

import com.example.gaope.novel.Base.BaseView;

public interface ProveView extends BaseView {

    //提醒用户网络没有连接
    void showNoNetwork();

    //提示用户先要获得验证码
    void showGetNumber();

    //提示用户手机号位数错误
    void showRightLengthAccount();

    //提示用户验证码已经发送
    void  showHaveSendNumber();

    //从得到验证码开始计时，5分钟后就要重新获取短信验证码
    void loseEffect();

    //手机号已经注册
    void accountHaveExist();

    //验证码正确,打开注册活动
    void intentNewAcitvity(String regiesterMessage);

    //验证码输入错误
    void showMistakeNumber();

    //验证码已失效
    void showLoseEffectNumber();

    //提示用户手机号不存在
    void showMistake();


}
