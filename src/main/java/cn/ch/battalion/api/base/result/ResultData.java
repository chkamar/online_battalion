package cn.ch.battalion.api.base.result;

public class ResultData<T> {

    public ResultData() {
        super();
    }

    public ResultData(boolean status) {
        this.status = status;
    }

    public ResultData(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResultData(boolean status, T data, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultData(boolean status, String msg, Integer statusCode) {
        this.status = status;
        this.msg = msg;
        this.statusCode = statusCode;
    }

    public ResultData(boolean status, String msg, Integer statusCode, String toUrl) {
        this.status = status;
        this.msg = msg;
        this.statusCode = statusCode;
        this.toUrl = toUrl;
    }

    public ResultData(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResultData(boolean status, String toUrl, T data) {
        this.status = status;
        this.toUrl = toUrl;
        this.data = data;
    }

    public ResultData(boolean status, String msg, T data, Integer statusCode, String toUrl) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.statusCode = statusCode;
        this.toUrl = toUrl;
    }

    private boolean status;
    private String msg;
    private T data;
    private String toUrl;
    private int statusCode;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }
}
