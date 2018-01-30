package org.xteam.plus.mars.wx.bean.outxmlbuilder;

import org.xteam.plus.mars.wx.bean.WxXmlOutNewsMessage;
import org.xteam.plus.mars.wx.bean.WxXmlOutNewsMessage.Item;

import java.util.ArrayList;
import java.util.List;


/**
 * 图文消息builder
 *
 * @author antgan
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder, WxXmlOutNewsMessage> {

    protected final List<Item> articles = new ArrayList<WxXmlOutNewsMessage.Item>();

    public NewsBuilder addArticle(Item item) {
        this.articles.add(item);
        return this;
    }

    public WxXmlOutNewsMessage build() {
        WxXmlOutNewsMessage m = new WxXmlOutNewsMessage();
        for (Item item : articles) {
            m.addArticle(item);
        }
        setCommon(m);
        return m;
    }

}
