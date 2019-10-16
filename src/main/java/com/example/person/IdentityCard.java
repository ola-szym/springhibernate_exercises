package com.example.person;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="identity_cards")
public class IdentityCard {

    @Id
    @GeneratedValue
    private Long id;
    private String series;
    private String number;
    private LocalDate issueDate;
    private LocalDate validityDate;

    @OneToOne(mappedBy = "identityCard")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return validityDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.validityDate = expirationDate;
    }
}
