package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.request.UserDto;

public interface KeyCloakUserHandleService {

    void clientUser(UserDto userDto);

    void providerUser(UserDto userDto);

}
