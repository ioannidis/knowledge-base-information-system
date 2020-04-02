package eu.ioannidis.vks.commentservice.controllers;

import eu.ioannidis.vks.commentservice.models.entities.CommentEntity;
import eu.ioannidis.vks.commentservice.models.requests.CommentRequest;
import eu.ioannidis.vks.commentservice.services.CommentService;
import eu.ioannidis.vks.commentservice.utils.feignclients.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(CommentController.BASE_URL)
public class CommentController {

    static final String BASE_URL = "/v1/comments";

    private CommentService commentService;

    private UserFeignClient userFeignClient;

    @Autowired
    public CommentController(CommentService commentService, UserFeignClient userFeignClient) {
        this.commentService = commentService;
        this.userFeignClient = userFeignClient;
    }

    @GetMapping()
    public ResponseEntity<List<CommentEntity>> getCommentByVulnerability(HttpServletRequest request, @RequestParam(value = "vulnerability", required = false) String vulnerabilityId) {

        System.out.println(request.getHeader("Authorization"));
        System.out.println(vulnerabilityId);
        System.out.println(this.userFeignClient.getUser("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", request.getHeader("Authorization")).getBody());

        return Optional.of(commentService.findAllByVulnerability(UUID.fromString(vulnerabilityId)))
                .map(comments -> new ResponseEntity<>(comments, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping()
    public ResponseEntity<CommentEntity> saveComment(@Valid @RequestBody CommentRequest commentRequest) {

        System.out.println(commentRequest);

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentRequest.getContent());
        commentEntity.setVulnerability(commentRequest.getVulnerabilityId());

        System.out.println(commentEntity);
        System.out.println("=====================================");

        return new ResponseEntity<>(this.commentService.save(commentEntity), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentEntity> updateComment(@PathVariable int id, @Valid @RequestBody CommentRequest commentRequest) {

        return commentService.findById(id).map(comment -> {
            comment.setContent(commentRequest.getContent());

            return new ResponseEntity<>(commentService.save(comment), HttpStatus.OK);
        })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
