package ImageHoster.controller;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;



    //This controller method is called when the request pattern is of type 'image/{imageId}/{imageTitle}/comment' and also the incoming request is of POST type
    //The method receives all the details of the comment to be stored in the database, and now the comment will be sent to the business logic to be persisted in the database
    //After you get the comment, set the user of the comment by getting the logged in user from the Http Session
    //Set the date on which the comment is posted
    //Get the image by using the imageId and method getImageByImageId() in ImageServices
    //set the image on which the comment has been posted.
    //Set the Text of the comment.
    //After storing the comment, this method directs to the logged in user to '/images/{imageId}/{imageTitle}' that is showimage() in the ImageController to display image details and comments.

    @RequestMapping(value = "image/{imageId}/{imageTitle}/comments",method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @RequestParam("comment")String text,Comment newComment, HttpSession session ){

        User user = (User) session.getAttribute("loggeduser");

        newComment.setUser(user);
        newComment.setDate(new Date());

        Image image =imageService.getImage(imageId);
        newComment.setImages(image);
        newComment.setText(text);
        commentService.createComment(newComment);
        return "redirect:/images/{imageId}/{imageTitle}";
    }

}
