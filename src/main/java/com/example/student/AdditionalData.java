package com.example.student;
import java.time.LocalDate;

import javax.persistence.Embeddable;

@Embeddable
public class AdditionalData {

    private LocalDate creationDate;
    private final String responsiblePerson = "Joanna Kowalczyk";

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}

