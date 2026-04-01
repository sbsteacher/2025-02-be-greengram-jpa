package com.green.greengram.application.feedcomment;

import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.entity.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {
    @Query(" SELECT new com.green.greengram.application.feedcomment.model.FeedCommentGetRes(fc.id, fc.feed.id, fc.comment, u.id, u.nm, u.pic, fc.createdAt) " +
            " FROM FeedComment fc JOIN fc.user u " +
            " WHERE fc.feed.id = :feedId " +
            " ORDER BY fc.feed.id DESC ")
    List<FeedCommentGetRes> getFeedCommentList(@Param("feedId") Long feedId
                                             , Pageable pageable);
}
