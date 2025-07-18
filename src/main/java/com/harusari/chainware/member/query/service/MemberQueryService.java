package com.harusari.chainware.member.query.service;

import com.harusari.chainware.member.command.application.dto.response.EmailExistsResponse;
import com.harusari.chainware.member.query.dto.request.MemberSearchRequest;
import com.harusari.chainware.member.query.dto.response.LoginHistoryResponse;
import com.harusari.chainware.member.query.dto.response.MemberSearchDetailResponse;
import com.harusari.chainware.member.query.dto.response.MemberSearchResponse;
import com.harusari.chainware.member.query.dto.response.MyMemberDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryService {

    EmailExistsResponse checkEmailDuplicate(String email);

    Page<MemberSearchResponse> searchMembers(MemberSearchRequest memberSearchRequest, Pageable pageable);

    MemberSearchDetailResponse getMemberDetail(Long memberId);

    MyMemberDetailResponse getMyProfile(Long memberId);

    Page<LoginHistoryResponse> searchMemberLoginHistory(Long memberId, Pageable pageable);

}