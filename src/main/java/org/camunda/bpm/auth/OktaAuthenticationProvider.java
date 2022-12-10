package org.camunda.bpm.auth;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.impl.ContainerBasedAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class OktaAuthenticationProvider extends ContainerBasedAuthenticationProvider {

  private final Logger logger = LoggerFactory.getLogger(OktaAuthenticationProvider.class);

  @Override
  public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
      return AuthenticationResult.unsuccessful();
    }

    String name = ((OidcUser) authentication.getPrincipal()).getName();

    if (name == null || name.isEmpty()) {
      return AuthenticationResult.unsuccessful();
    }

    String emailId = ((OidcUser) authentication.getPrincipal()).getEmail();
    AuthenticationResult authenticationResult;

    authenticationResult = new AuthenticationResult(emailId, true);
    authenticationResult.setGroups(getUserGroups(authentication));

    return authenticationResult;

  }

  private List<String> getUserGroups(Authentication authentication) {

    List<String> groupIds;
    groupIds = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());
    return groupIds;

  }

}
