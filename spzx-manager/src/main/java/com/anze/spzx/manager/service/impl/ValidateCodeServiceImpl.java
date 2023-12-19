package com.anze.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.anze.spzx.manager.service.ValidateCodeService;
import com.anze.spzx.model.vo.system.ValidateCodeVo;
import com.anze.spzx.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ValidateCodeServiceImpl implements ValidateCodeService {
    private final RedisCache redisCache;
    @Override
    public ValidateCodeVo generateValidateCode() {
        //使用hutool工具包中的工具类生成验证码
        //参数：宽  高  验证码位数  干扰线数目
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 20);
        String codeVal = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();
        // 生成uuid作为图片验证码的key
        String codeKey = UUID.randomUUID().toString().replace("-", "");

        // 将验证码存储到Redis中
        redisCache.setCacheObject("user:login:validatecode:" + codeKey , codeVal , 5 , TimeUnit.MINUTES);

        // 构建响应结果数据
        ValidateCodeVo validateCodeVo = new ValidateCodeVo() ;
        validateCodeVo.setCodeKey(codeKey);
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);

        // 返回数据
        return validateCodeVo;
    }
}
