package ai.plats.domain.comments.service;


import ai.plats.domain.comments.entity.Comments;
import ai.plats.domain.comments.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    CommentsRepository commentsRepository;


    public Comments saveComments(Comments vo) {

        return commentsRepository.save(vo);
    }

    public List<Comments> findByIdxWriting(int idxWriting) {
        return commentsRepository.findByIdxWritingAndDel(idxWriting,"N");
    }

    public Comments findByIdxComments(int idxComments){

      Comments comments = commentsRepository.findByIdxComments(idxComments);
        return comments;
    }

    public Comments updateComments(Comments comments){

        Comments result = commentsRepository.save(comments);

        return result;
    }



}