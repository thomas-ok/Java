package org.thomas;

import java.io.File;
import java.io.IOException;

/**
 * author Thomas
 * version V1.0
 * 2020/9/29 12:50
 * Description:
 */
public class ProcessDemo {
    public static void main(String[] args) {
        String jarPathAndName = "D:\\jarsFolder\\iostream-1.0.0.jar";
        unCompress(jarPathAndName);
    }

    public static void unCompress(String jarPathAndName) {
        File jar = new File(jarPathAndName);
        String jarName = jar.getName().replace(".jar", "");
        File unCompressFolder = new File(jar.getParent(), jarName);
        if (!unCompressFolder.exists()) {
            unCompressFolder.mkdir();
        }
        String[] command = {"cmd.exe", "/C", "jar -xvf " + jarPathAndName};
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command, null, unCompressFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("解压缩完毕");
    }
}
