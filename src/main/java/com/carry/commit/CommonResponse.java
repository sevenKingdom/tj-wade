package com.carry.commit;

import java.util.Map;

/**
 * Created by songxianying on 17/7/24.
 */
public class CommonResponse<T> {
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(Map<String, Object> errorcode) {
        this.errorcode = errorcode;
    }

    T data;
    Map<String , Object> errorcode;
}
