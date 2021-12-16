package uofg.zehuilyu.queueapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *   Interface returns data format 接口返回数据格式
 */
@Data
@ApiModel(value="Interface return object", description="Interface return object")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * success flag
	 */
	@ApiModelProperty(value = "success flag")
	private boolean success = true;

	/**
	 * Return processing message 返回处理消息
	 */
	@ApiModelProperty(value = "Return processing message")
	private String message = "success！";

	/**
	 * return code 返回代码
	 */
	@ApiModelProperty(value = "return code")
	private Integer code = 0;
	
	/**
	 * Return data object data
	 */
	@ApiModelProperty(value = "Return data object")
	private T result;
	
	/**
	 * timestamp
	 */
	@ApiModelProperty(value = "timestamp")
	private long timestamp = System.currentTimeMillis();

	public Result() {
		
	}
	
	public Result<T> success(String message) {
		this.message = message;
		this.code = 200;
		this.success = true;
		return this;
	}

	@Deprecated
	public static Result<Object> ok() {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(200);
		r.setMessage("success");
		return r;
	}

	@Deprecated
	public static Result<Object> ok(String msg) {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(200);
		r.setMessage(msg);
		return r;
	}

	@Deprecated
	public static Result<Object> ok(Object data) {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(200);
		r.setResult(data);
		return r;
	}

	public static<T> Result<T> OK() {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setMessage("success");
		return r;
	}

	public static<T> Result<T> OK(T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setResult(data);
		return r;
	}

	public static<T> Result<T> OK(String msg, T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setMessage(msg);
		r.setResult(data);
		return r;
	}
	
	public static Result<Object> error(String msg) {
		return error(500, msg);
	}
	
	public static Result<Object> error(int code, String msg) {
		Result<Object> r = new Result<Object>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}

	public Result<T> error500(String message) {
		this.message = message;
		this.code = 500;
		this.success = false;
		return this;
	}
	/**
	 * Result returned without permission
	 */
	public static Result<Object> noauth(String msg) {
		return error(510, msg);
	}

	@JsonIgnore
	private String onlTable;

}