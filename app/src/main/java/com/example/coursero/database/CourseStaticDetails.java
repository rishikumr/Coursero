package com.example.coursero.database;

import com.example.coursero.model.Quiz;
import com.example.coursero.util.Constants;

public class CourseStaticDetails {

    public final static String[] URL_COURSE_Name = new String[]{"Flutter Tutorial for Beginners #2 - Flutter Overview"
            , "Flutter Tutorial for Beginners #3 - Dart Primer"
            , "Flutter Tutorial for Beginners #4 - Creating a Flutter App in Android Studio"
            , "Flutter Tutorial for Beginners #5 - Scaffold & AppBar Widgets"
            , "Flutter Tutorial for Beginners #6 - Colours & Fonts"
            , "Flutter Tutorial for Beginners #7 - Stateless Widgets & Hot Reload"
            , "Flutter Tutorial for Beginners #8 - Images & Assets"
            , "Flutter Tutorial for Beginners #9 - Buttons & Icons"
            , "Flutter Tutorial for Beginners #10 - Containers & Padding"
            , "Flutter Tutorial for Beginners #11 - Rows"
    };


    public final static String[] URL_COURSE_Duration = new String[]{
            "426", "2712", "1346", "846", "1042", "839", "652", "750", "752", "818"
    };

    public final static String baseRemoteUrlFirst = "https://www.youtube.com/watch?v=";
    public final static String[] URL_COURSE_URLS = new String[]{
            "a6oKFvGuTH4"
            , "H0cJ0gUlgE8"
            , "ABmqtI7ec7E"
            , "Hxh6nNHSUjo"
            , "zwPBMg3SHVU"
            , "km2P_KQJyO0"
            , "C5lpPjoivaw"
            , "TSIhiZ5jRB0"
            , "FLQ-Vhw1NYQ"
            , "bKueYVtV0eA"
    };


    public final static Quiz[] QUIZ_LIST = new Quiz[10];
    public final static String[] QUESTIONS_COURSE = new String[]{
            "Q1. Who created Flutter",
            "Q2. What is Flutter",
            "Q3. Which programing language is used by Flutter",
            "Q4. Who created Dart programing language?",
            "Q5. Flutter is an __________ mobile application development framework created by Google.",
            "Q6. Flutter is used to develop applications for __________ and __________.",
            "Q7. The first version of Flutter was known as codename __________ and ran on the Android operating system.",
            "Q8. Flutter's engine, written primarily in C++, provides low-level rendering support using Google's Skia graphics library.",
            "Q9. Flutter apps are written in the __________ language and make use of many of the language's more advanced features.",
            "Q10. Due to App Store restrictions on dynamic code execution, Flutter apps use __________ compilation on iOS.",
    };
    public final static String[][] ANSWERS_LIST_COURSE = new String[10][4];
    public static int[] ANSWER_COURSE = new int[]{
            2,
            3,
            1,
            0,
            1,
            2,
            1,
            1,
            2,
            1
    };

    public static void prepareQuizList() {
        {
            prepareQuizAnswerList();
            for (int i = 0; i < 10; i++) {
                QUIZ_LIST[i] = new Quiz(QUESTIONS_COURSE[i], ANSWERS_LIST_COURSE[i], ANSWER_COURSE[i]);
                Constants.logD(QUIZ_LIST[i].getQuestion());
            }
        }
    }

    private static void prepareQuizAnswerList() {
        ANSWERS_LIST_COURSE[0][0] = " Facebook ";
        ANSWERS_LIST_COURSE[0][1] = " Adobe";
        ANSWERS_LIST_COURSE[0][2] = " Google";
        ANSWERS_LIST_COURSE[0][3] = " Microsoft";

        ANSWERS_LIST_COURSE[1][0] = " Android Development Kit";
        ANSWERS_LIST_COURSE[1][1] = " IOS Development Kit";
        ANSWERS_LIST_COURSE[1][2] = " Web Development Kit";
        ANSWERS_LIST_COURSE[1][3] = " SDK to build beautiful IOS, Android, Web & Desktop Native Apps";

        ANSWERS_LIST_COURSE[2][0] = " Ruby";
        ANSWERS_LIST_COURSE[2][1] = " Dart";
        ANSWERS_LIST_COURSE[2][2] = " C++";
        ANSWERS_LIST_COURSE[2][3] = " Kotlin";

        ANSWERS_LIST_COURSE[3][0] = " Lars Bak and Kasper Lund";
        ANSWERS_LIST_COURSE[3][1] = " Brendan Eich";
        ANSWERS_LIST_COURSE[3][2] = " Bjarne Stroustru";
        ANSWERS_LIST_COURSE[3][3] = " Jeremy Ashkenas";

        ANSWERS_LIST_COURSE[4][0] = " Open-source ";
        ANSWERS_LIST_COURSE[4][1] = " Shareware";
        ANSWERS_LIST_COURSE[4][2] = " Both ";
        ANSWERS_LIST_COURSE[4][3] = " None of the above";

        ANSWERS_LIST_COURSE[5][0] = " Android ";
        ANSWERS_LIST_COURSE[5][1] = " iOS";
        ANSWERS_LIST_COURSE[5][2] = " Both";
        ANSWERS_LIST_COURSE[5][3] = " None";

        ANSWERS_LIST_COURSE[6][0] = " Cloud";
        ANSWERS_LIST_COURSE[6][1] = " Sky";
        ANSWERS_LIST_COURSE[6][2] = " Rain";
        ANSWERS_LIST_COURSE[6][3] = " None";

        ANSWERS_LIST_COURSE[7][0] = " True";
        ANSWERS_LIST_COURSE[7][1] = " False";
        ANSWERS_LIST_COURSE[7][2] = " Maybe";
        ANSWERS_LIST_COURSE[7][3] = " None";

        ANSWERS_LIST_COURSE[8][0] = " C";
        ANSWERS_LIST_COURSE[8][1] = " Java";
        ANSWERS_LIST_COURSE[8][2] = " Dart";
        ANSWERS_LIST_COURSE[8][3] = " Swift";

        ANSWERS_LIST_COURSE[9][0] = " ahead-of-time (AOT)";
        ANSWERS_LIST_COURSE[9][1] = " ahed-of-code (AOC)";
        ANSWERS_LIST_COURSE[9][2] = " Both";
        ANSWERS_LIST_COURSE[9][3] = " None";

    }


}

