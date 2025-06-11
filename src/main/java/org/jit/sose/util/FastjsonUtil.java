package org.jit.sose.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class FastjsonUtil {

	/**
	 * 获取json对象中的int类型的值<br>
	 * 若值为空返回null
	 * 
	 * @param jsonObject
	 * @param key
	 * @return 获取的int值
	 */
	public static Integer getIntValue(JSONObject jsonObject, String key) {
		return StringUtil.isEmpty(jsonObject.getString(key)) ? null : jsonObject.getIntValue(key);
	}

	/**
	 * 将 fastjson的JSONArray转化为泛型列表
	 * 
	 * @param jsonArray 源数据
	 * @param clz       泛型类
	 * @param <T>       泛型
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> convertJSONArrayToTypeList(JSONArray jsonArray, Class<T> clz) {
		if (CollectionUtils.isEmpty(jsonArray)) {
			return new ArrayList<T>();
		}
		List<T> result = new ArrayList<T>(jsonArray.size());
		jsonArray.forEach(element -> {
			// 基础类型不可以转化为JSONObject，需要特殊处理
			if (element instanceof String || element instanceof Number || element instanceof Boolean) {
				result.add((T) element);
			} else {
				T t = JSONObject.toJavaObject((JSONObject) element, clz);
				result.add(t);
			}
		});
		return result;
	}

}
