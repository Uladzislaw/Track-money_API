package com.serh.trackmoney.controller.helper;

import com.serh.trackmoney.controller.UserController;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class RelatedLinkCreatorHelper {

    public List<Link> createSimpleListLinkForUser(final Long id, final Integer pageSize) {
        //calculates page number where is user placed among all users
        int userPage = id <= pageSize ? 1 : (pageSize / id.intValue()) + 1;
        return asList(linkTo(methodOn(UserController.class).getUserById(id))
                        .withSelfRel(),
                linkTo(methodOn(UserController.class).all(userPage, pageSize, null))
                        .withRel("users"));
    }
}
