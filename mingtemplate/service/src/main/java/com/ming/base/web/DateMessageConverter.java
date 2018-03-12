package com.ming.base.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.Date;

/**
 * 返回数数据格式转换器
 *
 * @author com.ming
 * @date 2017-11-10 13:57
 */
public class DateMessageConverter extends AbstractHttpMessageConverter<Object> {

    /**
     * date 转换为 时间戳
     *
     * @author com.ming
     * @date 2017-11-10 13:56
     */
    ValueFilter filter = (obj, s, v) -> {
        if (v instanceof Date) {
            return ((Date) v).getTime();
        }
        return v;
    };

    public DateMessageConverter() {
        super(MediaType.ALL);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return this.supports(clazz) && this.canWrite(mediaType);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage httpOutputMessage) throws IOException {
        FileCopyUtils.copy(JSON.toJSONString(o, filter, SerializerFeature.DisableCircularReferenceDetect).getBytes(), httpOutputMessage.getBody());
    }


}
