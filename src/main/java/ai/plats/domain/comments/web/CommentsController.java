package ai.plats.domain.comments.web;


import ai.plats.domain.comments.entity.Comments;
import ai.plats.domain.comments.service.CommentsService;
import ai.plats.domain.user.service.UserJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class CommentsController {


    @Autowired
    CommentsService commentService;

    @Autowired
    UserJoinService userJoinService;

    @RequestMapping(value="/comments_write" , method = RequestMethod.POST)
    @ResponseBody
    public String comments_write(Comments comments, Principal principal){

        comments.setUser(userJoinService.findUserByIdxUser(Integer.parseInt(principal.getName())).get());
        comments.setDel("N");
        commentService.saveComments(comments);

        String result="/goViewWriting?idxWriting="+comments.getIdxWriting();
        return result;
    }

    @RequestMapping(value="/delComments", method = RequestMethod.GET)
    @ResponseBody
    public String delComments(Comments comments){

        comments = commentService.findByIdxComments(comments.getIdxComments());
        comments.setDel("Y");
        commentService.saveComments(comments);

        return "deleted";

    }

    @RequestMapping(value="/updateComments", method = RequestMethod.POST)
    @ResponseBody
    public String delComments(@RequestParam("idxComments") Integer idxComments,@RequestParam("content") String content, Principal principal){
        System.out.println("오죠?");
        System.out.println(idxComments);
        System.out.println(content);

        Comments comments = commentService.findByIdxComments(idxComments);
        comments.setUser(userJoinService.findUserByIdxUser(Integer.parseInt(principal.getName())).get());
        comments.setContent(content);
        Comments result = commentService.updateComments(comments);
        System.out.println(result.getContent());

        String redirect_url="/goViewWriting?idxWriting="+comments.getIdxWriting();
        return redirect_url;

    }



}
