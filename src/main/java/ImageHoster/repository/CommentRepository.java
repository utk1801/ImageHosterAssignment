package ImageHoster.repository;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;


import java.util.List;

//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class CommentRepository {


    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;



    //The method receives the Comment object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public Comment createComment(Comment newComment){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newComment;
    }



    //The method creates an instance of EntityManager
    //Executes JPQL query to fetch all the Comments from the database related to the imageId
    //Returns the list of all the comments fetched from the database
    public List<Comment> getCommentsByImageId(Integer imageId){

        EntityManager em = emf.createEntityManager();

        try{
            TypedQuery<Comment> typedQuery = em.createQuery("SELECT i FROM Comment i where i.images.id = :imageId",Comment.class).setParameter("imageId",imageId);
            return typedQuery.getResultList();
        }catch (NoResultException nre) {
            return null;
        }
    }
}
