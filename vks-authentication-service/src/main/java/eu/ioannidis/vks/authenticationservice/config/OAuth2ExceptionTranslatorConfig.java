package eu.ioannidis.vks.authenticationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

@Configuration
public class OAuth2ExceptionTranslatorConfig {

    @Bean
    public WebResponseExceptionTranslator oauth2ResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {

            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                OAuth2Exception body = responseEntity.getBody();
                HttpStatus statusCode = responseEntity.getStatusCode();

//                body.addAdditionalInformation("timestamp", dateTimeFormat.format(clock.instant()));
//                body.addAdditionalInformation("status", body.getHttpErrorCode().toString());
                body.addAdditionalInformation("timestamp", "timestamp");
                body.addAdditionalInformation("status", "status");
                body.addAdditionalInformation("message", body.getMessage());
                body.addAdditionalInformation("code", body.getOAuth2ErrorCode().toUpperCase());

                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                // do something with header or response
                return new ResponseEntity<>(body, headers, statusCode);
            }
        };
    }

}
