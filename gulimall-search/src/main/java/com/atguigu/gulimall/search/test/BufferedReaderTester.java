package com.atguigu.gulimall.search.test;

import java.io.BufferedReader;
import java.io.FileReader;

class BufferedReaderTester {
    //main方法中声明抛出I/O异常#6(1分)
    public static void main ( String[] args )     throws Exception {
        String fileName = "D:/Hello.txt";
        String line;
        //创建文件输入流并放入缓冲流当中#7(1分)
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        line = in.readLine();                  //读取一行内容
        while (line != null) {      //控制循环条件直到文件终点#8(1分)
            System.out.println(line); //#9(1分)
            line = in.readLine();
        }
        in.close();       //关闭缓冲流，文件输入流自动也被关闭
    }
}
