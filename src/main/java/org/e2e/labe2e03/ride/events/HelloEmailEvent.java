package org.e2e.labe2e03.ride.events;

import org.springframework.context.ApplicationEvent;

public class HelloEmailEvent extends ApplicationEvent {
    private final String email;

    public HelloEmailEvent(String email) {
        super(email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
