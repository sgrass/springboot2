package org.cx.stream.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义消息源
 * @author grass
 * @date 2017/12/27
 */
public interface MessageSource {

    /**
     * 消息来源的管道名称："cx"
     */
    String OUTPUT = "cx";

    @Output(OUTPUT)
    MessageChannel cx();
}
