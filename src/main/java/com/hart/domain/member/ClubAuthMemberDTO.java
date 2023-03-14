package com.hart.domain.member;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User implements OAuth2User {

	private static final long serialVersionUID = 1L;
	private String mid;
	private String mname;
	private String mpassword;
	private int social;
	private Map<String, Object> OA2_attr;

	// ClubOAuth2UserDetailsService 용 구성자
	public ClubAuthMemberDTO(String username, String password, int fromSocial, List<GrantedAuthority> authorities,
			Map<String, Object> OA2_attr) {
		this(username, password, fromSocial, authorities);
		this.OA2_attr = OA2_attr;
	}// end ClubAuthMemberDTO

	// 구성자 설정
	public ClubAuthMemberDTO(String username, String password, int fromSocial, List<GrantedAuthority> authorities) {
		// password는 부모클래스 사용
		super(username, password, authorities);
		this.mid = username;
		this.social = fromSocial;
	}// end ClubAuthMemberDTO

	// OAuth2User 정보 저장
	@Override
	public Map<String, Object> getAttributes() {
		return OA2_attr;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}// end class
