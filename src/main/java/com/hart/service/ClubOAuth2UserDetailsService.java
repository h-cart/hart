package com.hart.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.member.ClubMember;
import com.hart.domain.member.ClubMember2;
import com.hart.domain.member.ClubMemberRole;
import com.hart.mapper.MemberMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ClubOAuth2UserDetailsService extends DefaultOAuth2UserService {

	// db저장을 위해S
	@Autowired
	private MemberMapper memberMapper;

	// 패스워드 암호화
	@Autowired
	private PasswordEncoder passwordEncoder;

	private ClubMember2 saveSocialMember(String email) throws SQLException {
		log.info("saveSocialMember  시작");
		// 기본에 동일한 이메일로 가입한 회원인지 확인
		ClubMember2 result = memberMapper.findByEmail(email, 1);
		// 기본 회원이면 정보 반환
		if (!(result == null)) {
			log.info("기존 회원");
			return result;
		} // end if

		// 가입한적이 없다면 추가 패스워드 1111 이름은 이메일주소
		ClubMember clubMember = new ClubMember();
		clubMember.setMid(email);
		clubMember.setMname(email);
		clubMember.setMpassword(passwordEncoder.encode("1111"));
		clubMember.setSocial(1);
		clubMember.setMroles(ClubMemberRole.USER.toString());
		// 디비에 ClubMember 행저장
		memberMapper.insertClubMember(clubMember);

		/*
		 * ClubMemberRoleSet clubMemberRoleSet = new ClubMemberRoleSet();
		 * clubMemberRoleSet.setClub_member_email(email);
		 * clubMemberRoleSet.setRole_set(ClubMemberRole.USER.toString()); // 디비에
		 * ClubRoleSet 행저장 memberMapper.insertClubRoleSet(clubMemberRoleSet);
		 */

		result = memberMapper.findByEmail(email, 1);
		// 추가된 정보 반환
		return result;
	}// end saveSocialMember..

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("-------loaduser-------------");
		log.info("userRequest" + userRequest);

		String clienName = userRequest.getClientRegistration().getClientName();
		// 인증 제공자 출력
		log.info("clienName" + clienName);
		log.info(userRequest.getAdditionalParameters());

		// 사용자 정보 가져오기 구글에서 허용한 API 범위
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("======oAuth2User===============");
		oAuth2User.getAttributes().forEach((k, v) -> {
			log.info(k + " : " + v);
		});// end foreach

		// 신규회원 테이블에 저장 시작
		String email = null;
		if (clienName.equals("Google")) {// 구글 인증 확인
			email = oAuth2User.getAttribute("email");
		} // end if
		log.info("구글 인증 확인");
		log.info("email : " + email);

		try {
			ClubMember2 clubMember2 = saveSocialMember(email);
			log.info("---saveSocialMember--");
			log.info(clubMember2);
			// ClubAuthMemberDTO 생성시 필요한 authorities
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + clubMember2.getMroles()));

			log.info(clubMember2.getMpassword());
			// OAuth2User 를 clubAuthMemberDTO 로 변환
			ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(clubMember2.getMid(),
					clubMember2.getMpassword(), 1, authorities, oAuth2User.getAttributes(),clubMember2.getCsno(),clubMember2.getMpoint()
					,clubMember2.getMphone(),clubMember2.getMzipcode(),clubMember2.getMaddress(),clubMember2.getMaddressdetail());
			clubAuthMemberDTO.setMname(clubMember2.getMname());
			clubAuthMemberDTO.setMpassword(clubMember2.getMpassword());
			// clubAuthMemberDTO --> UserDetails 반환
			log.info("OAuth2User 를 clubAuthMemberDTO");
			log.info(clubAuthMemberDTO);
			return clubAuthMemberDTO;

		} catch (SQLException e) {
			log.info("saveSocialMember error");
			log.info("에러 ");
			log.info(e.toString());
			return null;
		} // end try

	}// end load..

}// end class
