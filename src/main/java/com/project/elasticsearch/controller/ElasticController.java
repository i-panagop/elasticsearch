package com.project.elasticsearch.controller;

import static com.project.elasticsearch.utils.Constants.EMAIL_ANALYZED;
import static com.project.elasticsearch.utils.Constants.TITLE_ANALYZED;

import com.project.elasticsearch.client.ElasticSearchClient;
import com.project.elasticsearch.dto.ListResponseDto;
import com.project.elasticsearch.dto.ResponseDto;
import com.project.elasticsearch.dto.elastic.UserElasticDto;
import com.project.elasticsearch.model.RoleEnum;
import com.project.elasticsearch.service.ElasticService;
import com.project.elasticsearch.service.UserService;
import com.project.elasticsearch.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/elastic")
public class ElasticController {

    @Autowired
    private ElasticSearchClient esClient;

    @Autowired
    private ElasticService esService;


    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/exists")
    public ResponseDto indexExists(@RequestParam String indexName) {
        return ResponseUtils.createResponse(HttpStatus.OK.toString(), HttpStatus.OK.toString(),
            String.valueOf(Boolean.TRUE.equals(esClient.indexExists(indexName))));
    }

    @ResponseBody
    @PostMapping("/put-mapping")
    public ResponseDto pubMapping() {
        return ResponseUtils.createResponse(HttpStatus.OK.toString(), HttpStatus.OK.toString(),
            String.valueOf(Boolean.TRUE.equals(esService.putMapping())));
    }

    @ResponseBody
    @PostMapping("/full-index")
    public ResponseDto fullIndex() {
        return esService.fullExportToIndex();
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ListResponseDto> getAllUsers(
        @RequestParam(required = false) Integer p,
        @RequestParam(required = false) String s,
        @RequestParam(required = false) Boolean asc,
        @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        int page = p != null ? p : 0;
        String sort = s == null ? EMAIL_ANALYZED : s;
        boolean ascending = asc == null || asc;
        ListResponseDto users = userService.search( null, page, size, null, sort, ascending);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/by-id")
    public ResponseEntity<UserElasticDto> getUserById(
        @RequestParam() String id
    ) {
        UserElasticDto users = (UserElasticDto) userService.findById(id);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/by-email")
    public ResponseEntity<UserElasticDto> getUserByEmail(
        @RequestParam() String email
    ) {
        UserElasticDto users = (UserElasticDto) userService.findByEmail(email);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<ListResponseDto> searchUser(
        @RequestParam(required = false) String q,
        @RequestParam(required = false) Integer p,
        @RequestParam(required = false) String s,
        @RequestParam(required = false) Boolean asc,
        @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        Map<String, List<Object>> filters = new HashMap<>();
        filters.put("role", Collections.singletonList(RoleEnum.ARTIST));
        int page = p != null ? p : 0;
        String sort = s == null ? EMAIL_ANALYZED : s;
        boolean ascending = asc == null || asc;
        ListResponseDto users = userService.search( q, page, size, filters, sort, ascending);
        return ResponseEntity.ok(users);
    }

}
