package com.akcam.secureChat.domain.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class FetchMessage extends AbstractMessage {
    private Date from;
}
