package com.telusko.quizapp.repository;

import com.telusko.quizapp.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends MongoRepository<Quiz, String> {
}
