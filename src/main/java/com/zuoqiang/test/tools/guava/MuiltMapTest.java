package com.zuoqiang.test.tools.guava;

import com.google.common.collect.ArrayListMultimap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Multimap
 * <p>
 * 类似Map<K, List<V>>或者Map<K, Set<V>>这样的多层结构
 * </p>
 * @date 2020/5/6 11:38 上午
 */

public class MuiltMapTest {
    Map<String, List<StudentScore>> StudentScoreMap = new HashMap<String, List<StudentScore>>();

    public void testStudentScore() {

        for (int i = 10; i < 20; i++) {
            StudentScore studentScore = new StudentScore();
            studentScore.CourseId = 1001 + i;
            studentScore.score = 100 - i;
            addStudentScore("peida", studentScore);
        }

        System.out.println("StudentScoreMap:" + StudentScoreMap.size());
        System.out.println("StudentScoreMap:" + StudentScoreMap.containsKey("peida"));

        System.out.println("StudentScoreMap:" + StudentScoreMap.containsKey("jerry"));
        System.out.println("StudentScoreMap:" + StudentScoreMap.size());
        System.out.println("StudentScoreMap:" + StudentScoreMap.get("peida").size());

        List<StudentScore> StudentScoreList = StudentScoreMap.get("peida");
        if (StudentScoreList != null && StudentScoreList.size() > 0) {
            for (StudentScore stuScore : StudentScoreList) {
                System.out.println("stuScore one:" + stuScore.CourseId + " score:" + stuScore.score);
            }
        }
    }

    public void addStudentScore(final String stuName, final StudentScore studentScore) {
        List<StudentScore> stuScore = StudentScoreMap.get(stuName);
        if (stuScore == null) {
            stuScore = new ArrayList<StudentScore>();
            StudentScoreMap.put(stuName, stuScore);
        }
        stuScore.add(studentScore);
    }


    public static void main(String[] args) {
        MuiltMapTest test = new MuiltMapTest();
        //传统实现放方法
//        test.testStudentScore();
        //谷歌实现方法
        test.teststuScoreMultimap();
    }

    private void teststuScoreMultimap() {
        ArrayListMultimap<Object, Object> scoreMultimap = ArrayListMultimap.create();
        for (int i = 10; i < 20; i++) {
            StudentScore studentScore = new StudentScore();
            studentScore.CourseId = 1001 + i;
            studentScore.score = 100 - i;
            scoreMultimap.put("peida", studentScore);
        }
        System.out.println("scoreMultimap:" + scoreMultimap.size());
        System.out.println("scoreMultimap:" + scoreMultimap.keySet().size());
        System.out.println("scoreMultimap:" + scoreMultimap.keys());
        List<Object> studentScore = scoreMultimap.get("peida");
        studentScore.clear();
        StudentScore studentScoreNew = new StudentScore();
        studentScoreNew.CourseId = 1034;
        studentScoreNew.score = 67;
        studentScore.add(studentScoreNew);

        System.out.println("scoreMultimap:" + scoreMultimap.size());
        System.out.println("scoreMultimap:" + scoreMultimap.keys());
    }
}

class StudentScore {
    int CourseId;
    int score;
}
