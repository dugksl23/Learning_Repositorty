package ai.plats.domain.board.web;


import ai.plats.domain.board.entity.Comments;
import ai.plats.domain.board.repository.CommentsRepository;
import ai.plats.domain.board.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class CommentsController {


    @Autowired
    CommentsService commentService;

    @RequestMapping(value="/comments_write" , method = RequestMethod.POST)
    @ResponseBody
    public String comments_write(HttpServletRequest req, Principal principal, Model model){

        String idxWriting=req.getParameter("idxWriting");
        String comments=req.getParameter("commContent");

//        System.out.println(">>"+idxWriting);
//        System.out.println(">>"+comments);
        Comments inputComments=commentService.saveComments(idxWriting,comments,Integer.parseInt(principal.getName()));
        String result="/goViewWriting?idxWriting="+idxWriting;
        return result;
    }




}
