package mx.com.amx.unotv.workflow.utils.dto;

import java.io.Serializable;

public class ParametrosDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ambiente;
	private String api_key;
	private String secretKey;
	private String expires;
	private String content_id;
	private String signature;
	private String pathRequest;
	
	private String method_assets;
	private String method_assets_source_file_info;
	
	
	/**
	 * @return the ambiente
	 */
	public String getAmbiente() {
		return ambiente;
	}
	/**
	 * @param ambiente the ambiente to set
	 */
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
	/**
	 * @return the api_key
	 */
	public String getApi_key() {
		return api_key;
	}
	/**
	 * @param api_key the api_key to set
	 */
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	/**
	 * @return the expires
	 */
	public String getExpires() {
		return expires;
	}
	/**
	 * @param expires the expires to set
	 */
	public void setExpires(String expires) {
		this.expires = expires;
	}
	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}
	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	/**
	 * @return the content_id
	 */
	public String getContent_id() {
		return content_id;
	}
	/**
	 * @param content_id the content_id to set
	 */
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}
	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	/**
	 * @return the pathRequest
	 */
	public String getPathRequest() {
		return pathRequest;
	}
	/**
	 * @param pathRequest the pathRequest to set
	 */
	public void setPathRequest(String pathRequest) {
		this.pathRequest = pathRequest;
	}
	/**
	 * @return the method_assets
	 */
	public String getMethod_assets() {
		return method_assets;
	}
	/**
	 * @param method_assets the method_assets to set
	 */
	public void setMethod_assets(String method_assets) {
		this.method_assets = method_assets;
	}
	/**
	 * @return the method_assets_source_file_info
	 */
	public String getMethod_assets_source_file_info() {
		return method_assets_source_file_info;
	}
	/**
	 * @param method_assets_source_file_info the method_assets_source_file_info to set
	 */
	public void setMethod_assets_source_file_info(String method_assets_source_file_info) {
		this.method_assets_source_file_info = method_assets_source_file_info;
	}
	
		
}
