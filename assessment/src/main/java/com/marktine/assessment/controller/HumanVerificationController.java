package com.marktine.assessment.controller;

import com.marktine.assessment.models.ServiceResult;
import com.marktine.assessment.models.request.ClientRequest;
import com.marktine.assessment.service.HumanVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class HumanVerificationController {

    @Autowired
    private HumanVerificationService verificationService;

    @PostMapping("/question")
    public ResponseEntity<?> initiateVerification(@Valid @RequestBody ClientRequest request) {
        return ResponseEntity.status(200)
                .body(new ServiceResult<>(verificationService.initiate(request)));
    }


}
