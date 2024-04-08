package ru.gb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.model.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    AuthorEntity findFirstByFirstNameAndLastName(String firstName, String lastName);
}
