package com.ming.utils;

import com.ming.base.CodeException;
import com.ming.base.ResponseBody;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;

import java.io.IOException;

/**
 * 数据缓冲 工具类
 *
 * @author ming
 * @date 2019-09-04 17:12:21
 */
public class DataBufferUtils {
    /**
     * 读取 DataBuffer
     *
     * @param dataBuffer 数据缓冲
     * @return byte[] 读取的数组
     * @author ming
     * @date 2019-09-04 17:06:50
     */
    public static byte[] readDataBuffer(DataBuffer dataBuffer) {
        try {
            return IOUtils.toByteArray(dataBuffer.asInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new CodeException(ResponseBody.CodeEnum.SERVER_ERROR, "dataBuffer转换数组异常");
        }
    }

    /**
     * 重新填充数据缓冲
     *
     * @param dataBuffer  数据缓冲
     * @param dataByteArr 数据二进制数组
     * @return DataBuffer
     * @author ming
     * @date 2019-09-04 17:11:36
     */
    public static DataBuffer refillDataBuffer(DataBuffer dataBuffer, byte[] dataByteArr) {
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
        org.springframework.core.io.buffer.DataBufferUtils.release(dataBuffer);
        return nettyDataBufferFactory.wrap(dataByteArr);
    }

}
