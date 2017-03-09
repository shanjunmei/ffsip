package com.ffzx.ffsip.pingxx;

import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by sunkai on 15/5/19. webhooks 验证签名示例
 * <p>
 * 该实例演示如何对 ping++ webhooks 通知进行验证。
 * 验证是为了让开发者确认该通知来自 ping++ ，防止恶意伪造通知。用户如果有别的验证机制，可以不进行验证签名。
 * <p>
 * 验证签名需要 签名、公钥、验证信息，该实例采用文件存储方式进行演示。
 * 实际项目中，需要用户从异步通知的 HTTP header 中读取签名，从 HTTP body 中读取验证信息。公钥的存储方式也需要用户自行设定。
 * <p>
 * 该实例仅供演示如何验证签名，请务必不要直接 copy 到实际项目中使用。
 */
public class WebHooksVerify {


    private static String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuxpMIqv3WjOanOEviLgX\n" +
            "wUKuVExniHAjdwUGaDiv6f6SfPf+r8GEV4jfcxdZ0ttcjCWHXSeNiQ5RnUJ7gs5E\n" +
            "hdcjiKHP9fBGzCBCmRivSqoC20c6h9LvKzefMnaiThVJeQb73aOSCysqKYWr8ldq\n" +
            "o4ZvwP+urRvPl3DiXlahR2bTOuHUGA1kRQXZcgUtY3Sxi3SwftqzIwK2c240Hu/f\n" +
            "A0b0+Y65LYMPTxTWsuSrTS4TPX8RIbl3pHfeRFnPFvZ3q2SPtCwf5dPlivN7vgFG\n" +
            "g1+/7lNUk2fqILzzGtXUhvqi1oii5WqMlSIXBVjmzgKrwXra+ruc6QN+KIiZojNS\n" +
            "bQIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";

    /**
     * 验证webhooks 签名，仅供参考
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String data = "{\"id\":\"evt_401170220094735288418201\",\"created\":1487555253,\"livemode\":true,\"type\":\"refund.succeeded\",\"data\":{\"object\":{\"id\":\"re_WHOyjD40GqH4CaT0aLP0OSiL\",\"object\":\"refund\",\"order_no\":\"WHOyjD40GqH4CaT0aLP0OSiL\",\"amount\":3,\"created\":1487555248,\"succeed\":true,\"status\":\"succeeded\",\"time_succeed\":1487555255,\"description\":\"客户申请退款\",\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"charge\":\"ch_Sqv1eHfnnrPCenTqnDXLavv5\",\"charge_order_no\":\"14873175930041\",\"transaction_no\":\"2005712001201702200851697736\",\"funding_source\":\"unsettled_funds\"}},\"object\":\"event\",\"request\":\"iar_vr5K4SzvLKm9rTS084OGyrb9\",\"pending_webhooks\":0}";
        String sign = "o0cu65Wzn9Rm7inwZGlov+AeWwrXMq71iUGlw9SmzbP10o0zOb4Lpm87bUaHqGhdZCAkCSLmRP8CXJsGFxgZfjdZhqBe8W4I7XixOQPeV/sczEfShpyjfshmrylFMWCsnHG4ap9rXeFkxMEAVYJcs809ByHHo6ZS7J4rPsWPSBPJDHv9Um3H9v67Amk4r3id0R8+S3bW+iLJg/hdr9R6o37B7+jvtOfvG/uSYLOxer432ZM0S0+W3JJQ3WpAcjaCN2VGmZSIXt+3pjNOWLI9Ly1loead7zWQpW3OV728gu2/pP3lCfoL4jMQfABrRDVfdDgmmOR4849FnWI8LVtWWw==";

        boolean result = verifyData(getByte(data, false), getByte(sign, true), getPubKey());
        System.out.println("验签结果：" + result);
    }

    /**
     * 读取文件,部署web程序的时候，签名和验签内容需要从request中获得
     *
     * @param src
     * @param base64
     * @return
     * @throws Exception
     */
    public static byte[] getByte(String src, boolean base64) throws Exception {
        byte[] fileBytes = src.getBytes();
        String pubKey = new String(fileBytes, "UTF-8");
        if (base64) {
            Base64 decoder = new Base64();
            fileBytes = decoder.decode(pubKey);
        }
        return fileBytes;
    }

    /**
     * 获得公钥
     *
     * @return
     * @throws Exception
     */
    public static PublicKey getPubKey() throws Exception {
// read key bytes

        byte[] keyBytes = publicKey.getBytes();
        String pubKey = new String(keyBytes, "UTF-8");
        pubKey = pubKey.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
        keyBytes = Base64.decodeBase64(pubKey);
// generate public key
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(spec);
        return publicKey;
    }

    /**
     * 验证签名
     *
     * @param data
     * @param sigBytes
     * @param publicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verifyData(byte[] data, byte[] sigBytes, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(sigBytes);
    }

    public static boolean verifyData(String data, String sign) {
        try {
            return verifyData(getByte(data, false), getByte(sign, true), getPubKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}