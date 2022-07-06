package com.marktine.assessment.service.impl;

import com.marktine.assessment.exceptions.InvalidQueryException;
import com.marktine.assessment.models.request.ClientRequest;
import com.marktine.assessment.models.response.ServerQuestionResponse;
import com.marktine.assessment.service.HumanVerificationService;
import com.marktine.assessment.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HumanVerificationServiceImpl implements HumanVerificationService {

    @Value("${client.question}")
    private String intiateQuery;

    @Value("${sum.question}")
    private String initiateQueryResponse;



    public ServerQuestionResponse initiate(ClientRequest request) {

        if(!request.getData().equalsIgnoreCase(intiateQuery)) {
            throw new InvalidQueryException();
        }
        int n = RandomUtils.generate(1,10);
        int n2 = RandomUtils.generate(1,99);


        StringBuilder builder = new StringBuilder("");
        return ServerQuestionResponse.builder()
                .data(initiateQueryResponse.replace("{0}",builder.append(n)
                        .append(",")
                        .append(n2).toString()))
                .build();

    }


    public ServerQuestionResponse verify(ClientRequest request) {

       //TODO pending bcz of strict timeline
        return null;
    }
}
