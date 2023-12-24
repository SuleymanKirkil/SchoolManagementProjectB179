package school_management.pojos.DeanControllerPojo;

import java.io.Serializable;

public class DeanPojo implements Serializable {
	private String httpStatus;
	private String message;
	private ObjectPojo object;

	public DeanPojo() {
	}

	public DeanPojo(String httpStatus, String message, ObjectPojo object) {
		this.httpStatus = httpStatus;
		this.message = message;
		this.object = object;
	}

	public void setHttpStatus(String httpStatus){
		this.httpStatus = httpStatus;
	}

	public String getHttpStatus(){
		return httpStatus;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setObject(ObjectPojo object){
		this.object = object;
	}

	public ObjectPojo getObject(){
		return object;
	}

	@Override
 	public String toString(){
		return 
			"DeanPojo{" + 
			"httpStatus = '" + httpStatus + '\'' + 
			",message = '" + message + '\'' + 
			",object = '" + object + '\'' + 
			"}";
		}
}