package uz.b25.testing_system.client.ui;

import uz.b25.testing_system.client.util.ScannerUtil;
import uz.b25.testing_system.server.entity.Question;
import uz.b25.testing_system.server.payload.QuestionDTO;
import uz.b25.testing_system.server.payload.Result;
import uz.b25.testing_system.server.service.CategoryService;
import uz.b25.testing_system.server.service.QuestionService;

import java.util.List;
import java.util.Set;

public class QuestionUI {
    public static void addQuestion() {

        if(!CategoryUI.showCategories()) return;

        System.out.println("Enter category name");
        String categoryName = ScannerUtil.SCANNER_STR.nextLine();

        System.out.println("Enter question text (e.g.  2*2=?)");
        String text = ScannerUtil.SCANNER_STR.nextLine();

        System.out.println("Enter correct answer (e.g.  4)");
        String correctAnswer = ScannerUtil.SCANNER_STR.nextLine();

        System.out.println("Enter wrong 3 variants with '/' (e.g.  5 / 8 / 6) ");
        String wrongVariants = ScannerUtil.SCANNER_STR.nextLine();

        QuestionDTO questionDTO = new QuestionDTO(
                categoryName, text,
                correctAnswer, wrongVariants
        );

        Result result = QuestionService.addQuestion(questionDTO);
        System.out.println(result);
    }

    public static void showQuestions() {
        if(!CategoryUI.showCategories()) return;

        System.out.println("Enter category name");
        String categoryName = ScannerUtil.SCANNER_STR.nextLine();

        List<Question> questionList = QuestionService.getQuestionListByCategory(categoryName.toUpperCase());

        if(questionList.isEmpty()){
            System.out.println("No questions by this category");
        }else{
            questionList.forEach(System.out::println);
        }
    }
}
