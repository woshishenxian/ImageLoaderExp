package com.vince.imageloaderexp.data;

import java.util.List;

/**
 * Created by vince on 16/8/11.
 */

public class TngouVo {
    private boolean status;
    private List<Tngou> tngou;
    private int total;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Tngou> getTngou() {
        return tngou;
    }

    public void setTngou(List<Tngou> tngou) {
        this.tngou = tngou;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
