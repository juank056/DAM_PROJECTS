/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.List;

import com.vegadvisor.server.persistence.bo.Chdmensa;
import com.vegadvisor.server.persistence.bo.Usmusuar;
import com.vegadvisor.server.services.IChatServices;
import com.vegadvisor.server.services.bo.ReturnValidation;

/**
 * Clase que define los servicios de Chat
 * 
 * @author JuanCamilo
 *
 */
public class ChatServices implements IChatServices {

	/**
	 * 
	 */
	public ChatServices() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IChatServices#findChatUsers(java.lang.
	 * String)
	 */
	@Override
	public List<Usmusuar> findChatUsers(String clue) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IChatServices#registerChatMessage(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ReturnValidation registerChatMessage(String userIdFrom,
			String userIdTo, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IChatServices#recolectChatMessages(java
	 * .lang.String)
	 */
	@Override
	public List<Chdmensa> recolectChatMessages(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
