package com.herego.api.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ValidErrorResponse extends GenericResponse {
    private List<String> reasons;

    public ValidErrorResponse() {
        this.reasons = new ArrayList<>();
    }
}
