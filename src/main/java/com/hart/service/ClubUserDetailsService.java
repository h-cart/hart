package com.hart.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.member.ClubMember2;
import com.hart.mapper.MemberMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ClubUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper clubMemberRepository;
	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 입력한 이메일로 ClubMember 찾음
		ClubMember2 result = null;
		try {
			result = clubMemberRepository.findByEmail(username, 0);
		} catch (SQLException e) {
			throw new UsernameNotFoundException("Check Email or Social!!");
		} // end try
		if (result == null) {
			try {
				result = clubMemberRepository.findByEmail(username, 1);
			} catch (SQLException e1) {
				throw new UsernameNotFoundException("Check Email or Social!!");
			} // end try

		} // end if
			// clubMember 생성

		ClubMember2 clubMember2 = result;

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + clubMember2.getMroles()));

		// clubMember --> ClubAuthMemberDTO 변환
//		ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(clubMember2.getMid(), clubMember2.getMpassword(),
//				clubMember2.getSocial(), authorities);
		ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(clubMember2.getMid(),
				clubMember2.getMpassword(), 1, authorities,clubMember2.getCsno(),clubMember2.getMpoint()
				,clubMember2.getMphone(),clubMember2.getMzipcode(),clubMember2.getMaddress(),clubMember2.getMaddressdetail());
		// ClubAuthMemberDTO 값 세팅
		clubAuthMemberDTO.setMname(clubMember2.getMname());
		clubAuthMemberDTO.setSocial(clubMember2.getSocial());

		log.info(clubAuthMemberDTO);
		log.info(clubAuthMemberDTO.getAuthorities().toString());

		// ClubAuthMemberDTO는 UserDetails 타입으로 처리됨
		return clubAuthMemberDTO;
	}// end load..

}// end Cla...
