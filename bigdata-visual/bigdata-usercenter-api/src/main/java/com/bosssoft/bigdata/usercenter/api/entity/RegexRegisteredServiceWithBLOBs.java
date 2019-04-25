package com.bosssoft.bigdata.usercenter.api.entity;

import lombok.Data;

@Data
public class RegexRegisteredServiceWithBLOBs extends RegexRegisteredService {
    private byte[] accessStrategy;

    private byte[] attributeRelease;

    private byte[] expirationPolicy;

    private byte[] mfaPolicy;

    private byte[] proxyPolicy;

    private byte[] publicKey;

    private byte[] requiredHandlers;

    private byte[] usernameAttr;



}