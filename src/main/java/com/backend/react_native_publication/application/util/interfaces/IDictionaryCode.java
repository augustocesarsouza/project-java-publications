package com.backend.react_native_publication.application.util.interfaces;

public interface IDictionaryCode {
    Integer getKeyDictionary(String key);

    Integer removeKeyDictionary(String key);

    Integer putKeyValueDictionary(String key, Integer value);
}
