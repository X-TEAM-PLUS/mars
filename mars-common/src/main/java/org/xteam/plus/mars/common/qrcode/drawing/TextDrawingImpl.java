package org.xteam.plus.mars.common.qrcode.drawing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextDrawingImpl extends Drawing {
    /**
     * 字体
     */
    private Font font;

    /**
     * 文本
     */
    private String text;

    /**
     * 颜色
     */
    private Color color;

    /**
     * 显示的位置
     */
    private Position position;

    public TextDrawingImpl setText(String text) {
        this.text = text;
        return this;
    }

    public TextDrawingImpl setFont(Font font) {
        this.font = font;
        return this;
    }
    public TextDrawingImpl setPosition(Position position) {
        this.position = position;
        return this;
    }

    public TextDrawingImpl setColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public void draw(BufferedImage bufferedImage) throws Exception {
        Graphics g = bufferedImage.createGraphics();
        //g.drawImage(image, 0, 0, imageW, imageH, null);
        g.setColor(color);
        g.setFont(new Font("粗体", Font.BOLD, 30));
        g.drawString(text, position.x, position.y);
        g.dispose();
    }
}
