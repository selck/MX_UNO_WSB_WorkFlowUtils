package mx.com.amx.unotv.workflow.utils.bo;


import mx.com.amx.unotv.workflow.utils.dto.VideoOoyalaDTO;
import mx.com.amx.unotv.workflow.utils.exception.GetInfoVideoBOException;

public interface IGetInfoVideoBO {
	/**
	 * Metodo que obtiene la informacion del video
	 * @param asset_id,  content id de ooyala
	 * @return InfoVideoDTO, DTO con la infromacion que necesitamos del video
	 * @exception InfoVideoBOException
	 * @author Fernando Aviles
	 * */
	public VideoOoyalaDTO getInfoVideo(String asset_id) throws GetInfoVideoBOException;
}
