package framekworkTest.iocTest.bean.impl;

import framekworkTest.iocTest.bean.Person;

public class Teacher implements Person {

    @Override
    public void introduce(String type) {
        System.out.println("I am a " + type + " teacher");
    }
}
