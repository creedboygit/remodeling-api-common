package com.hanssem.remodeling.common.api.controller.member;

import com.hanssem.remodeling.common.api.controller.member.request.AddMemberRequest;
import com.hanssem.remodeling.common.api.controller.member.request.PathTestRequest;
import com.hanssem.remodeling.common.api.controller.member.request.SearchRequest;
import com.hanssem.remodeling.common.api.controller.member.request.UpdateMemberRequest;
import com.hanssem.remodeling.common.api.controller.member.response.AddMemberResponse;
import com.hanssem.remodeling.common.api.controller.member.response.MembersResponse;
import com.hanssem.remodeling.common.api.controller.member.response.MembersResponse.MemberResponse;
import com.hanssem.remodeling.common.api.controller.member.response.PathTestResponse;
import com.hanssem.remodeling.common.api.controller.member.response.PathTestResponse2;
import com.hanssem.remodeling.common.api.service.member.MemberService;
import com.hanssem.remodeling.common.api.service.member.dto.MemberInfoDto;
import com.hanssem.remodeling.common.common.response.PageResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Hidden
@Tag(name = "Member")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/member", produces = MediaType.APPLICATION_JSON_VALUE)
public final class MemberController {

    private final MemberService memberService;

    @Operation(summary = "생성" , description = "회원 생성", responses = { @ApiResponse(content = @Content(schema = @Schema(implementation = AddMemberResponse.class)))})
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddMemberResponse addMember(@RequestBody @Valid final AddMemberRequest request) {
        log.info("data =>  {}", request);
        return memberService.addMember(request);
    }

    @Operation(summary = "수정" , description = "회원 수정", responses = { @ApiResponse(content = @Content(schema = @Schema(implementation = AddMemberResponse.class)))})
    @PutMapping(value = "/{memberId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MemberResponse updateMember(@PathVariable(name = "memberId") long memberId
            ,@RequestBody @Valid final UpdateMemberRequest request) {
        log.info("data =>  {}", request);
        return memberService.updateMember(memberId, request);
    }

    @Operation(summary = "목록" , description = "회원 목록", responses = { @ApiResponse(content = @Content(schema = @Schema(implementation = MembersResponse.class)))})
    @GetMapping(value = "/list")
    public MembersResponse findMember() {
        return memberService.getMembers();
    }

    @Operation(summary = "목록2" , description = "회원 목록 (페이징 없음)", responses = { @ApiResponse(content = @Content(schema = @Schema(implementation = MembersResponse.class)))})
    @GetMapping(value = "/mapstruct-version")
    public MembersResponse findMember2() {
        return memberService.getMembersByMapstruct();
    }

    @Operation(summary = "목록" , description = "회원 목록", responses = { @ApiResponse(content = @Content(schema = @Schema(implementation = PageResponse.class)))})
    @GetMapping(value = "/paging-version", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<MemberInfoDto> findMember3(final SearchRequest request) {
        return memberService.getMembersByPaging(request);
    }
    
    @Operation(summary = "Path 정보에 도메인 붙이는 테스트" , description = "Path 정보를 보내면 CDN 도메인이 붙어서 나와요~", responses = { @ApiResponse(content = @Content(schema = @Schema(implementation = PathTestResponse.class)))})
    @PostMapping(value = "/path/test", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PathTestResponse pathTest(@RequestBody @Valid final PathTestRequest request) {
        log.info("data =>  {}", request);
        return memberService.pathTest(request);
    }

    @Operation(summary = "Path 정보에 도메인 붙이는 테스트" , description = "Path 정보를 보내면 CDN 도메인이 붙어서 나와요~", responses = { @ApiResponse(content = @Content(schema = @Schema(implementation = PathTestResponse.class)))})
    @PostMapping(value = "/path/test2", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PathTestResponse2 pathTest2(@RequestBody @Valid final PathTestRequest request) {
        log.info("data =>  {}", request);
        return memberService.pathTest2(request);
    }
}
