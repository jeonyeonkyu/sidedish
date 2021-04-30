package com.codesquad.team14.dto.requestDTO;

import java.util.List;

public class RequestBestItemDTO {
    private Long category_id;
    private String name;
    private List<RequestItemDTO> items;

    public Long getCategory_id() {
        return category_id;
    }

    public String getName() {
        return name;
    }

    public List<RequestItemDTO> getItems() {
        return items;
    }
}
