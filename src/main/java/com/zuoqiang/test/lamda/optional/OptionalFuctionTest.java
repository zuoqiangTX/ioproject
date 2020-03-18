package com.zuoqiang.test.lamda.optional;

import com.zuoqiang.test.lamda.data.Person;

import java.util.Optional;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Optional默认的方法
 * <p>
 * 注意：
 * 错误示范
 * <p>
 * public static String getGender(Student student)
 * {
 * Optional<Student> stuOpt =  Optional.ofNullable(student);
 * if(stuOpt.isPresent())
 * {
 * return stuOpt.get().getGender();
 * }
 * return "Unkown";
 * }
 * </p>
 *  使用Optional开发时要注意正确使用Optional的“姿势”，特别注意不要使用上文提到的错误示范，
 * 谨慎使用isPresent()和get()方法，尽量多使用map()、filter()、orElse()等方法来发挥Optional的作用。
 * </p>
 * @date 2020/3/18 11:28 上午
 */

public class OptionalFuctionTest {
    public static void main(String[] args) {


        Person person = null;
//        System.out.println(get(person));
//        System.out.println(isPresent(person));
//        ifPresent(person);
//        filterAge(person);
//        System.out.println(getAge(person).get());
//        System.out.println(getAgeFluMap(person).get());
//        System.out.println(orElse(person));
//        System.out.println(orElseGet(person));
        System.out.println(orElseThrow(person));
    }


    /**
     * get方法，如果空对象get会抛出异常
     * of方法会判断传入对象是否为null，null的话会抛空指针
     * get方法如果传入对象为null，会抛出NoSuchElementException
     *
     * @param person
     * @return
     */
    public static String get(Person person) {
//        return Optional.of(person).map(Person::getName).get();
        return Optional.ofNullable(person).map(Person::getName).get();
    }

    /**
     * isPresent()方法用于判断包装对象的值是否非空
     *
     * @param person
     * @return
     */
    public static boolean isPresent(Person person) {
        return Optional.ofNullable(person).isPresent();
    }


    /**
     * 如果对象不为null，调用ifPresent方法内的逻辑
     * 由于ifPresent()方法内部做了null值检查，调用前无需担心NPE问题。
     *
     * @param person
     */
    public static void ifPresent(Person person) {
        Optional.ofNullable(person).ifPresent(u -> System.out.println(u.getName()));
    }

    /**
     * filter()方法接受参数为Predicate对象，用于对Optional对象进行过滤，
     * 如果符合Predicate的条件，返回Optional对象本身，否则返回一个空的Optional对象。
     *
     * @param person
     */
    public static void filterAge(Person person) {
        person = new Person();
        person.setAge(1);
//        person.setAge(-1);
//        person = null;
        Optional.ofNullable(person).filter(o -> o.getAge() > 0).ifPresent(u -> System.out.println(u.getAge()));
    }

    /**
     * map()方法的参数为Function（函数式接口）对象，map()方法将Optional中的包装对象用Function函数进行运算，
     * 并包装成新的Optional对象（包装对象的类型可能改变）。
     *
     * @param person
     * @return
     */
    public static Optional<Integer> getAge(Person person) {
        return Optional.ofNullable(person).map(p -> p.getAge());
    }

    /**
     * 跟map()方法不同的是，入参Function函数的返回值类型为Optional<U>类型，
     * 而不是U类型，这样flatMap()能将一个二维的Optional对象映射成一个一维的对象。
     *
     * @param person
     * @return
     */
    public static Optional<Integer> getAgeFluMap(Person person) {
        return Optional.ofNullable(person).flatMap(p -> Optional.ofNullable(p.getAge()));
    }

    /**
     * orElse()方法功能比较简单，即如果包装对象值非空，返回包装对象值，否则返回入参other的值（默认值）。
     *
     * @return
     */
    public static String orElse(Person person) {
        return Optional.ofNullable(person).orElse(new Person("zuo", 1)).getName();
    }

    /**
     * orElseGet()方法与orElse()方法类似，区别在于orElseGet()方法的入参为一个Supplier对象，
     * 用Supplier对象的get()方法的返回值作为默认值。
     *
     * @param person
     * @return
     */
    public static String orElseGet(Person person) {
//        person = new Person("zuo", 1);
        return Optional.ofNullable(person).orElseGet(() -> new Person("an", 1)).getName();
    }

    /**
     * orElseThrow()方法适用于包装对象值为空时需要抛出特定异常的场景。
     *
     * @param person
     * @return
     */
    public static String orElseThrow(Person person) {
        return Optional.ofNullable(person).orElseThrow(() -> new RuntimeException("返回为空！")).getName();
    }
}
