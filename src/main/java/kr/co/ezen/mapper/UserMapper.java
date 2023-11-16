package kr.co.ezen.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.ezen.beans.User;

public interface UserMapper {
	//아이디를 기준으로 이름 추출
	@Select("select user_name from user_table where user_id=#{user_id}")
	String existId(String user_id);
	
	@Insert("insert into user_table (user_idx, user_name, user_id, user_pw) values (user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	void addUser(User joinBean);
	//gildont 1234
	//아이디랑 비번 기준으로 user_idx, user_name추출
	@Select("select user_idx, user_name from user_table "
			+ "where user_id=#{user_id} and user_pw=#{user_pw}")
	User getLoginUser(User loginProBean);
	
	
	//user_idx 기준으로 user_id, user_name추출
	@Select("select user_id, user_name "
			+ "from user_table "
			+ "where user_idx=#{user_idx}")
	User getModifyUser(int user_idx);
	
	
	@Update("update user_table "
			+ "set user_pw=#{user_pw} "
			+ "where user_idx=#{user_idx}")
	void modifyUser(User modifyBean);
	

}
