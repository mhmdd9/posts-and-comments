package com.example.demo.payload;

import com.example.demo.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private List<Post> posts;
    private int pageNo;
    private int pageSize;
    private long totalElements;
}
