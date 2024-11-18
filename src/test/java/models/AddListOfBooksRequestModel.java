package models;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AddListOfBooksRequestModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}


