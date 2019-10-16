package com.example.person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface IdentityCardRepository extends JpaRepository<IdentityCard,Long> {
    List<IdentityCard> findByIssueDateAfter(LocalDate issueDate);
}
