package com.github.fabioper.api.services;

import com.github.fabioper.api.dtos.CreateQuizDTO;
import com.github.fabioper.api.dtos.UpdateQuizDTO;
import com.github.fabioper.api.entities.Option;
import com.github.fabioper.api.entities.Question;
import com.github.fabioper.api.entities.Quiz;
import com.github.fabioper.api.mappers.Mappers;
import com.github.fabioper.api.repositories.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public void createQuiz(CreateQuizDTO dto) {
        var newQuiz = Mappers.mapToQuizEntity(dto);
        quizRepository.save(newQuiz);
    }

    public Quiz updateQuiz(UUID id, UpdateQuizDTO dto) {
        var quizToBeUpdated = quizRepository.findById(id).orElseThrow();

        quizToBeUpdated.setTitle(dto.title());

        var questions = quizToBeUpdated.getQuestions();

        var updatedQuestions = dto.questions().stream().map(questionDto -> {
            if (questionDto.id() == null) {
                return new Question(
                    questionDto.statement(),
                    questionDto.options().stream().map(optionDto -> new Option(
                        optionDto.text(),
                        optionDto.isCorrect()
                    )).collect(Collectors.toList())
                );
            }

            var existingQuestion = questions
                .stream()
                .filter(x -> x.getId().equals(questionDto.id()))
                .findFirst()
                .orElseThrow();

            existingQuestion.setStatement(questionDto.statement());

            var options = existingQuestion.getOptions();

            var updatedOptions = questionDto.options().stream().map(optionDto -> {
                if (optionDto.id() == null) {
                    return new Option(optionDto.text(), optionDto.isCorrect());
                }

                var existingOption = options
                    .stream()
                    .filter(x -> x.getId().equals(optionDto.id()))
                    .findFirst()
                    .orElseThrow();

                existingOption.setText(optionDto.text());
                existingOption.setCorrect(optionDto.isCorrect());

                return existingOption;
            }).collect(Collectors.toList());

            existingQuestion.setOptions(updatedOptions);

            return existingQuestion;
        }).collect(Collectors.toList());

        quizToBeUpdated.setQuestions(updatedQuestions);

        return quizRepository.save(quizToBeUpdated);
    }

    public List<Quiz> listQuizzes() {
        return quizRepository.findAll();
    }
}
