package nitconf.reviewermodule.reviewer.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import nitconf.reviewermodule.reviewer.Entities.Paper;
import nitconf.reviewermodule.reviewer.Entities.Review;
import nitconf.reviewermodule.reviewer.Repositories.PaperRepository;
import nitconf.reviewermodule.reviewer.Repositories.ReviewRepository;



@Service
public class ReviewService {


     @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PaperRepository paperRepository;


  

    


    @Autowired
    private MongoTemplate mongoTemplate;



    public int checkdeadLine(LocalDateTime currentDateTime, LocalDateTime deadLine)
    {
        if(currentDateTime.isAfter(deadLine))
        {
            return 0;

        }
        else
        {
            return 1;
        }

    }


    public boolean createReviewForPaper(String reviewBody, int paper_id)
    {
        Paper papertobereviewed = paperRepository.findByPaperid(paper_id);
        if(papertobereviewed==null)
        {
            return false;

        }
        

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime deadLine = papertobereviewed.getDeadLine();

        if(checkdeadLine(currentDateTime, deadLine)==0)

        {
            return false;
        }
        //create
        Review review = new Review(reviewBody);//Create the review with a constructor 
        reviewRepository.save(review);
        papertobereviewed.setReview(review);//set the Review field of the Paper object with the review
        papertobereviewed.setStatus("Reviewed");//change status
        paperRepository.save(papertobereviewed);
        return true;


    }
    public boolean updateReviewForPaper(String reviewBody, int paper_id)
    {
        Paper papertobeupdated= paperRepository.findByPaperid(paper_id);
        if(papertobeupdated.getStatus()=="Assigned")
        {
            return false;
        }
        
        papertobeupdated.setStatus("Assigned");
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime deadLine = papertobeupdated.getDeadLine();
        if(checkdeadLine(currentDateTime, deadLine)==0)
        {
            return false;

        }
        //delete the existing review
        Review existingReview = papertobeupdated.getReview();
        reviewRepository.delete(existingReview);


        //update 
        Review review = new Review(reviewBody);
        reviewRepository.save(review);
        papertobeupdated.setReview(review);
        papertobeupdated.setStatus("Reviewed");
        paperRepository.save(papertobeupdated);
        return true;


    }

    public boolean deleteReviewForPaper(int paper_id)
    {
        Paper papertobeunreviewed = paperRepository.findByPaperid(paper_id);
        if(papertobeunreviewed==null)
        {
            return false;
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime deadLine = papertobeunreviewed.getDeadLine();
        if(checkdeadLine(currentDateTime, deadLine)==0)
        {
            return false;

        }
        papertobeunreviewed.setStatus("Assigned");
        reviewRepository.delete(papertobeunreviewed.getReview());
        papertobeunreviewed.setReview(null);
        paperRepository.save(papertobeunreviewed);
        return true;
       

        

    }




}
