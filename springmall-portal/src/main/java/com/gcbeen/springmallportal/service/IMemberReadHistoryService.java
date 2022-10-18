package com.gcbeen.springmallportal.service;

import java.util.List;

import com.gcbeen.springmallportal.domain.MemberReadHistory;

/**
 * 会员浏览记录管理 Service
 */
public interface IMemberReadHistoryService {
    /**
     * 生成浏览记录
     * @param memberReadHistory
     * @return
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     * @param ids 记录 ID
     * @return
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     * @param memberId 用户 ID
     * @return
     */
    List<MemberReadHistory> list(Long memberId);
}
