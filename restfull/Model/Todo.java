package com.example.restfull.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Todo {

//no need for ID and instead using index for arrayList
    private String title,description;
    private boolean status;

}
