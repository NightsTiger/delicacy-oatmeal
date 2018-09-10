package com.delicacy.oatmeal.txmail.service;


import com.alibaba.fastjson.JSON;
import com.delicacy.oatmeal.txmail.constant.TxMailConstant;
import com.delicacy.oatmeal.txmail.dto.BaseResDto;
import com.delicacy.oatmeal.txmail.dto.mailgroup.MailGroupCreateDto;
import com.delicacy.oatmeal.txmail.dto.mailgroup.MailGroupUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import static com.delicacy.oatmeal.txmail.util.TxMailUtil.getAccessToken;
import static com.delicacy.oatmeal.txmail.util.TxMailUtil.getBaseResDto;

/**
 * @author yutao
 * @create 2018-08-16 11:12
 **/
@Slf4j
@RestController
public class MailGroupSerivice {


    public BaseResDto createMailGroup(MailGroupCreateDto groupCreateDto) {
        log.info("入参:{}", JSON.toJSONString(groupCreateDto));
        return getBaseResDto(TxMailConstant.MailGroup.CREATE,groupCreateDto);
    }

    public BaseResDto deleteMailGroup(String groupid) {
        log.info("入参:{}",groupid);
        Map<String, String> map = new HashMap<>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        map.put("groupId", groupid);
        return getBaseResDto(map, TxMailConstant.MailGroup.DELETE);
    }


    public BaseResDto updateMailGroup(MailGroupUpdateDto mailGroupUpdateDto) {
        log.info("入参:{}", JSON.toJSONString(mailGroupUpdateDto));
        return getBaseResDto(TxMailConstant.MailGroup.UPDATE,mailGroupUpdateDto);
    }

   /* private BaseResDto getBaseResDto(Map<String, String> map, String url, ResponseEntity<BaseResDto> forEntity) {
        String accessToken = null;
        if (forEntity.getBody().getErrcode().equals(TxMailConstant.ResResult.TOKEN_EXPIRE.getCode())) {
            accessToken = getAccessToken(url);
            forEntity = RestClientUtil.getRestTemplate().getForEntity(url, BaseResDto.class, map);
        }
        return forEntity.getBody();
    }

    private BaseResDto getBaseResDto(HttpEntity<Object> entity, String url) {
        Map<String, String> map = new HashMap<>();
        String key = url;
        String accessToken = getAccessToken(key);
        map.put("access_token", accessToken);
        ResponseEntity<BaseResDto> forEntity = RestClientUtil.getRestTemplate().postForEntity(url, entity, BaseResDto.class, map);
        if (forEntity.getBody().getErrcode().equals(TxMailConstant.ResResult.TOKEN_EXPIRE.getCode())) {
            accessToken = getAccessToken(key);
            forEntity = RestClientUtil.getRestTemplate().postForEntity(url, entity, BaseResDto.class, map);
        }
        return forEntity.getBody();
    }

    private String getAccessToken(String rediskey) {
        String s = RedisCacheUtil.get(rediskey);
        if (StringUtils.isNotBlank(s)) {
            return s;
        }
        String corpid = TxMailConstant.CORPID;
        String corpsecret = TxMailConstant.ATTRLIST_CORPSECRET;
        Map<String, String> map = new HashMap<>();
        map.put("corpid", corpid);
        map.put("corpsecret", corpsecret);
        ResponseEntity<TokenDto> tokenResponse = RestClientUtil.getRestTemplate().getForEntity(TxMailConstant.GETTOKEN, TokenDto.class, map);
        if (tokenResponse.getBody().getErrcode() != TxMailConstant.ResResult.SUSSCESS.getCode()) {
            log.error("获取accessToken失败,error={}", tokenResponse.getBody().getErrmsg());
            throw new RuntimeException("获取accessToken失败");
        }
        String access_token = tokenResponse.getBody().getAccess_token();
        RedisCacheUtil.set(rediskey, access_token, 3 * 60 * 60);
        return access_token;
    }*/

}
