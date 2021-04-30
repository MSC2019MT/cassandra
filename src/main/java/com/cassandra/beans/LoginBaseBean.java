package com.cassandra.beans;

import javax.validation.constraints.NotNull;

public class LoginBaseBean extends BaseBean{

    @NotNull(message = "please enter username")
    private String username;

    @NotNull(message = "please enter password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
