package org.xteam.plus.mars.common.excel;

public interface CellFormater {

    /**
     * 格式化
     * @param value
     * @return
     * @throws Exception
     */
    public String format(String value) throws Exception;
}
