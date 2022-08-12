import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nantian.test.java.util.pojo.Student;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/3/23
 */
public class RepeatMain {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        Student student1 = new Student("1001", "zhangSan", "dad1");
        Student student2 = new Student("1002", "liSi", "dad2");
        Student student3 = new Student("1003", "wanWu", "dad3");

        students.add(student1);
        students.add(student2);
        students.add(student3);

    }
}
