package com.marktine.assessment.service.impl;

import com.marktine.assessment.exceptions.InvalidQueryException;
import com.marktine.assessment.models.request.ClientRequest;
import com.marktine.assessment.models.response.ServerQuestionResponse;
import com.marktine.assessment.service.HumanVerificationService;
import com.marktine.assessment.utils.AESUtils;
import com.marktine.assessment.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    @Value("${secret}")
    private String secretKey;



    public ServerQuestionResponse initiate(ClientRequest request) {

        if(!request.getData().equalsIgnoreCase(intiateQuery)) {
            throw new InvalidQueryException();
        }
        int n = RandomUtils.generate(1,10);
        int n2 = RandomUtils.generate(1,99);


        StringBuilder builder = new StringBuilder();



        String question = initiateQueryResponse.replace("{0}",builder.append(n)
                .append(",")
                .append(n2).toString());

        String identifier = AESUtils.encrypt(question,secretKey);

        return ServerQuestionResponse.builder()
                .data(question)
                .identifier(identifier)
                .build();

    }


    public ServerQuestionResponse verify(ClientRequest request) {
        String decodedText = AESUtils.decrypt(request.getIdentifier(),secretKey);
        String[] answers = request.getData().split("answer");
        String[] numbers = answers[0].split(",");
        String[] originalQsnSplit = decodedText.split("answer");
        String[] originalNumbersArr = originalQsnSplit[0].split(",");
        String originalNumbers = Arrays.stream(originalNumbersArr).map(num -> num.replaceAll("[^0-9]", ""))
              .filter(StringUtils::hasLength).collect(Collectors.joining());
        String numbersInRequest = Arrays.stream(numbers).map(num -> num.replaceAll("[^0-9]", ""))
                .filter(StringUtils::hasLength).collect(Collectors.joining());
        if(!originalNumbers.equalsIgnoreCase(numbersInRequest))
            return ServerQuestionResponse.builder().data(incorrectMsg).build();

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
