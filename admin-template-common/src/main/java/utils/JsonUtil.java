package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.util.Assert;
import lombok.extern.slf4j.Slf4j;

/**
 * JSON字符串处理工具
 *
 * @author luffy
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
      * 将对象转成JSON字符串
      *
      * @author luffy
      * @date 2020/8/26 14:52
      * @param object
      * @return java.lang.String
      */
    public static String getJson(Object object) {
        Assert.checkNonNull(object);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("json convert is error");
        }
        return null;
    }
}
