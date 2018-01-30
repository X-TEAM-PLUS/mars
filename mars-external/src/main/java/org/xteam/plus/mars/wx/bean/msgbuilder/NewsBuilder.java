package org.xteam.plus.mars.wx.bean.msgbuilder;



import org.xteam.plus.mars.wx.api.WxConsts;
import org.xteam.plus.mars.wx.bean.WxMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息builder
 * 
 * <pre>
 * 用法:
 * WxCustomMessage m = WxCustomMessage.NEWS().addArticle(article).toUser(...).build();
 * </pre>
 * 
 * @author antgan
 *
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder> {

	private List<WxMessage.WxArticle> articles = new ArrayList<WxMessage.WxArticle>();

	public NewsBuilder() {
		this.msgType = WxConsts.CUSTOM_MSG_NEWS;
	}

	public NewsBuilder addArticle(WxMessage.WxArticle article) {
		this.articles.add(article);
		return this;
	}

	public WxMessage build() {
		WxMessage m = super.build();
		m.setArticles(this.articles);
		return m;
	}
}
