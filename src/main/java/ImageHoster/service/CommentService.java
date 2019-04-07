package ImageHoster.service;


import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;


    //Calls the createComment() method in the Comment repository passes the newComment to be persisted in the database
    public void createComment(Comment newComment){
        commentRepository.createComment(newComment);
    }

    //Call the getCommentsByImageId() method in the Repository and obtain a List of all the Comments related to the imageId in the database
    public List<Comment> getCommentsByImageId(Integer imageId){
        return commentRepository.getCommentsByImageId(imageId);
    }

}
