package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.ReviewsDto;
import edu.bootcamp_sb.service_market.dto.reponse.ReviewResponseDto;
import edu.bootcamp_sb.service_market.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDto>makeANewReview(@RequestBody ReviewsDto review){
        return reviewService.giveAReview(review);

    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewResponseDto>>getAll(){
        return reviewService.getAllResponses();
    }

    @GetMapping("/by-gig-id")
    public ResponseEntity<List<ReviewResponseDto>>getByGigId(@RequestParam UUID id){
        return reviewService.getAllReviewInGig(id);
    }

    @PostMapping
    public ResponseEntity<Map<String,String>>providerReply(
            @RequestParam UUID gigId, @RequestParam String reply){
        return reviewService.providerResponse(gigId,reply);
    }

    @DeleteMapping("/by-id")
    public ResponseEntity<Map<String,String>>deleteReview(@RequestParam UUID id){
        return reviewService.deleteReview(id);
    }
}
