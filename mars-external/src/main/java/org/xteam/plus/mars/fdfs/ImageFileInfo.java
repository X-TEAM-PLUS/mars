package org.xteam.plus.mars.fdfs;

public class ImageFileInfo{
    /**
     * 文件名
     */
    private String  fileName;

    /**
     * 宽度
     */
    private Integer  width;

    /**
     * 高度
     */
    private Integer  height;

    /**
     * 大小
     */
    private Long  size;

    /**
     *  存放地址
     */
    private String storeAddress;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    @Override
    public String toString() {
         StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder
                .append("fileName::").append(fileName)
                .append("::width::").append(width)
                .append("::height::").append(height)
                .append("::size::").append(size)
                .append("::storeAddress::").append(storeAddress)
        ;
        return stringBuilder.toString();
    }
}


