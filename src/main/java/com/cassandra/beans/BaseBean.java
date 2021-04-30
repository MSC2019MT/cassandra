package com.cassandra.beans;

import java.io.Serializable;
import java.util.List;

public class BaseBean implements Serializable {

    private String status;
    private List<String> errorList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
