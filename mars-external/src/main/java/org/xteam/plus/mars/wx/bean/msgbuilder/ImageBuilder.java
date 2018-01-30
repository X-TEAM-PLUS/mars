package org.xteam.plus.mars.wx.bean.msgbuilder;


import org.xteam.plus.mars.wx.api.WxConsts;
import org.xteam.plus.mars.wx.bean.WxMessage;

/**
 * 图片消息builder
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.IMAGE().mediaId(...).toUser(...).build();
 * </pre>
 * 
 * @author antgan
 *
 */
public final class ImageBuilder extends BaseBuilder<ImageBuilder> {
  private String mediaId;

  public ImageBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_IMAGE;
  }

  public ImageBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  public WxMessage build() {
    WxMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}
