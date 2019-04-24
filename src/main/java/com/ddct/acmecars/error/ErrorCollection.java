package com.ddct.acmecars.error;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorCollection {

    private List<ErrorResponse> errors = new ArrayList<>();
}
