/**
 * 
 */
package com.qigan.qiganshop.exception;

/**
 * @author wyy
 *
 */
public class XltcRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XltcRuntimeException(String message) {
		super(message);
	}

	public XltcRuntimeException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public static XltcRuntimeException throwable(String message) {
		return new XltcRuntimeException(message);
	}

	public static XltcRuntimeException throwable(String message, Throwable throwable) {
		return new XltcRuntimeException(message, throwable);
	}

}
