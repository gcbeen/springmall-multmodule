package com.gcbeen.springmallportal.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gcbeen.springmallportal.domain.MemberReadHistory;

/**
 * 用户浏览商品历史 Repository
 */
public interface IMemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String> {
    /**
     * 根据会员 id 按时间倒序获取浏览记录
     * @param memberId 会员 ID
     * @return
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);

}
