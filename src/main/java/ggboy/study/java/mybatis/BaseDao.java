package ggboy.study.java.mybatis;

import java.util.List;
import java.util.Map;

import ggboy.java.common.utils.sql.BaseSqlBuilder;

public interface BaseDao {
	public int insert(BaseSqlBuilder builder);

	public int delete(BaseSqlBuilder builder);

	public List<Map<String, Object>> select(BaseSqlBuilder builder);

	public int update(BaseSqlBuilder builder);
}
