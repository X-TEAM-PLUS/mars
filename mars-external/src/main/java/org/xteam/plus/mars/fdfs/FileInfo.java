package org.xteam.plus.mars.fdfs;

public class FileInfo {
    /**
     * 文件名
     */
    private String  fileName;

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
                .append("::size::").append(size)
                .append("::storeAddress::").append(storeAddress)
        ;
        return stringBuilder.toString();
    }
}


