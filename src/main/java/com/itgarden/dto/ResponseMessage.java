package com.itgarden.dto;

import lombok.*;

/*
  Created by Suresh Stalin on 16 / October / 2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class ResponseMessage<T> {

    @NonNull
    private T responseClassType; // User,emp info,billing info or order info cusomer info

    private String message;

    private String messageType;

    public static <T> ResponseMessage<T> withResponseData(T classType, String message,String messageType) {
        return new ResponseMessage<T>(classType, message, messageType);
    }

    public static <T> ResponseMessage<T> withResponseData(T classType) {
        return new ResponseMessage<T>(classType);
    }

    public static ResponseMessage<Void> empty() {
        return new ResponseMessage<>();
    }


}
