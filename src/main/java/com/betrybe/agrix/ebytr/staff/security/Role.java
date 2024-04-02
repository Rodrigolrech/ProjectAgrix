package com.betrybe.agrix.ebytr.staff.security;

/**
 * Enum representing a Role.
 */
public enum Role {
  ADMIN("ROLE_ADMIN"),
  MANAGER("ROLE_MANAGER"),
  USER("ROLE_USER");
  private final String name;

  Role(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static final String ROLE_ADMIN = "ROLE_ADMIN";
  public static final String ROLE_MANAGER = "ROLE_MANAGER";
  public static final String ROLE_USER = "ROLE_USER";
}