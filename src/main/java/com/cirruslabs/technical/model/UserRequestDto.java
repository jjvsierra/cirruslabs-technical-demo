package com.cirruslabs.technical.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.cirruslabs.technical.utils.Constants.IP_ADDRESS_EMPTY_ERROR_MESSAGE;
import static com.cirruslabs.technical.utils.Constants.PASSWORD_EMPTY_ERROR_MESSAGE;
import static com.cirruslabs.technical.utils.Constants.PASSWORD_PATTERN;
import static com.cirruslabs.technical.utils.Constants.PASSWORD_PATTERN_ERROR_MESSAGE;
import static com.cirruslabs.technical.utils.Constants.USERNAME_EMPTY_ERROR_MESSAGE;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @JsonProperty("username")
    @NotBlank(message = USERNAME_EMPTY_ERROR_MESSAGE)
    @Schema(description = USERNAME_EMPTY_ERROR_MESSAGE)
    private String userName;
    @NotBlank(message = PASSWORD_EMPTY_ERROR_MESSAGE)
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_PATTERN_ERROR_MESSAGE)
    @Schema(description = PASSWORD_PATTERN_ERROR_MESSAGE)
    private String password;
    @JsonProperty("ip_address")
    @NotBlank(message = IP_ADDRESS_EMPTY_ERROR_MESSAGE)
    @Schema(description = IP_ADDRESS_EMPTY_ERROR_MESSAGE)
    private String ipAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
