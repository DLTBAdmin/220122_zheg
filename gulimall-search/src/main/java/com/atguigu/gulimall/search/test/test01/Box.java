package com.atguigu.gulimall.search.test.test01;

public class Box {
    private int width;
    private int height;
    private int depth;
    private int volumn;

    public int getVolumn() {
        return volumn;
    }

    public Box(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.volumn = width*height*depth;

    }
}

class BoxTest{
    public static void main(String[] args) {
        Box box = new Box(1, 2, 3);
        int volumn = box.getVolumn();
        System.out.println(volumn);
    }
}
