package models;

import lombok.Data;

import java.util.List;

@Data
public class GetListOfBooksResponseModel {
    String userId,
            username;
    List<BookModel> books;
}
