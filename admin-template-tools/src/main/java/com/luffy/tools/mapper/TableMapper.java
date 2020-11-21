package com.luffy.tools.mapper;

import com.luffy.tools.model.Table;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author luffy
 */
public interface TableMapper {

    @Select("SELECT (@i := @i+1) `index`, s.table_name tableName FROM information_schema.TABLES s, (SELECT @i:=0) t WHERE table_schema = 'admin-template'")
    List<Table> queryAllTableName();
}
