package com.customers.backend.app.services;

import com.customers.backend.app.contracts.services.PartialContent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;

@Service
public class PartialContentImpl implements PartialContent {
    Gson gson = new Gson();




    @Override
    public String Partial(Object content, String fields) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        if (fields == null || fields == "")
            return mapper.writeValueAsString(content);

        JsonElement jsonElement = gson.toJsonTree(content);

        String[] fields_found = fields.trim().split(",");

        final JsonObject jsonObject = new JsonObject();

        fields = "metadata[],results";
        for (String field_found : fields_found) {

            if (field_found.contains("[]"))
                field_found = field_found.replace("[]", "");

                //jsonObject.add(field_found, "value");

        }
        //metadata[total],results[][id,name]



        return jsonObject.getAsString();
    }
}
