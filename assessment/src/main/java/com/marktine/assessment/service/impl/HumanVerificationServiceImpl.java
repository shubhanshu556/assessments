package com.marktine.assessment.service.impl;

import com.marktine.assessment.exceptions.InvalidQueryException;
import com.marktine.assessment.models.request.ClientRequest;
import com.marktine.assessment.models.response.ServerQuestionResponse;
import com.marktine.assessment.service.HumanVerificationService;
import com.marktine.assessment.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Service
public class HumanVerificationServiceImpl implements HumanVerificationService {

    @Value("${client.question}")
    private String intiateQuery;

    @Value("${sum.question}")
    private String initiateQueryResponse;

    @Value("${sum.answer.correct}")
    private String correctMsg;

    @Value("${sum.answer.wrong}")
    private String incorrectMsg;


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

        String[] answers = request.getData().split("answer");
        String[] numbers = answers[0].split(",");
        int sum = Arrays.stream(numbers)
                .map(num -> num.replaceAll("[^0-9]", ""))
                .filter(StringUtils::hasLength)
                .mapToInt(Integer::parseInt)
                .sum();
        String enteredAnswer = answers[1].replaceAll("[^0-9]", "");
        if(sum == Integer.parseInt(enteredAnswer))
           return ServerQuestionResponse.builder().data(correctMsg).build();
        return ServerQuestionResponse.builder().data(incorrectMsg).build();

    }
}
