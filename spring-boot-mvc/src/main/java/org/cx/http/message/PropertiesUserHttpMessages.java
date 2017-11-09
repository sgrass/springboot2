package org.cx.http.message;

import org.cx.model.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author grass
 * @date 2017/10/17
 */
public class PropertiesUserHttpMessages extends AbstractHttpMessageConverter<User> {
    public PropertiesUserHttpMessages() {
        super(MediaType.valueOf("application/properties+user"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    protected boolean supports(Class clazz) {
        //进来的类必须是User的子类
        return clazz.isAssignableFrom(User.class);
    }

    /**
     * 将请求properties数据转换为对象
     * @param clazz
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected User readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        /**
         * user.id=2
         * user.name=bbb
         */
        InputStream inputStream = inputMessage.getBody();
        Properties properties = new Properties();
        // 将请求中的内容转化成Properties
        properties.load(new InputStreamReader(inputStream,getDefaultCharset()));
        // 将properties 内容转化到 User 对象字段中
        User user = new User();
        user.setId(Long.valueOf(properties.getProperty("user.id")));
        user.setName(properties.getProperty("user.name"));

        return user;
    }

    /**
     * 请求的json转换为properties
     * @param user
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(User user, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        OutputStream outputStream = outputMessage.getBody();

        Properties properties = new Properties();
        properties.setProperty("person.id",String.valueOf(user.getId()));
        properties.setProperty("person.name",user.getName());

        properties.store(new OutputStreamWriter(outputStream,getDefaultCharset()),"write by web server");
    }
}
