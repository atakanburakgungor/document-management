package com.example.documentmanagement.components;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
public class JsonMergePatcher {
    private final ObjectMapper mapper;

    @Autowired
    public JsonMergePatcher(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public <T> T mergePatch(String json, @NotNull @Valid T target) {
        JsonNode patchedNode = null;
        try {
            final JsonMergePatch patch = mapper.readValue(json, JsonMergePatch.class);
            patchedNode = patch.apply(mapper.convertValue(target, JsonNode.class));
        } catch (IOException | JsonPatchException e) {
            throw new RuntimeException(e);
        }
        return (T) mapper.convertValue(patchedNode, target.getClass());
    }
}
