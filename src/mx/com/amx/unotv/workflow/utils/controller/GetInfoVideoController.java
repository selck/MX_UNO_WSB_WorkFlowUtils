package mx.com.amx.unotv.workflow.utils.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.amx.unotv.workflow.utils.bo.IGetInfoVideoBO;
import mx.com.amx.unotv.workflow.utils.dto.VideoOoyalaDTO;

@Component
@RequestMapping("getInfoController")
public class GetInfoVideoController {
	
	protected static final Logger log=Logger.getLogger(GetInfoVideoController.class);
	private IGetInfoVideoBO getInfoVideoBO;
	
	@RequestMapping(value={"getInfoVideo"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	public VideoOoyalaDTO getInfoVideo( @RequestBody String id_content, HttpServletResponse response){
		VideoOoyalaDTO respuesta = new VideoOoyalaDTO();
		log.info("getInfoVideo");
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		try{
			
			respuesta = getInfoVideoBO.getInfoVideo(id_content);
		} catch (Exception e){
			log.error(" Error getInfoController [getInfoVideo]:", e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return respuesta;
	}

	/**
	 * @return the getInfoVideoBO
	 */
	public IGetInfoVideoBO getGetInfoVideoBO() {
		return getInfoVideoBO;
	}

	/**
	 * @param getInfoVideoBO the getInfoVideoBO to set
	 */
	@Autowired
	public void setGetInfoVideoBO(IGetInfoVideoBO getInfoVideoBO) {
		this.getInfoVideoBO = getInfoVideoBO;
	}
	
	
}
