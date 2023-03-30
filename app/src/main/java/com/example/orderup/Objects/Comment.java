package com.example.orderup.Objects;
import com.example.orderup.Objects.User;

/*
Comment class; This object class represents a User's comment on the application.
               The comment is stored as a string and the user of the comment is stored as a "User" object.
 */
public class Comment {
    private final String comment;
    private final User user;

    public Comment(String comment, User user){
        this.comment=comment;
        this.user=user;
    }

    /*Getter methods for String comment and User user of the comment*/
    public String getComment(){
        return comment;
    }
    public User userOfComment(){
        return user;
    }


}
