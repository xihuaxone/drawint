package com.drawint.common.utils;

import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.PrivateKey;
import java.security.PublicKey;

public class SecretKeyUtil {
    private static final RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(),
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMmyZ3AChvKMTv1E\n" +
                    "4b5kxNHcwUSkhwp2iU7mhD4xWiDdyf/g5Plxuudddg+b2pRZm6ONPAnw2TYmdREn\n" +
                    "IX4PmQb86rnQvgZvoo2gqnsn3ONDZ9kE5tLMwvv15VoD/MP3mobzGyFthkqo/WJX\n" +
                    "Dcf3mOV2baylJA1LrCjIzNAKigQ7AgMBAAECgYEAiEUmqYCrl/e/7lG5ix8d8wQI\n" +
                    "982JB0Kq5knMExDY6devAFf7m6SBOIvmlgkglpYsF0X2yGiW+ITnFx2u/TKgmemL\n" +
                    "g+6t9drjWHVCB2ux2d1/1n9UgWZXoAh8fLr8B0hzWyMwMbQLqU4vdu/EtlPoMiVc\n" +
                    "c9/qRJ5kjSEwJdXAp2ECQQD6ZpfYE+rpmaFxFwNig8oEb6KpSJm2LtI7sCntcS4i\n" +
                    "BkKGfOpVmX5wLUjffO5mIMNVjfAtOVq61iXiLIdX5AeJAkEAzjUCDjUhSev+t+Sd\n" +
                    "ffLnmM598IZFa90gEkVYp7rO62gwupTpxKxBUBXaup+aphJx/6wZUdORXRtHPdRE\n" +
                    "RQl4owJAesDQr50UDHDENCyT/DefUY1osJRmxmktpxU1eLGDbCqlckjKmCwnT9bj\n" +
                    "YqxcMrTdDZQHqiPz1qQk7jvmHbAsAQJAbTJS6VUnUK8BTz5oTDcif5/xvzpLbuhF\n" +
                    "QIeqgAAD7VNaYqPS+0xAtfkswCEe0wz3g24OZm+EDesRGePbVzfBwwJAGU6iuehc\n" +
                    "rLEx/GqnvBXhCtePvcgp/gm/QajdkcvCADtwN6pGPvR+wbyIkPq7WuPcTIbtZbQi\n" +
                    "qPMXRAveaCaOoA==",
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJsmdwAobyjE79ROG+ZMTR3MFE\n" +
                    "pIcKdolO5oQ+MVog3cn/4OT5cbrnXXYPm9qUWZujjTwJ8Nk2JnURJyF+D5kG/Oq5\n" +
                    "0L4Gb6KNoKp7J9zjQ2fZBObSzML79eVaA/zD95qG8xshbYZKqP1iVw3H95jldm2s\n" +
                    "pSQNS6woyMzQCooEOwIDAQAB");

    public static PublicKey getPublicKey() {
        return rsa.getPublicKey();
    }

    public static PrivateKey getPrivateKey() {
        return rsa.getPrivateKey();
    }
}
