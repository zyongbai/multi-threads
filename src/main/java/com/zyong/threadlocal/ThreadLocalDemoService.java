package com.zyong.threadlocal;

/**
 * @author zyong
 * @date 2018/7/25
 */
public class ThreadLocalDemoService {
    private ThreadLocal<Person> threadLocal = new ThreadLocal<Person>();

    public Person getPerson() {
        Person person = threadLocal.get();
        if (person == null) {
            threadLocal.set(new Person());
            person = threadLocal.get();
        }
        return person;
    }

    public Person updatePerson(String name, int age) {
        Person person = threadLocal.get();
        person.setName(name);
        person.setAge(age);
        threadLocal.set(person);
        return person;
    }
}
