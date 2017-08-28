package com.carry.commit;


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

//    public Map<String, Object> getErrorcode() {
//        return errorcode;
//    }
//
//    public void setErrorcode(Map<String, Object> errorcode) {
//        this.errorcode = errorcode;
//    }

    T data;
    // Map<String , Object> errorcode;

    String errormessage;

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Integer total_count;


}
