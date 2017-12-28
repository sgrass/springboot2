package org.cx.stream.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author grass
 * @date 2017/12/28
 */
public interface MessageSource {
    String OUTPUT = "cx";

    @Output(OUTPUT)
    MessageChannel cx();
}
