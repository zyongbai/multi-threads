package com.zyong.threadlocal;

/**
 * @author zyong
 * @date 2018/7/25
 */
public class Person2 implements Cloneable {
    private String name;
    private int age;

    private static volatile Person2 person;

    private Person2() {
        this.name = "zyong";
        this.age = 27;
    }

    public static Person2 getInstance() {
        if (person == null) {
            synchronized (Person2.class) {
                if (person == null) {
                    person = new Person2();
                }
            }
        }
        return person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

	@Override
	public Object clone() {
		Person2 person = null;
		try {
			 person = (Person2) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return person;
	}
    
}
