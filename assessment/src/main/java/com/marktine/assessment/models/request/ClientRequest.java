package com.marktine.assessment.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientRequest {

    @NotBlank(message="data cannot be null or empty")
    private String data;
}
