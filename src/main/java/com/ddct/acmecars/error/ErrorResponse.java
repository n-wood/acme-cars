package com.ddct.acmecars.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
class ErrorResponse
{
    private String message;
    private String details;

}
