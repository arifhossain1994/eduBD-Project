package com.sql.dao;

import java.util.Map;

public interface BaseRowReverseMapper<T> {

    Map<String, Object> mapObject(T object);

}
