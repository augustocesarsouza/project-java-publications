package com.backend.react_native_publication.data.utilityExternal.Interface;

public interface ISendSmsTwilio {
    Boolean SendSms(String toPhoneNumber, String messageContent);
}
