package com.hanssem.remodeling.common.admin.controller.commoncode;

import com.hanssem.remodeling.common.admin.service.commoncode.CommoncodeAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "어드민 > 공통코드")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/v1/commoncode", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommoncodeAdminController {

    private final CommoncodeAdminService commoncodeAdminService;

    @Operation(
        summary = "공통코드 등록", description = ""
        + "import file을 다운로드 받아서 실행합니다.<br><br>"
        + "<a href='https://docs.google.com/spreadsheets/d/1PRM3VZ-wpvNxYUiki8vsFCb_tDvbUxWAcELz9DNFewM/edit#gid=0' target=_blank>Excel 파일 다운로드</a>",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = Long.class)))})
    @PostMapping(value = "/data-import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addCodeImport(@RequestParam(name = "file") MultipartFile file) throws IOException {

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        commoncodeAdminService.addCodeImport(file, extension);
    }

}
