package com.nantian.test.java.util.util;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/2/20
 */
public class FileToFileUtil {
    public static void nioCpoy(String source, String target, int allocate) throws IOException{
        ByteBuffer byteBuffer = ByteBuffer.allocate(allocate);
        FileInputStream inputStream = new FileInputStream(source);
        FileChannel inChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream(target,true);
        FileChannel outChannel = outputStream.getChannel();
        int length = inChannel.read(byteBuffer);
        StringBuffer s=new StringBuffer();
        while(length != -1){
            byteBuffer.flip();//读取模式转换写入模式
            outChannel.write(byteBuffer);
            byte b = byteBuffer.get();
            byteBuffer.clear(); //清空缓存，等待下次写入
            // 再次读取文本内容
            length = inChannel.read(byteBuffer);
        }
        outputStream.close();
        outChannel.close();
        inputStream.close();
        inChannel.close();
    }
}
