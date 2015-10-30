/**
 * 
 */
package com.vegadvisor.server.services;

import java.util.List;

import com.vegadvisor.server.persistence.bo.Chdmensa;
import com.vegadvisor.server.persistence.bo.Usmusuar;

/**
 * @author JuanCamilo
 *
 */
public interface IChatServices {

	/**
	 * Obtiene los usuarios activos de chat segun pista de su id
	 * 
	 * @param clue
	 *            Pista para encontrar usuario
	 * @return Lista de usuarios que estan en el chat
	 */
	public List<Usmusuar> findChatUsers(String clue);

	/**
	 * Método para registrar un mensaje de chat dentro del sistema
	 * 
	 * @param userIdFrom
	 *            Usuario que envia el mensaje
	 * @param userIdTo
	 *            Usuario que recibirá el mensaje
	 * @param content
	 *            Contenido del mensaje
	 * @return Indicador de registro de mensaje ok
	 */
	public String[] registerChatMessage(String userIdFrom, String userIdTo,
			String content);

	/**
	 * Método para recolectar los mensajes de chat pendientes
	 * 
	 * @param userId
	 *            Id del usuario que va a recolectar sus mensajes
	 * @return Lista de mensajes de chat pendientes por recoger
	 */
	public List<Chdmensa> recolectChatMessages(String userId);

}
