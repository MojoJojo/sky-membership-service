package com.sky.membership.reporting.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    String ENROL_INPUT = "enrolInput";
    String CANCEL_INPUT = "cancelInput";

    @Input(ENROL_INPUT)
    SubscribableChannel enrolInput();
    @Input(CANCEL_INPUT)
    SubscribableChannel cancelInput();

}
