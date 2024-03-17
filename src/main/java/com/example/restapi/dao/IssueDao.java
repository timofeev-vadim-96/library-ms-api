package com.example.restapi.dao;

import com.example.restapi.models.Issue;
import org.springframework.stereotype.Repository;

@Repository
public class IssueDao extends DaoAbstract<Issue>{

    @Override
    public Issue add(Issue issue){
        super.entities.add(issue);
        return issue;
    }

    @Override
    public Issue getById(long id){
        return super.entities.stream()
                .filter(issue -> issue.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean removeById(long id) {
        return super.entities.removeIf(issue -> issue.getId() == id);
    }
}
