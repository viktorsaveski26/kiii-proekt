package com.telusko.quizapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;

@Data
@Document(collection = "quizzes")
public class Quiz {
    @Id
    private String id; // MongoDB uses String for ids by default.
    private String title;

    // Reference to Question documents
    @DBRef
    private List<Question> questions;
}
