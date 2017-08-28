package com.carry.commit;

/**
 * Created by songxianying on 17/8/27.
 */
public class JsonRequest<T> {
    T data;
    String token;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
