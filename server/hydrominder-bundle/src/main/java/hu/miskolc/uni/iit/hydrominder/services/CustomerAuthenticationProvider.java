package hu.miskolc.uni.iit.hydrominder.services;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;

public interface CustomerAuthenticationProvider {

	CustomerAuthenticationCredentials showUsers();
}
