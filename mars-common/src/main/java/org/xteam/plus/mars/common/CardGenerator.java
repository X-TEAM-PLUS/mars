package org.xteam.plus.mars.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018/5/8
 * Time: 21:11
 * 功能:卡生成器
 */
public class CardGenerator {
    private   static  final String PWD_FEATURES="0123456789abcdefghijklmnopqurstuvwxyz";



    /**
     * 生成卡号卡密
     * @param prefix 前缀
     * @param length  卡号长度
     * @param pwdLength 卡密长度
     * @param num  卡的数量
     * @return
     * @throws Exception
     */
    public static List<Card> generate(Integer prefix,Integer length,Integer pwdLength,Integer num) throws Exception{
        List<Card> cards = new ArrayList<>();
        for(int i=0;i<num;i++){
            cards.add(new Card()
                    .setCardNo(getCardNo(prefix,length,num,i))
                    .setCardPwd(getCardPwd(PWD_FEATURES,pwdLength))
            );
        }
        return cards;
    }


    /**
     * 生成卡号卡密
     * @param prefix 前缀
     * @param length  卡号长度
     * @param pwdFeatures 卡密特征
     * @param pwdLength 卡密长度
     * @param num  卡的数量
     * @return
     * @throws Exception
     */
     public static List<Card> generate(Integer prefix,Integer length,String pwdFeatures,Integer pwdLength,Integer num) throws Exception{
         List<Card> cards = new ArrayList<>();
         for(int i=0;i<num;i++){
             cards.add(new Card()
                     .setCardNo(getCardNo(prefix,length,num,i))
                     .setCardPwd(getCardPwd(pwdFeatures,pwdLength))
             );
         }
         return cards;
     }


    /**
     * 获取卡号
     * @param prefix
     * @param length
     * @return
     * @throws Exception
     */
     private static BigDecimal getCardNo(Integer prefix,Integer length,Integer num,Integer currentNum)throws Exception{
         if(length-prefix.toString().length()-num.toString().length()>=0){
             throw  new Exception("无效的卡号长度");
         }
         //序号
        String serialNumber = fillLeft(currentNum.toString(),num.toString().length(),"0");



         return null;
     }

    /**
     * 获取卡密
     * @param pwdFeatures
     * @param pwdLength
     * @return
     * @throws Exception
     */
    private static String getCardPwd(String pwdFeatures,Integer pwdLength)throws Exception{
         return VerifyCodeUtils.generateVerifyCode(pwdLength,pwdFeatures);
    }


    /**
     * 左填充
     * @param str
     * @param width
     * @param inip
     * @return
     */
    private static String fillLeft(String str, int width, String inip) {
        String newStr = "";
        for (int i = str.length(); i < width; i++) {
            newStr = newStr + inip;
        }
        newStr += str;
        return newStr;

    }

    /**
     * 卡信息
     */
    public static class Card{
        private BigDecimal cardNo;
        private String cardPwd;

        public BigDecimal getCardNo() {
            return cardNo;
        }

        public Card setCardNo(BigDecimal cardNo) {
            this.cardNo = cardNo;
            return this;
        }

        public String getCardPwd() {
            return cardPwd;
        }

        public Card setCardPwd(String cardPwd) {
            this.cardPwd = cardPwd;
            return this;
        }
    }


    public static void main(String[] args){
        System.out.println("201806200011".hashCode());
    }
}
