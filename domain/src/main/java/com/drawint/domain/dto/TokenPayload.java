package com.drawint.domain.dto;

import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

@Data
public class TokenPayload {
    private String loginAccount;
    private Long uid;

    public TokenPayload() {}

    public TokenPayload(Map<String, Object> claimMap) {
        try {
            BeanUtils.populate(this, claimMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> toPayloadMap() {
        try {
            return BeanUtils.describe(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
