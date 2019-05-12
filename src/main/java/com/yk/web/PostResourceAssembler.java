package com.yk.web;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.yk.web.post.entity.Posts;

@Component
public class PostResourceAssembler implements ResourceAssembler<Posts, Resource<Posts>>{

	@Override
	public Resource<Posts> toResource(Posts entity) {
		
		
		 return new Resource<>(entity,
			      linkTo(methodOn(PostRestController.class).getPost(entity.getPost_id())).withSelfRel(),
			      linkTo(methodOn(PostRestController.class).postList()).withRel("all"));
	}
	
}
