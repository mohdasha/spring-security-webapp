package com.mohdasha.security.event;

import com.mohdasha.security.model.UserRegistrationDto;
import org.springframework.context.ApplicationEvent;


public class UserRegistrationEvent extends ApplicationEvent {

    private UserRegistrationDto userRegistrationDto;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param userRegistrationDto the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UserRegistrationEvent(UserRegistrationDto userRegistrationDto) {
        super(userRegistrationDto);
        this.userRegistrationDto = userRegistrationDto;
    }

    public UserRegistrationDto getUserRegistrationDto() {
        return userRegistrationDto;
    }
}
