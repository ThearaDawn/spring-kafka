package com.learn.bind;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;

public interface SynSink {
    @Input("input")
    PollableMessageSource source();
}
