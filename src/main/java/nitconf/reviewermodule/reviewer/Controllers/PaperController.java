package nitconf.reviewermodule.reviewer.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nitconf.reviewermodule.reviewer.Entities.Paper;
import nitconf.reviewermodule.reviewer.Repositories.PaperRepository;
import nitconf.reviewermodule.reviewer.Service.PaperService;

@RestController
@RequestMapping("/papers")
public class PaperController {


    @Autowired
    private PaperService paperService;

    @GetMapping("/assignedpapers")
    public List<Paper> assignedpapers()
    {
        return paperService.assignedPapers();
    }
    
    @GetMapping("/reviewedpapers")
    public List<Paper> reviewedpapers()
    {
        return paperService.reviewedPapers();
    }


}
