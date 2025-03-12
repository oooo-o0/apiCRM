package com.example.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.example.app.domain.Member;

@Mapper
public interface MemberMapper {
    @Select("SELECT * FROM members")
    List<Member> selectAll() throws Exception;

    @Select("SELECT * FROM members WHERE id = #{id}")
    Member selectById(@Param("id") Integer id) throws Exception;

    @Select("SELECT * FROM members WHERE id = LAST_INSERT_ID()")
    Member selectByLastInsertId() throws Exception;

    @Insert("INSERT INTO members (name, age, address, type_id, created) "
            + " VALUES (#{name}, #{age}, #{address}, #{typeId}, NOW()) ")
    void insert(Member member) throws Exception;

    @Update("UPDATE members SET name = #{name}, age = #{age}, "
            + " address = #{address}, type_id = #{typeId} "
            + " WHERE id = #{id} ")
    void update(Member member) throws Exception;

    @Delete("DELETE FROM members WHERE id = #{id}")
    void delete(@Param("id") Integer id) throws Exception;
}
