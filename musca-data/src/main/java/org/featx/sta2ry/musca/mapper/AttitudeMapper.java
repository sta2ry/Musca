package org.featx.sta2ry.musca.mapper;

import org.apache.ibatis.annotations.*;
import org.featx.spec.model.PageRequest;
import org.featx.sta2ry.musca.criteria.AttitudeCriteria;
import org.featx.sta2ry.musca.entity.AttitudeEntity;

import java.util.List;

/**
 * Domain Module generated by coder as meta data below: Coder-Meta:
 * {
 *   "name": "Storage-Mapper",
 *    "data": {
 *       "org/featx/template/app": "com/juext/haldus/user", 
 *       "org.featx.template.app": "com.juext.haldus.user", 
 *       "DomainModule" : "${domain.name|camel}",
 *       "domainModule" : "${domain.name|camel}",
 *       "domain_module": "${domain.name|snake}"},
 *   "description": ""
 * }
 * @author Coder
 * @since 2020/4/12 13:49
 */

@Mapper
public interface AttitudeMapper {

    String COLUMUS = "id, code, name, type, status, sort, image_url, description, deleted, created_at, updated_at";

    @Insert({"insert into t_attitude(code, name, type, status, sort, image_url, description) ",
            "value(#{entity.code}, #{entity.name}, #{entity.type}, #{entity.status}, #{entity.sort}, ",
            "#{entity.imageUrl}, #{entity.description})"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "entity.id", before = false, resultType = Long.class)
    int insert(@Param("entity") AttitudeEntity domainModuleEntity);

    @Update({"update t_attitude set name = #{entity.name},",
            " type = #{entity.type}, status = #{entity.status},",
            " sort = #{entity.sort}, image_url = #{entity.imageUrl},",
            " description = #{entity.description}, ",
            " where code = #{entity.code} and deleted = 0"})
    int upsert(@Param("entity") AttitudeEntity domainModuleEntity);

    @Update({"<script>update t_attitude <set> ",
            "<if test=\"entity.name != null and entity.name != ''\"> name = #{entity.name},</if>",
            "<if test=\"entity.type != null\"> type = #{entity.type},</if>",
            "<if test=\"entity.status != null\"> status = #{entity.status},</if>",
            "<if test=\"entity.sort != null\"> sort = #{entity.sort},</if>",
            "<if test=\"entity.imageUrl != null and entity.imageUrl != ''\"> image_url = #{entity.imageUrl}, </if>",
            "<if test=\"entity.description != null and entity.description != ''\"> description = #{entity.description}, </if>",
            "</set> where code = #{entity.code} and deleted = 0",
            "</script> "})
//    @SelectKey(statement = "select updated_at from t_attitude where id = (SELECT LAST_INSERT_ID())")
    int update(@Param("entity") AttitudeEntity domainModuleEntity);

    @Update({"update t_attitude set deleted = #{deleted} where code = #{code}"})
    int delete(@Param("code") String code, @Param("deleted") Boolean delete);

    @Select({"select ", COLUMUS, " from t_attitude where deleted = 0 and code = #{code} limit 1"})
    AttitudeEntity selectByCode(@Param("code") String code);

    @Select({"<script>select ", COLUMUS, " from t_attitude where deleted = 0 and code in ",
            "<foreach collection='codes' item='code' open='(' separator=',' close=')'>#{code}</foreach>",
            "</script>"})
    List<AttitudeEntity> selectByCodes(@Param("codes") List<String> codes);

    @Select({"select ", COLUMUS, " from t_attitude where deleted = 0 ",
            "",
            "order by id limit #{page.offset}, #{page.size}"})
    List<AttitudeEntity> selectByPage(@Param("query") AttitudeCriteria criteria, @Param("page") PageRequest page);

    @Select({"select count(1) from t_attitude where deleted = 0 ",
            ""})
    long countByQuery(@Param("query") AttitudeCriteria criteria);
}
