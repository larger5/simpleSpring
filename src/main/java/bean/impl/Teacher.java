package bean.impl;

import bean.Person;

public class Teacher implements Person {

    @Override
    public void introduce(String type) {
        System.out.println("I am a " + type + " teacher");
    }
}
