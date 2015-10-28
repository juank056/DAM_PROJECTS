/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.Date;

import com.vegadvisor.server.services.IUserServices;

/**
 * @author JuanCamilo
 *
 */
public class UserServices implements IUserServices {

	/**
	 * 
	 */
	public UserServices() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.vegadvisor.server.services.IUserServices#validateUser(java.lang.String, java.lang.String)
	 */
	@Override
	public String[] validateUser(String userId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vegadvisor.server.services.IUserServices#createUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String[] createUser(String userId, String userName,
			String userLastName, String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vegadvisor.server.services.IUserServices#updateUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String[] updateUser(String userId, String userName,
			String userLastName, String email, String password,
			Date dateOfBirth, String countryCode, String cityCode,
			String isVegan, String hobbies) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vegadvisor.server.services.IUserServices#checkInUser(java.lang.String, java.lang.String)
	 */
	@Override
	public String[] checkInUser(String userId, String establishmentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
