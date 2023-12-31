package kr.co.ezen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.co.ezen.beans.Content;

public interface BoardMapper {
	
	@Insert("insert into content_table(content_idx,content_subject,content_text,"
			+ "content_writer_idx,content_board_idx,content_date)"
			+ "values(content_seq.nextval, #{content_subject}, #{content_text},"
			+ "#{content_writer_idx},#{content_board_idx},sysdate)")
	void addContent(Content writeBean);
	
	@Select("select board_info_name from board_info_table where board_info_idx=#{board_info_idx}")
	String getBoardName(int board_info_idx);
	
	//content_table(a1), user_table(a2) 조인
	@Select("select a1.content_idx, a1.content_subject, a2.user_name as content_writer_name, " + 
			"       to_char(a1.content_date, 'YYYY-MM-DD') as content_date " + 
			"from content_table a1, user_table a2 " + 
			"where a1.content_writer_idx = a2.user_idx " + 
			"      and a1.content_board_idx = #{board_info_idx} " + 
			"order by a1.content_idx desc")
	List<Content> getContent(int board_info_idx);
	
	//content_table(a1), user_table(a2) 조인
	@Select("select a2.user_name as content_writer_name, " + 
			"       to_char(a1.content_date, 'YYYY-MM-DD') as content_date, " + 
			"       a1.content_subject, a1.content_text, a1.content_writer_idx " + 
			"from content_table a1, user_table a2 " + 
			"where a1.content_writer_idx = a2.user_idx " + 
			"      and content_idx = #{content_idx}")
	Content getInfo(int content_idx);
	
	

}
