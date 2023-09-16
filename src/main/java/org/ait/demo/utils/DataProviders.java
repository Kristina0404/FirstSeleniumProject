package org.ait.demo.utils;

import org.ait.demo.models.Registration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @org.testng.annotations.DataProvider
    public Iterator<Object[]> newUser(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Christel21", "Hacker","hacker21@gmail.com","Hacker123$"});
        list.add(new Object[]{"Christel22", "Hacker","hacker22@gmail.com","Hacker123$"});
        list.add(new Object[]{"Christel23", "Hacker","hacker23@gmail.com","Hacker123$"});

        return list.iterator();
    }
    @org.testng.annotations.DataProvider
    public Iterator<Object[]> newUserWithCSVFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(
                "src/test/resources/users.csv"));
        String line = reader.readLine();
        while(line!=null) {
            String[] split = line.split(",");
            list.add(new Object[]{
                    new Registration().setName(split[0])
                            .setSurname(split[1])
                            .setEmail(split[2])
                            .setPassword(split[3])
            });
            line = reader.readLine();
        }

        return list.iterator();
    }
}
