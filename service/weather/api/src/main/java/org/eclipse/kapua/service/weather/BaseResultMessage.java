package org.eclipse.kapua.service.weather;



/**
 * 回复基类
 * @author L
 *
 */
public abstract class BaseResultMessage {
	private int errcode;
	
	protected abstract String BuildSpecialResult();
	
	public String BuildResult(){
		String result = "";
		if(errcode!=0){
			result = ErrorMessageHelper.builtErrorMsg(errcode);
		}else{
			result = BuildSpecialResult();
		}
		return result;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
}
