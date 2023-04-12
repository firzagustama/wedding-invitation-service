package jf.service.weddinginvitationservice.service.impl;

import jf.service.weddinginvitationservice.enums.ApiResponseCodes;
import jf.service.weddinginvitationservice.exception.ApiException;
import jf.service.weddinginvitationservice.model.UserWishes;
import jf.service.weddinginvitationservice.model.Users;
import jf.service.weddinginvitationservice.model.request.CreateWishRequest;
import jf.service.weddinginvitationservice.model.request.ListWishPaginationRequest;
import jf.service.weddinginvitationservice.model.response.ListWishPaginationResponse;
import jf.service.weddinginvitationservice.model.response.PaginationResponse;
import jf.service.weddinginvitationservice.model.response.CreateWishResponse;
import jf.service.weddinginvitationservice.repository.UserRepository;
import jf.service.weddinginvitationservice.repository.UserWishRepository;
import jf.service.weddinginvitationservice.service.UserWishService;
import jf.service.weddinginvitationservice.util.ParamHelper;
import jf.service.weddinginvitationservice.util.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserWishServiceImpl implements UserWishService {
    @Autowired
    private UserWishRepository userWishRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateWishResponse createUserWish(CreateWishRequest request) {
        ParamHelper.paramNotNull(request, "request body");
        ParamHelper.paramNotNull(request.getUserId(), "userId");
        ParamHelper.paramNotNullOrBlank(request.getWish(), "wish");
        ParamHelper.paramLength(request.getWish(), 0, 256, "wish");

        UUID userId = UUID.fromString(request.getUserId());
        Users user = userRepository.findById(userId).orElseThrow(() -> new ApiException(ApiResponseCodes.USER_NOT_FOUND));

        UserWishes userWishes = new UserWishes();
        userWishes.setWish(request.getWish());
        userWishes.setUser(user);
        userWishes.setCreatedDate(new Date());

        // save to db
        userWishRepository.save(userWishes);

        CreateWishResponse response = new CreateWishResponse();
        response.setTitle(UserHelper.getTitle(user.getGender(), user.isMarried()));
        response.setName(user.getName());
        response.setWish(userWishes.getWish());
        return response;
    }

    @Override
    public ListWishPaginationResponse getListUserWish(ListWishPaginationRequest request) {
        if (request.getPageNo() <= 0) request.setPageNo(1);
        if (request.getPageSize() <= 0) request.setPageSize(10);

        Pageable pageable = PageRequest.of(request.getPageNo() - 1, request.getPageSize(), Sort.by("createdDate").descending());
        Page<UserWishes> userWishes = userWishRepository.findAll(pageable);

        PaginationResponse info = new PaginationResponse();
        info.setPrevPageNo(request.getPageNo() <= 1 ? null : request.getPageNo() - 1);
        info.setCurrPageNo(request.getPageNo());
        info.setNextPageNo(request.getPageNo() >= userWishes.getTotalPages() ? null : request.getPageNo() + 1);
        info.setTotalData((int) userWishes.getTotalElements());
        info.setTotalPage(userWishes.getTotalPages());
        info.setPageSize(request.getPageSize());

        ListWishPaginationResponse response = new ListWishPaginationResponse();
        response.setInfo(info);
        response.setWishes(userWishes.stream().map(u -> {
            Users user = u.getUser();

            CreateWishResponse uw = new CreateWishResponse();
            uw.setTitle(UserHelper.getTitle(user.getGender(), user.isMarried()));
            uw.setName(user.getName());
            uw.setWish(u.getWish());
            return uw;
        }).collect(Collectors.toList()));
        return response;
    }
}
