import stream.genetator.CollectionStreamGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author wfnuser
 * on 2019/5/26.
 */
public class TestMyStream {

    public static class Person {
        public int age;
        public String name;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args){
        List<Person> people = new ArrayList<Person>();

        people.add(new Person("Erlich", 10));
        people.add(new Person("Richard", 12));
        people.add(new Person("Dinesh", 14));
        people.add(new Person("Jared", 16));
        people.add(new Person("Gilfoyle", 18));
        people.add(new Person("Jian-Yang", 20));
        people.add(new Person("Big head", 22));

        int sumAge = CollectionStreamGenerator.getListStream(people).map(item -> item.age).reduce(0, (i1, i2) -> i1 + i2);

        String concatAge = CollectionStreamGenerator.getListStream(people).map(item -> item.age).reduce("", (i1, i2) -> i1 + i2);

        System.out.println(sumAge);
        System.out.println(concatAge);
    }
}
