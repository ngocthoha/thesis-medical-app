package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.exception.ApiRequestException;
import com.thesis.medicalapp.models.Favorite;
import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.models.Profile;
import com.thesis.medicalapp.models.User;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.repository.ProfileRepository;
import com.thesis.medicalapp.search.*;
import com.thesis.medicalapp.services.FavoriteService;
import com.thesis.medicalapp.services.ProfileService;
import com.thesis.medicalapp.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final UserService userService;
    private final ProfileRepository profileRepository;

    @Data
    public static class FavoriteRequest {
        @NotNull
        private Integer favorite;
        private String comment;
        @NotNull
        private String username;
        @NotNull
        private String objectId;
    }

    @PostMapping("")
    public ResponseEntity<Object> saveFavorite(@RequestBody @Valid FavoriteRequest favoriteRequest) {
        String username = favoriteRequest.getUsername();
        if (!userService.existsByUsername(username))
            throw new ApiRequestException("Không tìm thấy user!");

        Favorite favorite = new Favorite();
        favorite.setFavorite(favoriteRequest.getFavorite());
        favorite.setComment(favoriteRequest.getComment());
        favorite.setDate(new Date());
        Profile profile = profileRepository.findProfileByRelationshipAndUser_Username("Chủ tài khoản", username);
        favorite.setProfile(profile);
        favorite.setObjectId(favoriteRequest.getObjectId());
        Favorite favoriteRes = favoriteService.save(favorite);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(favoriteRes)
        );
    }

    @GetMapping("")
    public ResponseEntity<Object> getFavorites(
            @RequestParam String objectId,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        SearchRequest request = new SearchRequest(objectId, page, size);
        Page<Favorite> pageResponse = favoriteService.getAll(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(pageResponse.getContent(), pageResponse)
        );
    }
}
