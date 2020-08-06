package com.lamdba;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class PersonTest
{
    private String name;
    private Integer age;

    @Test
    public void test()
    {
        PersonTest person3 = new PersonTest("张三", 20);
        PersonTest person4 = new PersonTest("李四", 20);
        PersonTest person5 = new PersonTest("王五", 20);

    }

    //找到名字一样的
    public List<PersonTest> getByName(String name, List<PersonTest> persons)
    {
        return persons.stream().filter(personTest -> personTest.getName().equals(name)).collect(Collectors.toList());
    }

    //找到大于某个年纪的Person
    public List<PersonTest> getByAge(Integer age, List<PersonTest> persons)
    {
        return persons.stream().filter(personTest -> personTest.getAge() > age).collect(Collectors.toList());
    }

    public List<PersonTest> getByAge2(Integer age, List<PersonTest> persons)
    {
        BiFunction<Integer, List<PersonTest>, List<PersonTest>> biFunction = (ageofPerson, personList) -> personList.stream().filter(personTest -> personTest.getAge() > age).collect(Collectors.toList());

        return biFunction.apply(age, persons);
    }

}
