package org.xteam.plus.mars.common.qrcode.drawing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class QrCodeDrawingImpl extends Drawing {
    /**
     * 二维码链接地址
     */
    private String url;

    /**
     * 显示的大小
     */
    private Size size;

    /**
     * 显示的位置
     */
    private Position position;

    public QrCodeDrawingImpl setUrl(String url) {
        this.url = url;
        return this;
    }

    public QrCodeDrawingImpl setSize(Size size) {
        this.size = size;
        return  this;
    }
    public QrCodeDrawingImpl setPosition(Position position) {
        this.position = position;
        return this;
    }

    @Override
    public void draw(BufferedImage bufferedImage) throws Exception {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, size.width, size.height, hints);
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = bufferedImage.createGraphics();
        // 在图形和图像中实现混合和透明效果
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f));
        // 绘制
        g2d.drawImage(toBufferedImage(bitMatrix),position.x, position.y, size.width,size.height, null);
        g2d.dispose();// 释放图形上下文使用的系统资源
    }
}
