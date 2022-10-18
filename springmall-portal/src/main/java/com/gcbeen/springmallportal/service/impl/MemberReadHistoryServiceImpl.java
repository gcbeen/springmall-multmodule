package com.gcbeen.springmallportal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gcbeen.springmallportal.repository.IMemberReadHistoryRepository;
import com.gcbeen.springmallportal.domain.MemberReadHistory;
import com.gcbeen.springmallportal.service.IMemberReadHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberReadHistoryServiceImpl implements IMemberReadHistoryService {

    @Autowired
    private IMemberReadHistoryRepository memberReadHistoryRepository;

    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        memberReadHistoryRepository.save(memberReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList = new ArrayList<>();
        for (String id : ids) {
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }
        memberReadHistoryRepository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
    
}
