package com.learn.ratelimiter.areaCalculator;


import com.learn.ratelimiter.areaCalculator.DTO.BaseResponse;
import com.learn.ratelimiter.areaCalculator.DTO.ErrorResponse;
import com.learn.ratelimiter.areaCalculator.DTO.Rectangle;
import io.github.bucket4j.Bucket;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@Slf4j
public class areaCalculatorController {

//    @Autowired



    /*
    * POST endpoint to calculate area of a rectangle which takes a pojo as input
    * */
  /*  @PostMapping("/rectangle")

    public BaseResponse<Double> calculateArea(@RequestBody Rectangle rectangle) {
        if (bucket.tryConsume(1)) {
            double v =  rectangle.getLength() * rectangle.getWidth();
            return new BaseResponse(v, null, 200);
 *//*           BaseResponse<Double> doubleBaseResponse = new BaseResponse<>();
            doubleBaseResponse.setPayload(v);
            doubleBaseResponse.setHttpStatusCode(200);
            return doubleBaseResponse;*//*
        } else {
            log.error("Too many requests");
            return  new BaseResponse<>(ErrorResponse.builder()
                    .status(429)
                    .error("Too many requests")
                    .message("Too many requests")
                    .build(), null, 429);
        }
    }*/


    //a duplicate of the above method without the if conditions to test the rate limiter

    @PostMapping("/rectangle2")
    public BaseResponse<Double> calculateArea2(@RequestBody Rectangle rectangle) {
        double v =  rectangle.getLength() * rectangle.getWidth();
        return new BaseResponse(v, null, 200);
    }


}
