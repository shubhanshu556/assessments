package com.marktine.assessment.service;

import com.marktine.assessment.models.request.ClientRequest;
import com.marktine.assessment.models.response.ServerQuestionResponse;

public interface HumanVerificationService {

    ServerQuestionResponse initiate(ClientRequest request);

    ServerQuestionResponse verify(ClientRequest request);
}
