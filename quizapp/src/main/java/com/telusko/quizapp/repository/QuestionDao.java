package com.telusko.quizapp.repository;

import com.telusko.quizapp.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends MongoRepository<Question, String> {
    List<Question> findByCategory(String category);
}
