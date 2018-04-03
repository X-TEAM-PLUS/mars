package org.xteam.plus.mars.common.qrcode;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

public class QRCodeBuilder {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    private static void writeToFile(BitMatrix matrix, String format, File file)   throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format "   + format + " to " + file);
        }
    }

    /**
     * @param file      源文件(图片)
     * @param waterFile 水印文件(图片)
     * @param x         距离右下角的X偏移量
     * @param y         距离右下角的Y偏移量
     * @param alpha     透明度, 选择值从0.0~1.0: 完全透明~完全不透明
     * @return BufferedImage
     * @throws IOException
     * @Title: 构造图片
     * @Description: 生成水印并返回java.awt.image.BufferedImage
     */
    private static BufferedImage watermark(File file, File waterFile, int x, int y, float alpha) throws IOException {
        // 获取底图
        BufferedImage buffImg = ImageIO.read(file);
        // 获取层图
        BufferedImage waterImg = ImageIO.read(waterFile);
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = buffImg.createGraphics();
        // 获取层图的宽度
        int waterImgWidth = waterImg.getWidth();
        // 获取层图的高度
        int waterImgHeight = waterImg.getHeight();
        // 在图形和图像中实现混合和透明效果
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        // 绘制
        g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
        g2d.dispose();// 释放图形上下文使用的系统资源
        return buffImg;
    }


    /**
     * 输出水印图片
     *
     * @param buffImg  图像加水印之后的BufferedImage对象
     * @param savePath 图像加水印之后的保存路径
     */

    private static void generateWaterFile(BufferedImage buffImg, String savePath) {
        int temp = savePath.lastIndexOf(".") + 1;
        try {
            ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param text           二维码内容
     * @param width          二维码图片宽度
     * @param height         二维码图片高度
     * @param format         二维码的图片格式
     * @param sourceFilePath 底层图片路径
     * @param waterFilePath  二维码路径
     * @param saveFilePath   合成图片路径
     * @param maginx         二维码距离底图x轴距离
     * @param maginy         二维码距离底图y轴距离
     * @throws Exception
     */

    public static void addImageQRcode(
            String text
            , int width
            , int height
            , String format
            ,String sourceFilePath
            , String waterFilePath
            , String saveFilePath
            , int maginx
            , int maginy) throws Exception {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text,BarcodeFormat.QR_CODE, width, height, hints);
        // 生成二维码
        File outputFile = new File(waterFilePath);
        writeToFile(bitMatrix, format, outputFile);
        // 构建叠加层
        BufferedImage buffImg = watermark(new File(sourceFilePath), new File(waterFilePath), maginx, maginy, 1.0f);
        // 输出水印图片
        generateWaterFile(buffImg, saveFilePath);
        pressText("给老子去死",saveFilePath,buffImg,Color.MAGENTA,24,maginx,maginy);
    }

    /**
     * @param pressText 文字
     * @param newImg    带文字的图片
     * @param image     需要添加文字的图片
     * @param color
     * @param fontSize
     * @param width
     * @param height
     * @为图片添加文字
     */
    public static void pressText(String pressText, String newImg, BufferedImage image, Color color, int fontSize, int width, int height) {
        //计算文字开始的位置
        //x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = (width - (fontSize * pressText.length())) - 10;
        //y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = height - (height - width) / 2 - 30;

        System.out.println("startX: " + startX);
        System.out.println("startY: " + startY);
        System.out.println("height: " + height);
        System.out.println("width: " + width);
        System.out.println("fontSize: " + fontSize);
        System.out.println("pressText.length(): " + pressText.length());

        try {
            int imageW = image.getWidth();
            int imageH = image.getHeight();
            Graphics g = image.createGraphics();
            g.drawImage(image, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font("粗体", Font.BOLD, 30));
            g.drawString(pressText, startX, startY);
            g.dispose();

            FileOutputStream out = new FileOutputStream(newImg);
            ImageIO.write(image, "JPEG", out);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
            System.out.println("image press success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            addImageQRcode("http://www.baidu.com", 200, 200,"jpg",
                    "d:/qrcode/backgroud.jpg",
                    "d:/qrcode/qrcode.jpg",
                    "d:/qrcode/bk-qrcode.png",170,380);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
