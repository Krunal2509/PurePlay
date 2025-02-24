package com.example.pureplay;

public class songArray {
    int img;
    String sName, sPath;

    songArray(int img, String sName, String sPath) {
        this.img = img;
        this.sName = sName;
        this.sPath = sPath;
    }

    public String getsPath() {
        return sPath;
    }

    public String getsName() {
        return sName;
    }
    public int getsImg() {
        return img;
    }

}