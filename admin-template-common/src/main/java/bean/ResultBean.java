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

    private int resultCode;
    private String resultMsg;
    private T data;

    public ResultBean() {

    }

    public ResultBean(ResultCode resultCode) {
        this.resultCode = resultCode.getCode();
        this.resultMsg = resultCode.getMsg();
    }
}
