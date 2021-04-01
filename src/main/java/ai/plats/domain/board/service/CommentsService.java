package ai.plats.domain.board.service;


import ai.plats.domain.board.entity.Comments;
import ai.plats.domain.board.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
