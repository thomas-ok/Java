package com.tmszh;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * author Thomas
 * version V1.0
 * 2020/9/27 16:47
 * Description:
 */
public class MainApp {
    public static void main(String[] args) {
        File file = new File("D:\\thomas-ok\\document\\readme.txt");
        //创建一个文件夹，如果由于某些原因导致不能创建，则抛出异常
        //一次可以创建单级或者多级目录
        try {
            FileUtils.forceMkdir(new File("D:\\thomas-ok\\Downloads"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("D:\\thomas-ok\\Downloads 目录创建成功!");
        //为指定文件创建文件的父级目录
        try {
            FileUtils.forceMkdirParent(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("D:\\thomas\\document\\readme.txt 父级目录创建成功");
    }
}
