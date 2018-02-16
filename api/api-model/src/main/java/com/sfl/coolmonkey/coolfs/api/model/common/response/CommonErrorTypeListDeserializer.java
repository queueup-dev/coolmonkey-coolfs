package com.sfl.coolmonkey.coolfs.api.model.common.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sfl.coolmonkey.coolfs.api.model.common.CommonErrorType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 12/3/15
 * Time: 12:15 PM
 */
public class CommonErrorTypeListDeserializer extends JsonDeserializer<List<CommonErrorType>> {
    @Override
    public List<CommonErrorType> deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
        final List<CommonErrorType> result = new ArrayList<>();
        for (JsonToken token = p.nextToken(); token != JsonToken.END_OBJECT; token = p.nextToken()) {
            switch (token) {
                case VALUE_STRING:
                    result.add(CommonErrorType.valueOf(p.getValueAsString()));
                    break;
                default:
                    break;
            }
        }
        return result;
    }
}
