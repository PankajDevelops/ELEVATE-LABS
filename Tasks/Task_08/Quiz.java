package ElevateLabs.Tasks.Task_08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions;
    private int score;
    private Scanner scanner;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        scanner = new Scanner(System.in);
        initializeQuestions();
    }

    private void initializeQuestions() {
        questions.add(new Question("What is the capital of France?",
                Arrays.asList("Berlin", "Madrid", "Paris", "Rome"), 2));
        questions.add(new Question("Which planet is known as the Red Planet?",
                Arrays.asList("Earth", "Mars", "Jupiter", "Venus"), 1));
        questions.add(new Question("What is 7 + 8?",
                Arrays.asList("12", "13", "14", "15"), 3));
        questions.add(new Question("Who developed Java?",
                Arrays.asList("Guido van Rossum", "James Gosling", "Bjarne Stroustrup", "Dennis Ritchie"), 1));
    }

    public void startQuiz() {
        System.out.println("Welcome to the Java Quiz!");
        System.out.println("-------------------------");

        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.getQuestionText());

            List<String> options = currentQuestion.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            int userAnswer = getUserAnswer(options.size());
            if (currentQuestion.isCorrect(userAnswer - 1)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was option " + (currentQuestion.getOptions().indexOf(options.get(currentQuestion.isCorrect(0) ? currentQuestion.getOptions().indexOf(options.get(0)) : currentQuestion.getOptions().indexOf(options.get(1)))) + 1) + ": " + options.get(currentQuestion.isCorrect(0) ? currentQuestion.getOptions().indexOf(options.get(0)) : currentQuestion.getOptions().indexOf(options.get(1))));
            }
        }

        displayResult();
    }

    private int getUserAnswer(int numberOfOptions) {
        int answer = -1;
        while (true) {
            System.out.print("Enter your answer (1-" + numberOfOptions + "): ");
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                if (answer >= 1 && answer <= numberOfOptions) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and " + numberOfOptions + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return answer;
    }

    private void displayResult() {
        System.out.println("\n-------------------------");
        System.out.println("Quiz Finished!");
        System.out.println("Your final score is: " + score + " out of " + questions.size());
        System.out.println("-------------------------");
        scanner.close();
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.startQuiz();
    }
}
