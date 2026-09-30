package com.pig4cloud.pig.common.mq.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginFailureMessage extends BaseMessage<LoginFailureMessage.Payload> {

    public LoginFailureMessage(String username, String clientIp) {
        super("LOGIN_FAILURE", new Payload(username, clientIp));
    }

    @Data
    @NoArgsConstructor
    public static class Payload {
        private String username;
        private String clientIp;

        public Payload(String username, String clientIp) {
            this.username = username;
            this.clientIp = clientIp;
        }
    }
}