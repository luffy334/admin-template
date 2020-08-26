package bean;

import enums.ResultCode;
import lombok.Data;

/**
 *  前后端交互实体类
 *
 * @author luffy
 */
@Data
public class ResultBean<T> {

    private ResultCode resultCode;
    private T data;

    public ResultBean(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
