package org.xteam.plus.mars.type;

public enum WithDrawStatusName {
    Reject(-1,"已驳回")
    ,Unaudited(0,"未审核")
    ,Audited(1,"已审核")
    ,Pay(2,"已打款")
    ,Confirmed(3,"已确认");
    private int code;
    private String info;

    WithDrawStatusName(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static WithDrawStatusName valueOf(Integer code) {
            switch (code){
                case -1:
                    return Reject;
                case 0:
                    return  Unaudited;
                case 1:
                    return Audited;
                case 2:
                    return Pay;
                case 3:
                    return Confirmed;
                    default:
                        return null;
            }

    }

}
