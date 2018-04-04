package org.xteam.plus.mars.common.qrcode;


import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.common.qrcode.drawing.*;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class QRCodeBuilder extends Logging {
    private BufferedImage bufferedImage = null;
    private List<Drawing> drawingList = new ArrayList<Drawing>();

    /**
     * 设置背景
     *
     * @param file 背景图片地址
     * @return
     * @throws Exception
     */
    public QRCodeBuilder setBackGround(String file) throws Exception {
        // 获取底图
        logInfo("setBackGround['file']:" + file);
        this.bufferedImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        return this;
    }


    public QRCodeBuilder add(Drawing drawing) {
        drawingList.add(drawing);
        return this;
    }

    public void draw(File file) throws Exception {
        for (Drawing drawing : drawingList) {
            drawing.draw(bufferedImage);
        }
        ImageIO.write(bufferedImage, "png", file);
    }

    public void draw( OutputStream output) throws Exception {
        for (Drawing drawing : drawingList) {
            drawing.draw(bufferedImage);
        }
        ImageIO.write(bufferedImage, "png", output);
    }

}
