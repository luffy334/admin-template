package enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 前后端交互状态码
 *
 * @author luffy
 */

public enum ResultCode {

    /**
     * 请求正常
     */
    SUCCESS(10000, "Success"),
    ACCOUNT_DISABLE(10100, "Account Is Disable"),
    ACCOUNT_LOCK(10101, "Account Is Lock"),
    ACCOUNT_UNREGISTERED(10102, "Account Is Unregistered"),
    ACCOUNT_PASSWORD_FAIL(10103, "Incorrect Account Or Password"),
    ACCOUNT_UNKNOWN_FAIL(10103, "Unknown Fail For Account"),
    ACCOUNT_REGISTERED(10104, "Account Is Registered"),
    ACCOUNT_SUPPER(10105, "Can Not Delete Supper Account"),
    LOGIN_CREDENTIALS_FAIL(10106, "The Login Credentials Is Fail");


    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(int code) {
        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.getCode() == code) {
                return resultCode.msg;
            }
        }
        return null;
    }
}
