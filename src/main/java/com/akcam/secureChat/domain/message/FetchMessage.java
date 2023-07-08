package com.akcam.secureChat.domain.message;

import lombok.Builder;

@Builder
public class FetchMessage extends AbstractMessage {
    private final String content = "fetch";
}
