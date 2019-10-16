package com.example;

import com.example.person.IdentityCard;
import com.example.person.IdentityCardRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

    @Autowired
    IdentityCardRepository identityCardRepository;

    @Test
    @Transactional
    public void shouldFindAllIdentityCardsIssued_afterDate() {
        //given
        IdentityCard identityCard = new IdentityCard();
        identityCard.setSeries("AXY");
        identityCard.setNumber("090954");
        identityCard.setIssueDate(LocalDate.of(1998,02,06));
        identityCard.setExpirationDate(LocalDate.of(2018,02,06));
        identityCardRepository.save(identityCard);

        // dodac drugi dowod i sprawdzic

        //when
        List<IdentityCard> foundAllIdentityCardsIssuedAfterDate = identityCardRepository.findByIssueDateAfter(LocalDate.of(2001,1,1));

        //then
        Assert.assertNotNull(foundAllIdentityCardsIssuedAfterDate);
    }
}