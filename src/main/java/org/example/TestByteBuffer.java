package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author rlz
 * @date 2024/3/25 00:54
 * @description 
 */
public class TestByteBuffer {
    public static void main(String[] args) {
        try (FileChannel channel = new FileInputStream("src/main/resources/ByteBufferTest.txt").getChannel()) {
            // 获得缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            StringBuilder builder = new StringBuilder();
            while (channel.read(buffer) > 0) {
                // 切换模式 limit=position, position=0
                buffer.flip();
                // 当buffer中还有数据时，获取其中的数据
                while (buffer.hasRemaining()) {
                    builder.append((char) buffer.get());
                }
                // 切换模式 position=0, limit=capacity
                buffer.clear();
            }
            System.out.println(builder.toString());
        } catch (IOException ignored) {
        }
    }
}
