package com.example.backend;

import com.example.backend.service.BoardService;
import com.example.backend.service.ProjectMemberService;
import com.example.backend.service.ProjectService;
import com.example.backend.util.ResponseEntityCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResponseEntityCreatorTest {

    @Autowired ProjectService projectService;
    @Autowired ProjectMemberService projectMemberService;
    @Autowired BoardService boardService;

    ResponseEntityCreator creator = new ResponseEntityCreator();

    @Test
    public void test1() {
        //System.out.println(creator.create(boardService.getBoardsByProjectNo(13L)).toString());
        //System.out.println(creator.create(projectService.getProjectsByLeaderNo(13L)));
        //System.out.println(creator.create(projectMemberService.getProjectsByUser(12L)));
        System.out.println(creator.create(projectService.getProjectByNo(13L)));
        System.out.println(creator.create(projectService.getProjectByNo(1L)));
    }
}
