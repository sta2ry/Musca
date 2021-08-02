package org.featx.sta2ry.musca.mapper;

import org.apache.ibatis.annotations.*;
import org.featx.spec.model.PageRequest;
import org.featx.sta2ry.musca.criteria.FollowCriteria;
import org.featx.sta2ry.musca.entity.FollowEntity;

import java.util.List;

/**
 *
 */

@Mapper
public interface FollowMapper {

    String COLUMUS = "id, code, name, type, status, sort, image_url, description, deleted, created_at, updated_at";

    @Insert({"insert into t_follow(code, name, type, status, sort, image_url, description) ",
            "value(#{entity.code}, #{entity.name}, #{entity.type}, #{entity.status}, #{entity.sort}, ",
            "#{entity.imageUrl}, #{entity.description})"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "entity.id", before = false, resultType = Long.class)
    int insert(@Param("entity") FollowEntity domainModuleEntity);

    @Update({"update t_follow set name = #{entity.name},",
            " type = #{entity.type}, status = #{entity.status},",
            " sort = #{entity.sort}, image_url = #{entity.imageUrl},",
            " description = #{entity.description}, ",
            " where code = #{entity.code} and deleted = 0"})
    int upsert(@Param("entity") FollowEntity domainModuleEntity);

    @Update({"<script>update t_follow <set> ",
            "<if test=\"entity.name != null and entity.name != ''\"> name = #{entity.name},</if>",
            "<if test=\"entity.type != null\"> type = #{entity.type},</if>",
            "<if test=\"entity.status != null\"> status = #{entity.status},</if>",
            "<if test=\"entity.sort != null\"> sort = #{entity.sort},</if>",
            "<if test=\"entity.imageUrl != null and entity.imageUrl != ''\"> image_url = #{entity.imageUrl}, </if>",
            "<if test=\"entity.description != null and entity.description != ''\"> description = #{entity.description}, </if>",
            "</set> where code = #{entity.code} and deleted = 0",
            "</script> "})
//    @SelectKey(statement = "select updated_at from t_follow where id = (SELECT LAST_INSERT_ID())")
    int update(@Param("entity") FollowEntity domainModuleEntity);

    @Update({"update t_follow set deleted = #{deleted} where code = #{code}"})
    int delete(@Param("code") String code, @Param("deleted") Boolean delete);

    @Select({"select ", COLUMUS, " from t_follow where deleted = 0 and code = #{code} limit 1"})
    FollowEntity selectByCode(@Param("code") String code);

    @Select({"<script>select ", COLUMUS, " from t_follow where deleted = 0 and code in ",
            "<foreach collection='codes' item='code' open='(' separator=',' close=')'>#{code}</foreach>",
            "</script>"})
    List<FollowEntity> selectByCodes(@Param("codes") List<String> codes);

    @Select({"select ", COLUMUS, " from t_follow where deleted = 0 ",
            "",
            "order by id limit #{page.offset}, #{page.size}"})
    List<FollowEntity> selectByPage(@Param("query") FollowCriteria criteria, @Param("page") PageRequest page);

    @Select({"select count(1) from t_follow where deleted = 0 ",
            ""})
    long countByQuery(@Param("query") FollowCriteria criteria);
}
