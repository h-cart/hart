package com.hart.domain.member;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
 
@Data
@Setter
@Getter
public class ClubMemberRoleSet implements Serializable{
    private String club_member_email ;
    private String role_set ;   
}//end class
