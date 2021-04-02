package ai.plats.domain.comment.service;


import ai.plats.domain.comment.entity.Comments;
import ai.plats.domain.comment.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    CommentsRepository commentsRepository;


    public Comments saveComments(String idxWriting, String comments, String commWriter) {
        return commentsRepository.save(new Comments(Integer.parseInt(idxWriting),comments,commWriter,"N"));
    }

    public List<Comments> findByIdxWriting(int idxWriting) {
        return commentsRepository.findByIdxWriting(idxWriting);
    }
}
