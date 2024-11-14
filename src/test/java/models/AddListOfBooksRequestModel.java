package models;

import lombok.Data;

import java.util.List;

@Data
public class AddListOfBooksRequestModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}


