package org.xteam.plus.mars.wx.bean.msgbuilder;


import org.xteam.plus.mars.wx.api.WxConsts;
import org.xteam.plus.mars.wx.bean.WxMessage;

/**
 * 语音消息builder
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.VOICE().mediaId(...).toUser(...).build();
 * </pre>
 * @author antgan
 *
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder> {
  private String mediaId;

  public VoiceBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_VOICE;
  }

  public VoiceBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  public WxMessage build() {
    WxMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}
