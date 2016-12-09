package com.java8.newfeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * String :: valueOf   等价于  x -> String.valueOf(x)
 * Object :: toString  等价于  x -> x.toString();
 * x::toString         等价于  () -> x.toString()
 * ArrayList::new      d等价于  () -> new ArrayList<>();
 *
 * Created by Administrator on 2016/12/9.
 */
public class MethodReference {
    public static void main(String[] args) {
        // 方法引用::引用构造函数
        PersonFactory factory = new PersonFactory(Person::new);

        List<Person> personList = new ArrayList<>();

        Person p1 = (Person) factory.getPerson();
        p1.setName("Kobe");
        personList.add(p1);
        Person p2 = (Person) factory.getPerson();
        p2.setName("James");
        personList.add(p2);
        Person p3 = (Person) factory.getPerson();
        p3.setName("Paul");
        personList.add(p3);

        Person[] persons1 = personList.toArray(new Person[personList.size()]);
        System.out.print("排序前: ");
        printArray(persons1);

        // 方法引用::引用静态方法
        Arrays.sort(persons1, MethodReference::myCompare);
        System.out.print("排序后: ");
        printArray(persons1);
        System.out.println();

        Person[] persons2 = personList.toArray(new Person[personList.size()]);
        System.out.print("排序前: ");
        printArray(persons2);

        // 方法引用::用特定对象的实例方法
        Arrays.sort(persons2, p1::compare);
        System.out.print("排序后: ");
        printArray(persons2);
        System.out.println();

        Person[] persons3 = personList.toArray(new Person[personList.size()]);
        System.out.print("排序前: ");
        printArray(persons3);

        // 方法引用::引用特定类型的任意对象的实例方法
        Arrays.sort(persons3, Person::compareTo);
        System.out.print("排序后: ");
        printArray(persons3);

        PersonFactory peoplePersonFactory = new PersonFactory(People::new);
        People people = (People) peoplePersonFactory.getPerson();
        people.setPersons(personList);
        System.out.println(people.getMaleList((person) -> ((Person)person).getName().equals("Kobe")).toString());
    }

    public static void printArray(Person[] persons) {
        for (Person p : persons) {
            System.out.print(p.getName() + "  ");
        }
        System.out.println();
    }

    public static int myCompare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }

    interface PersonInterface {
        public boolean test(Person person);
    }
    static class People {
        public People() {
        }
        private List<Person> persons= new ArrayList<Person>();
//        public List<Person> getMaleList(PersonInterface filter) {
//            List<Person> res = new ArrayList<Person>();
//            persons.forEach(
//                    (Person person) ->
//                    {
//                        if (filter.test(person)) {//调用 PersonInterface 的方法
//                            res.add(person);
//                        }
//                    }
//            );
//            return res;
//        }
        public List<Person> getMaleList(Predicate filter) {
            List<Person> res = new ArrayList<Person>();
            persons.forEach(
                    (Person person) ->
                    {
                        if (filter.test(person)) {//调用 PersonInterface 的方法
                            res.add(person);
                        }
                    }
            );
            return res;
        }


        public void setPersons(List<Person> persons) {
            this.persons = persons;
        }
    }


    static class PersonFactory<T> {
        private Supplier<T> supplier;

        public PersonFactory(Supplier<T> t) {
            this.supplier =t;
        }

        public T getPerson() {
            return supplier.get();
        }
    }
}