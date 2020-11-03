package org.thomas;

import java.io.File;

/**
 * author Thomas
 * version V1.0
 * 2020/9/28 18:31
 * Description:
 */
public class MainApp {
    public static void main(String[] args) {
        /*String source = "E:\\Documents\\全套SpringBoot入门到项目实战课程.pdf";
        String dest = "D:\\springboot.pdf";*/

        /*File destFile = new File(dest);
        boolean exists = destFile.exists();
        if (exists) {
            System.out.println(dest+" 存在");
        } else {

            System.out.println(dest+" 不存在");
        }*/
        //boolean success = FileUtils.copyFileToFileUsingIOStream(source, dest);
        //boolean success = FileUtils.copyFileToFileUsingFileChannel(source, dest);
        /*if (success) {
            System.out.println("E:\\Documents\\全套SpringBoot入门到项目实战课程.pdf" + " 复制到 " + "D:\\springboot.pdf" + " 成功");
        } else {
            System.out.println("E:\\Documents\\全套SpringBoot入门到项目实战课程.pdf" + " 复制到 " + "D:\\springboot.pdf" + " 失败");
        }*/
        //System.out.println("复制文件:" + success);
        //String path = "D:\\file";
        /*File file = new File(path);
        boolean result = FileUtils.delete(file);*/
        //String path = "D:\\springboot.pdf";
        //boolean result = FileUtils.delete(path);
        //if (result) {
        //    System.out.println("D:\\file 删除成功");
        //} else {
        //    System.out.println("D:\\file 删除失败");
        //}
        //String source ="D:\\git安装后设置.txt";
        String source="D:\\file";
        String dest="D:\\file2";
        File sourceFile = new File(source);
        File destFile = new File(dest);
        FileUtils.copy(sourceFile,destFile);
    }
}
