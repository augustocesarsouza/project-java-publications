package com.backend.react_native_publication.data.utilityExternal.Interface;

import com.backend.react_native_publication.domain.InfoErrors.InfoErrors;
import com.backend.react_native_publication.domain.entities.User;

public interface ISendEmailBrevo {
    InfoErrors<String> sendEmail(User user, String url);
    InfoErrors<String> sendCode(User user, int codeRandom);
}
