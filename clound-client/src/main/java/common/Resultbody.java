package common;

/**
 * @author fly
 * @create 2024-05-30-16:01
 **/
public class Resultbody<T> {
    private String code;
    private String msg;
    private T data;


    private Resultbody(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Resultbody<T> create(String code, String msg, T data) {
        return new Resultbody<T>(code, msg, data);
    }

    public static <T> Resultbody<T> create(String code, String msg) {
        return new Resultbody<T>(code, msg, null);
    }

    public static <T> Resultbody<T> success() {
        return new Resultbody<T>("200", "success", null);
    }

    public static <T> Resultbody<T> success(T data) {
        return new Resultbody<T>("200", "success", data);
    }

    public static <T> Resultbody<T> success(String msg, T data) {
        return new Resultbody<T>("200", msg, data);
    }

    public static <T> Resultbody<T> failed(String msg) {
        return new Resultbody<T>("500", msg, null);
    }

    public static <T> Resultbody<T> failed(String msg, T data) {
        return new Resultbody<T>("500", msg, data);
    }

    public String getCode() {return code;}

    public Resultbody<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {return msg;}

    public Resultbody<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {return data;}

    public Resultbody<T> setData(T data) {
        this.data = data;
        return this;
    }
}
