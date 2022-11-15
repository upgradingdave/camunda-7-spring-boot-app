package org.camunda.bpm;

import org.camunda.bpm.identity.impl.ldap.plugin.LdapIdentityProviderPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LdapCustomConfiguration {

  @Bean
  public LdapIdentityProviderPlugin ldapIdentityProviderPlugin() {
    LdapIdentityProviderPlugin ldapIdentityProviderPlugin = new LdapIdentityProviderPlugin();
    ldapIdentityProviderPlugin.setServerUrl("ldap://localhost:1389");
    ldapIdentityProviderPlugin.setManagerDn("cn=admin,dc=example,dc=org");
    ldapIdentityProviderPlugin.setManagerPassword("adminpassword");
    ldapIdentityProviderPlugin.setBaseDn("dc=example,dc=org");
    return ldapIdentityProviderPlugin;
  }

}
