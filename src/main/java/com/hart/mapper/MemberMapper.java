package com.hart.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.member.ClubMember;
import com.hart.domain.member.ClubMember2;
import com.hart.domain.member.ClubMemberRoleSet;
import com.hart.domain.order.OinfoDTO;

@Mapper
public interface MemberMapper {

	public void insertClubMember(ClubMember clubMember) throws SQLException;

	public void insertClubRoleSet(ClubMemberRoleSet clubMemberRoleSet) throws SQLException;

	public ClubMember2 findByEmail(@Param("mid") String mid, @Param("social") int social) throws SQLException;
	
	public void updateAddress(OinfoDTO oDTO)throws SQLException;
}
