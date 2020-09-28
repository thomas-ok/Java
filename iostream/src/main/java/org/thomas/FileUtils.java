package org.thomas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * author Thomas
 * version V1.0
 * 2020/9/27 15:41
 * Description:
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);


    /**
     * 新建目录
     *
     * @param folderPath
     */
    public static void newFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        logger.info("新建目录{}成功", folderPath);
    }

    /**
     * 新建文件
     *
     * @param filePathAndName
     * @param fileContent
     */
    public static void newFile(String filePathAndName, String fileContent) {

        try {
            File newFile = new File(filePathAndName);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(newFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(fileContent);
            fileWriter.close();
            logger.info("新建文件{}成功", filePathAndName);
        } catch (Exception e) {
            logger.error("新建文件{}发生异常，exception is {}", filePathAndName, e.getMessage());

        }

    }

    //用IO流实现文件复制
    public static boolean copyFileToFileUsingIOStream(String source, String dest) {
        boolean success = false;
        File sourceFile = new File(source);
        File destFile = new File(dest);
        if (!destFile.exists()) {
            try {
                destFile.createNewFile();
            } catch (IOException e) {
                logger.error("创建{}文件失败", dest);
                e.printStackTrace();
            }
            logger.error("创建{}文件成功", dest);
        }
        if (!destFile.isFile()) {
            logger.warn("复制失败，{}不是文件", dest);
            return success;
        }
        if (!sourceFile.exists()) {
            logger.warn("复制失败，{}不存在", source);
            return success;
        }
        if (!sourceFile.isFile()) {
            logger.warn("复制失败，{}不是文件", source);
            return success;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024 * 8];
            int read = 0;
            while ((read = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }
            success = true;
            logger.info("从{}复制文件到{}成功", source, dest);

        } catch (Exception e) {
            logger.error("从{}复制文件到{}发生异常，exception is {}", source, dest, e.getMessage());

        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("关闭fis失败,exception is {}", e.getMessage());
                }
            }
            if (fos != null) {
                try {

                    fos.close();
                } catch (IOException e) {
                    logger.error("关闭fos失败,exception is {}", e.getMessage());
                }
            }
        }
        return success;
    }

    /**
     * 使用FileChannel实现文件复制
     *
     * @param source
     * @param dest
     * @return
     */
    public static boolean copyFileToFileUsingFileChannel(String source, String dest) {
        boolean success = false;
        File sourceFile = new File(source);
        File destFile = new File(dest);
        if (!destFile.exists()) {
            try {
                destFile.createNewFile();
            } catch (IOException e) {
                logger.error("创建{}文件失败", dest);
                e.printStackTrace();
            }
            logger.error("创建{}文件成功", dest);
        }
        if (!destFile.isFile()) {
            logger.warn("复制失败，{}不是文件", dest);
            return success;
        }
        if (!sourceFile.exists()) {
            logger.warn("复制失败，{}不存在", source);
            return success;
        }
        if (!sourceFile.isFile()) {
            logger.warn("复制失败，{}不是文件", source);
            return success;
        }


        final int BSIZE = 1024 * 8;
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;

        try {
            inputChannel = new FileInputStream(sourceFile).getChannel();
            outputChannel = new FileOutputStream(destFile).getChannel();

            //outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            //上面一行代码或者下面7行代码
            //从inChannel文件中读出BSIZE bytes ，并写入outChannel文件。
            ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();//prepare for reading;清空缓冲区；
            }
            success = true;
            logger.info("从{}复制文件到{}成功", source, dest);
        } catch (Exception e) {
            logger.error("从{}复制文件到{}发生异常，exception is {}", source, dest, e.getMessage());
        } finally {

            if (inputChannel != null) {
                try {
                    inputChannel.close();
                } catch (IOException e) {
                    logger.error("关闭inputChannel失败,exception is {}", e.getMessage());
                }
            }
            if (outputChannel != null) {
                try {
                    outputChannel.close();
                } catch (IOException e) {
                    logger.error("关闭outputChannel失败,exception is {}", e.getMessage());
                }
            }
        }
        return success;
    }

    /**
     * 复制文件或文件夹到另一文件或文件夹
     *
     * @param sourceFile
     * @param destFile
     */
    public static void copy(File sourceFile, File destFile) {
        if (!sourceFile.exists()) {
            logger.error("{}不存在", sourceFile.getAbsolutePath());
            return;
        }
        if (!destFile.exists()) {
            logger.error("{}不存在", destFile.getAbsolutePath());
            return;
        }
        byte[] buffer = new byte[1024 * 8];
        int read;
        FileInputStream fis;
        FileOutputStream fos;
        if (sourceFile.isDirectory()) {
            String fileName = sourceFile.getName();
            String toFilepath = destFile.getAbsolutePath();
            File copy = new File(toFilepath, fileName);
            //复制文件夹
            if (!copy.exists()) {
                copy.mkdirs();
            }
            //遍历文件夹
            for (File f : sourceFile.listFiles()) {
                copy(f, copy);
            }
        } else {
            if (destFile.isDirectory()) {
                String fileName = sourceFile.getName();
                String toFilepath = destFile.getAbsolutePath();
                //写文件
                File newFile = new File(toFilepath, fileName);
                try {
                    fis = new FileInputStream(sourceFile);
                    fos = new FileOutputStream(newFile);
                    while ((read = fis.read(buffer)) != -1) {
                        fos.write(buffer, 0, read);
                    }
                } catch (Exception e) {
                    logger.error("{} 复制文件到 {}失败，exception is {}", sourceFile.getAbsolutePath(), toFilepath, e.getMessage());
                }
            } else {
                //写文件
                try {
                    fis = new FileInputStream(sourceFile);
                    fos = new FileOutputStream(destFile);
                    while ((read = fis.read(buffer)) != -1) {
                        fos.write(buffer, 0, read);
                    }
                } catch (Exception e) {
                    logger.error("{} 复制文件到 {}失败，exception is {}", sourceFile.getAbsolutePath(), destFile.getAbsolutePath(), e.getMessage());
                }
            }

        }
        logger.info("{} 复制到 {} 成功", sourceFile.getAbsolutePath(), destFile.getAbsolutePath());
    }

    /**
     * 删除文件夹
     *
     * @param path
     * @return
     */
    public static boolean delete(String path) {
        File file = new File(path);
        if (!file.exists()) {
            logger.warn(path + "文件或文件夹不存在");
            return false;
        }

        if (file.isFile()) {
            file.delete();
            logger.info(file.getAbsolutePath() + "-已删除");

        } else {
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                delete(file + File.separator + files[i]);
            }
            file.delete();
            logger.info(file.getAbsolutePath() + "-已删除");
        }
        return true;

    }

    /**
     * 删除文件夹
     *
     * @param file
     * @return
     */
    public static boolean delete(File file) {
        if (!file.exists()) {
            logger.warn(file.getAbsolutePath() + "文件或文件夹不存在");
            return false;
        }
        if (file.isFile()) {
            file.delete();
            //logger.info(file.getAbsolutePath() + "-已删除");
            //return true;
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                delete(files[i]);
            }
            file.delete();
            //logger.info(file.getAbsolutePath() + "-已删除");
        }
        return true;

    }


}
