package com.travelvcommerce.personalizedservice.controller;

import com.travelvcommerce.personalizedservice.dto.ResponseDto;
import com.travelvcommerce.personalizedservice.dto.TagDto;
import com.travelvcommerce.personalizedservice.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("personalize-service/tags")
public class TagController {

    private final TagService tagService;

    //TagId가 유효한지 아닌지 예외처리 추가해야함
    @PostMapping("/{userId}/init")
    public ResponseEntity<ResponseDto> InitSubscribedTagList(@PathVariable String userId, @RequestBody TagDto.InitTagListRequestDto initTagListRequestDto){

        Map<String, String> initTagListResponse = tagService.initSubscribedTagList(userId, initTagListRequestDto);

        ResponseDto responseDto = ResponseDto.builder()
                .payload(initTagListResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto> getSubscribedTagList(@PathVariable String userId){

        Map<String, Object> subscribedTagListResponse = new HashMap<>();
        subscribedTagListResponse.put("userId", userId);
        subscribedTagListResponse.put("subscribedTagList", tagService.getSubscribedTagList(userId));

        ResponseDto responseDto = ResponseDto.builder().
                payload(subscribedTagListResponse).
                build();

        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/{userId}/subscribe")
    public ResponseEntity<ResponseDto> subscribeTag(@PathVariable String userId, @RequestBody TagDto.SubscribeTagRequestDto subscribeTagRequestDto){
        Map<String, String> subscribeTagResponse = tagService.subscribeTag(userId, subscribeTagRequestDto);

        ResponseDto responseDto = ResponseDto.builder()
                .payload(subscribeTagResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/{userId}/unsubscribe")
    public ResponseEntity<ResponseDto> unsubscribeTag(@PathVariable String userId, @RequestBody TagDto.UnsubscribeTagRequestDto unsubscribeTagRequestDto){
        Map<String, String> unsubscribeTagResponse = tagService.unsubscribeTag(userId, unsubscribeTagRequestDto);

        ResponseDto responseDto = ResponseDto.builder()
                .payload(unsubscribeTagResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/{userId}/view")
    public ResponseEntity<ResponseDto> viewTag(){


        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
