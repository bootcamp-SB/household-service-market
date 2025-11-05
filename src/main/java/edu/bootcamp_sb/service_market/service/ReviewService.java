package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ReviewsDto;
import edu.bootcamp_sb.service_market.dto.reponse.ReviewResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {

    ResponseEntity<ReviewResponseDto> giveAReview(ReviewsDto review);

    ResponseEntity<List<ReviewResponseDto>> getAllResponses();


}
