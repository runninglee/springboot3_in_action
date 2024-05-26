package com.sp3.chapter6.app.control;

import com.sp3.chapter6.util.api.ResultJson;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/control")
public class RequestController {

    @GetMapping("{id}")
    public ResultJson<Object> getPath(@PathVariable("id") Long id) {
        return ResultJson.success(new HashMap<>() {{
            put("Path Variable:", id);
        }});
    }

    @GetMapping("query")
    public ResultJson<Object> getParam(@RequestParam("id") Long id) {
        return ResultJson.success(new HashMap<>() {{
            put("Query Param:", id);
        }});
    }

    @PostMapping
    public ResultJson<Object> store(@RequestBody HashMap<String, Object> param) {
        return ResultJson.success(new HashMap<>() {{
            put("Post Param:", param);
        }});
    }

    @PutMapping("{id}")
    public ResultJson<Object> update(@PathVariable("id") String id) {
        return ResultJson.success(new HashMap<>() {{
            put("Put Param:", id);
        }});
    }

    @DeleteMapping("{id}")
    public ResultJson<Object> destroy(@PathVariable("id") String id) {
        return ResultJson.success(new HashMap<>() {{
            put("Delete Param:", id);
        }});
    }

    @GetMapping("header")
    public ResultJson<Object> header(@RequestHeader("user-agent") String userAgent) {
        return ResultJson.success(new HashMap<>() {{
            put("Header Param:", userAgent);
        }});
    }

}
