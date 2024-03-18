package nitconf.reviewermodule.reviewer.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nitconf.reviewermodule.reviewer.Entities.Paper;
import nitconf.reviewermodule.reviewer.Repositories.PaperRepository;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    public List<Paper> reviewedPapers()
    {
        return paperRepository.findByStatus("Reviewed");
        
    }
    public List<Paper> assignedPapers()
    {
        return paperRepository.findByStatus("Assigned");
    }



}
