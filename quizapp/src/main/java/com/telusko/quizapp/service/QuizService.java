package com.telusko.quizapp.service;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.Quiz;
import com.telusko.quizapp.model.Response;
import com.telusko.quizapp.repository.QuestionDao;
import com.telusko.quizapp.repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = new QuestionService().getRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(String id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isPresent()) {
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();
            for (Question q : questionsFromDB) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionsForUser.add(qw);
            }
            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> calculateResult(String id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isPresent()) {
            List<Question> questions = quiz.get().getQuestions();
            int right = 0;
            int i = 0;
            for (Response response : responses) {
                if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                    right++;
                }
                i++;
            }
            return new ResponseEntity<>(right, HttpStatus.OK);
        }
        return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
    }
}
