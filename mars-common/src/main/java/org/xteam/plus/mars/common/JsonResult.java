package org.xteam.plus.mars.common;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: 通用json返回对象
 */
public class JsonResult extends HashMap<String, Object> {
    private static final String SUCCESS = "success";
    private static final String DATA = "data";
    private static final String MESSAGE = "message";

    public void setSuccess(boolean success) {
        put(SUCCESS, success);
    }

    public void setMessage(String message) {
        put(MESSAGE, message);
    }

    public void setData(Object data) {
        put(DATA, data);
    }

    public boolean isSuccess() {
        return (Boolean) get(SUCCESS);
    }

    public String getMessage() {
        return (String) get(MESSAGE);
    }

    public Object getData() {
        return get(DATA);
    }


}
