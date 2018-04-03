package org.xteam.plus.mars.common.qrcode.drawing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ImageDrawingImpl extends Drawing {
    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 显示的大小
     */
    private Size size;

    /**
     * 显示的位置
     */
    private Position position;
    public ImageDrawingImpl setSize(Size size) {
        this.size = size;
        return  this;
    }


    public ImageDrawingImpl setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public ImageDrawingImpl setPosition(Position position) {
        this.position = position;
        return this;
    }

    @Override
    public void draw(BufferedImage bufferedImage) throws Exception {
        BufferedImage image = ImageIO.read(new URL(imageUrl));
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = bufferedImage.createGraphics();
        // 在图形和图像中实现混合和透明效果
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f));
        // 绘制
        g2d.drawImage(image,position.x, position.y, size.width,size.height, null);
        g2d.dispose();// 释放图形上下文使用的系统资源
    }
}
