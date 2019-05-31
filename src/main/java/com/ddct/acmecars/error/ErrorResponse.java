package com.ddct.acmecars.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
class ErrorResponse
{
    private String message;
    private String details;
}
