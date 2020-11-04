package org.thomas.util;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * author Andrew
 * version V1.0
 * 2020/11/4 22:33
 * Description:
 */
public class TraverseFolderUtils {
    public void traverseFolder1(File folder) {
        LinkedList<File> list = new LinkedList<File>();
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("文件夹:" + file.getAbsolutePath());
                list.add(file);
            } else {
                System.out.println("文件:" + file.getAbsolutePath());
            }
        }
        File temp_file;
        while (!list.isEmpty()) {
            temp_file = list.removeFirst();
            files = temp_file.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("文件夹:" + file.getAbsolutePath());
                    list.add(file);
                } else {
                    System.out.println("文件:" + file.getAbsolutePath());
                }
            }
        }
    }

    public void traverseFolder2(File folder) {
        File[] files = folder.listFiles();
        if (null == files || files.length == 0) {
            System.out.println("文件夹是空的!");
            return;
        } else {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("文件夹:" + file.getAbsolutePath());
                    traverseFolder2(file);
                } else {
                    System.out.println("文件:" + file.getAbsolutePath());
                }
            }
        }
    }

    public static List<File> getFileList(File folder) {
        List<File> filelist = new ArrayList<>();
        File[] files = folder.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    System.out.println(files[i].getAbsolutePath());
                    filelist.addAll(getFileList(files[i])); // 获取文件绝对路径
                } else { // 判断文件名是否以.avi结尾
                    System.out.println(files[i].getAbsolutePath());
                    filelist.add(files[i]);
                }
            }

        }
        return filelist;
    }

}

