package models;

import lombok.Data;

@Data
public class BookModel {
    String isbn,
            title,
            subTitle,
            author,
            publish_date,
            publisher;

    int pages;

    String description,
            website;
}
