package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.exception.ApiRequestException;
import com.thesis.medicalapp.models.Favorite;
import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.models.User;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.search.SearchRequest;
import com.thesis.medicalapp.services.FavoriteService;
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
import java.util.Date;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final UserService userService;

    @Data
    public static class FavoriteRequest {
        @NotNull
        private Double favorite;
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
        User user = userService.findByUsername(username);
        favorite.setUser(user);
        favorite.setObjectId(favoriteRequest.getObjectId());
        Favorite favoriteRes = favoriteService.save(favorite);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(favoriteRes)
        );
    }

    @GetMapping("")
    public ResponseEntity<Object> getFavorites(@RequestBody SearchRequest request) {
        Page<Favorite> page = favoriteService.getAll(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(page.getContent(), page.getPageable())
        );
    }
}
