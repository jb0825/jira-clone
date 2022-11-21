package com.example.backend.controller;

import com.example.backend.dto.BodyDto;
import com.example.backend.dto.Message;
import com.example.backend.dto.StatusCode;
import com.example.backend.entity.Board;
import com.example.backend.entity.Project;
import com.example.backend.entity.User;
import com.example.backend.service.BoardService;
import com.example.backend.service.ProjectMemberService;
import com.example.backend.service.ProjectService;
import com.example.backend.util.ResponseEntityBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private BoardService boardService;

    public ResponseEntityBuilder maker = new ResponseEntityBuilder();

    @ApiOperation(value = "모든 프로젝트 조회", notes = "모든 프로젝트를 조회합니다.")
    @GetMapping
    public ResponseEntity<BodyDto> getAllProjects() {
        return new ResponseEntity<>(
            new BodyDto(StatusCode.OK, Message.READ, projectService.getAllProjects()),
            HttpStatus.OK
        );
    }

    @ApiOperation(value = "단일 프로젝트 조회", notes = "프로젝트 번호로 단일 프로젝트를 조회합니다.")
    @GetMapping("/{no}")
    public ResponseEntity<BodyDto> getProjectByNo(@PathVariable Long no) {
        Project project = projectService.getProjectByNo(no);
        return maker.read(project);
    }

    @ApiOperation(value = "프로젝트 멤버 조회", notes = "프로젝트 번호로 해당 프로젝트에 소속된 모든 멤버를 조회합니다.")
    @GetMapping("/{no}/member")
    public ResponseEntity<BodyDto> getProjectMembersByProject(@PathVariable Long no) {
        List<User> users = projectMemberService.getProjectMembersByProject(no);
        return maker.read(users);
    }

    @ApiOperation(value = "프로젝트 보드 조회", notes = "프로젝트 번호로 해당 프로젝트에 소속된 모든 보드를 조회합니다.")
    @GetMapping("/{no}/board")
    public ResponseEntity<BodyDto> getBoardsByProjectNo(@PathVariable Long no) {
        List<Board> boards = boardService.getBoardsByProjectNo(no);
        return maker.read(boards);
    }

    @PostMapping
    public ResponseEntity<BodyDto> createProject(@RequestBody Project project) {
        BodyDto body = new BodyDto();
        ResponseEntity<BodyDto> badRequest = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        if (project == null) return badRequest;
        if (!projectService.createProject(project)) return badRequest;

        body.setStatus(StatusCode.CREATE);
        body.setMessage(Message.CREATE);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity deleteProject(@PathVariable Long no) {
        ResponseEntity badRequest = new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (no == null) return badRequest;

        return projectService.deleteProject(no) ? new ResponseEntity(HttpStatus.OK) : badRequest;
    }

    /* PROJECT LEADER */
    /* 추후 Session 으로 로그인한 사용자가 리더인 프로젝트 목록 조회로 바꿀것 */
    @ApiOperation(value = "리더로 프로젝트 목록 조회", notes = "리더 (프로젝트 생성자) 번호로 생성된 모든 프로젝트를 조회합니다.")
    @GetMapping("/leader/{no}")
    public ResponseEntity<BodyDto> getProjectsByLeaderNo(@PathVariable Long no) {
        List<Project> projects = projectService.getProjectsByLeaderNo(no);
        return maker.read(projects);
    }

    /* PROJECT MEMBER */
    /* 추후 Session 으로 로그인한 사용자가 소속된 프로젝트 목록 조회로 바꿀것 */
    @ApiOperation(value = "멤버로 프로젝트 목록 조회", notes = "멤버 (사용자) 번호로 소속된 모든 프로젝트를 조회합니다.")
    @GetMapping("/member/{no}")
    public ResponseEntity<List<Project>> getProjectsByUser(@PathVariable Long no) {
        if (no == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(projectMemberService.getProjectsByUser(no), HttpStatus.OK);
    }

    // join project, leave project 만들기 (session) path variable projectNo


}
