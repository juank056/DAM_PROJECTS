/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.List;

import com.vegadvisor.server.persistence.bo.Fomhilfo;
import com.vegadvisor.server.services.IForumServices;
import com.vegadvisor.server.services.bo.ReturnValidation;

/**
 * Servicios de foro
 * 
 * @author JuanCamilo
 *
 */
public class ForumServices implements IForumServices {

	/**
	 * 
	 */
	public ForumServices() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IForumServices#findForumThreads(java.lang
	 * .String)
	 */
	@Override
	public List<Fomhilfo> findForumThreads(String clue) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IForumServices#createForumThread(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ReturnValidation createForumThread(String userId,
			String threadTitle, String threadDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IForumServices#createForumThreadResponse
	 * (java.lang.String, int, java.lang.String)
	 */
	@Override
	public ReturnValidation createForumThreadResponse(String userId,
			int threadId, String responseDetail) {
		// TODO Auto-generated method stub
		return null;
	}

}
