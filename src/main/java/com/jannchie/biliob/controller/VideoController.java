package com.jannchie.biliob.controller;

import com.jannchie.biliob.exception.UserAlreadyFavoriteVideoException;
import com.jannchie.biliob.exception.VideoAlreadyFocusedException;
import com.jannchie.biliob.model.Video;
import com.jannchie.biliob.service.VideoService;
import com.jannchie.biliob.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author jannchie
 */
@RestController
public class VideoController {

  private final VideoService videoService;

  @Autowired
  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/api/video/{aid}")
  public Video getVideoDetails(@PathVariable("aid") Long aid) {
    return videoService.getVideoDetails(aid);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/api/author/{mid}/video/{aid}")
  public Slice<Video> getAuthorVideo(
      @PathVariable("aid") Long aid,
      @PathVariable("mid") Long mid,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "5") Integer pagesize) {
    return videoService.getAuthorOtherVideo(aid, mid, page, pagesize);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/api/author/{mid}/video")
  public ResponseEntity getAuthorTopVideo(
      @PathVariable("mid") Long mid,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "5") Integer pagesize) {
    return videoService.getAuthorTopVideo(mid, page, pagesize);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/api/video")
  public ResponseEntity<Message> postVideoByAid(@RequestBody @Valid Long aid)
      throws VideoAlreadyFocusedException, UserAlreadyFavoriteVideoException {
    return videoService.postVideoByAid(aid);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/api/video")
  public Page<Video> getVideo(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "20") Integer pageSize,
      @RequestParam(defaultValue = "-1") Long aid,
      @RequestParam(defaultValue = "") String text) {
    return videoService.getVideo(aid, text, page, pageSize);
  }
}