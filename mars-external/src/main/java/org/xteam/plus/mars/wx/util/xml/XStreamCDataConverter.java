package org.xteam.plus.mars.wx.util.xml;

import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * CDATA 转换器
 * @author antgan
 *
 */
public class XStreamCDataConverter extends StringConverter {

	@Override
	public String toString(Object obj) {
		return "<![CDATA[" + super.toString(obj) + "]]>";
	}

}
