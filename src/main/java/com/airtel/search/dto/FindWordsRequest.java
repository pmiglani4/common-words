package com.airtel.search.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FindWordsRequest {
    
    @JsonProperty("files")
    List<String> files;

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

}
