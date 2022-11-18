package uz.b25.testing_system.client.ui;

import uz.b25.testing_system.client.Application;
import uz.b25.testing_system.client.util.ScannerUtil;
import uz.b25.testing_system.server.database.Database;
import uz.b25.testing_system.server.entity.Question;
import uz.b25.testing_system.server.entity.TestHistory;
import uz.b25.testing_system.server.entity.User;
import uz.b25.testing_system.server.service.QuestionService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class TestUI {
    public static void solveTest() {
        System.out.println("1 question's cost 1 $");
        if (!CategoryUI.showCategories()) {
            return;
        }
        System.out.println("Enter category name");
        String categoryName = ScannerUtil.SCANNER_STR.nextLine();
        List<Question> questionList = QuestionService.getQuestionListByCategory(categoryName.toUpperCase());
        if (questionList.isEmpty()) {
            System.out.println("No questions by this category");
        } else {
            if (questionList.size() > Application.sessionUser.getBalance()) {
                System.out.println("You have no enough money");
                return;
            }
            List<TestHistory> testHistories = new ArrayList<>();
            String key;
            int t = 0, right = 0;
            for (Question question : questionList) {
                List<String> variants = question.getVariants();
                TestHistory testHistory = new TestHistory();
                Collections.shuffle(variants);
                System.out.println(question.getText());
                System.out.println("A) " + variants.get(0));
                System.out.println("B) " + variants.get(1));
                System.out.println("C) " + variants.get(2));
                System.out.println("D) " + variants.get(3));
                System.out.println("Javobni kiriting: ");
                key = ScannerUtil.SCANNER_STR.nextLine();
                if (key.equalsIgnoreCase(question.getCorrectAnswer())) {
                    testHistory.setScore(1);
                    right++;
                }
                testHistory.setUser(Application.sessionUser);
                testHistory.setSubject(question.getSubject());
                testHistories.add(testHistory);
                t++;
                if (t == 30) {
                    break;
                }
            }
            testHistories.addAll(Database.TEST_HISTORY_MAP.get(Application.sessionUser));
            System.out.println("Siz " + right + " ta to'g'ri javob berdingiz");
            Database.TEST_HISTORY_MAP.put(Application.sessionUser, testHistories);
            Application.sessionUser.setBalance(Application.sessionUser.getBalance() - t);
        }
    }

    public static void showTestHistory() {
        List<TestHistory> testHistories = Database.TEST_HISTORY_MAP.get(Application.sessionUser);
        if (testHistories.size() == 0) {
            System.out.println("Hali test yechmadingiz!");
            return;
        }
        testHistories
                .forEach(testHistory -> System.out.println(
                        "\nTest's subject: " + testHistory.getSubject() +
                        "\nTime: " + testHistory.getCreatedAt().
                        format(DateTimeFormatter.ofPattern("'Sana' dd.MM.yyyy '\tVaqt' HH:mm:ss"))+
                                "Your score: " + testHistory.getScore()));
//        for (int i = 0; i < testHistories.size(); i++) {
//            System.out.println("Your score: " + testHistories.get(i).getScore() +
//                    "\nTest's subject: " + testHistories.get(i).getSubject() +
//                    "\nSolved time: " + testHistories.get(i).getCreatedAt().toLocalDate() +
//                    testHistories.get(i).getCreatedAt().toLocalTime());
//        }
    }
}
