package br.com.rmsystems.customsession.dto;

import lombok.*;

import java.io.Serializable;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomSession implements Serializable {

    private String sessaoId;
    private String login;
    private Long userId;
    private Long profileId;
}
